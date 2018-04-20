package lv.tsi.javacourses.boundary;

import lv.tsi.javacourses.entity.Reservation;
import lv.tsi.javacourses.entity.Status;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ManagerForm implements Serializable {
    @PersistenceContext
    private transient EntityManager em;
    @Inject
    private CurrentUser currentUser;

    private List<Reservation> ordersBooked;
    private List<Reservation> ordersAccepted;


    public void orderdersAwtConfirmation(){
        ordersBooked = em.createQuery("select r from Reservation r " +
                "where r.status = 'BOOKED'").getResultList();
    }
    public void orderdersAwtPayment(){
        ordersAccepted = em.createQuery("select r from Reservation r " +
                "where r.status = 'ACCEPTED' or r.status = 'PAID'").getResultList();
    }
    @Transactional
    public String acceptOrder(Reservation res){
        Reservation reservation = em.merge(res);
        reservation.setStatus(Status.ACCEPTED);
        return "/manager-space/orders.xhtml?faces-redirect=true";
    }

    @Transactional
    public String rejectOrder(Reservation res){
        Reservation reservation = em.merge(res);
        reservation.setStatus(Status.REJECTED);
        return "/manager-space/orders.xhtml?faces-redirect=true";
    }

    public List<Reservation> getOrdersBooked() {
        return ordersBooked;
    }

    public void setOrdersBooked(List<Reservation> ordersBooked) {
        this.ordersBooked = ordersBooked;
    }

    public List<Reservation> getOrdersAccepted() {
        return ordersAccepted;
    }

    public void setOrdersAccepted(List<Reservation> ordersAccepted) {
        this.ordersAccepted = ordersAccepted;
    }
}
