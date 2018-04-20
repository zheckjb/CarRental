package lv.tsi.javacourses.boundary;

import lv.tsi.javacourses.entity.Reservation;
import lv.tsi.javacourses.entity.Status;
import lv.tsi.javacourses.entity.User;

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
public class MyOrderForm implements Serializable {
    @PersistenceContext
    private transient EntityManager em;
    @Inject
    private CurrentUser currentUser;

    private List<Reservation> orders;

    public void prepareOrders(){
//        User user = em.merge(currentUser.getSignedInUser());
        orders = em.createQuery("select r from Reservation r " +
                "where r.user = :user").setParameter("user",currentUser.getSignedInUser()).getResultList();

    }
    @Transactional
    public String cancelReservation(Reservation res) {
        Reservation resrv = em.merge(res);
        resrv.setStatus(Status.CANCELED);
//        prepareOrders();
        return "/user-space/myorder.xhtml?faces-redirect=true";
    }

    public List<Reservation> getOrders() {
        return orders;
    }

    public void setOrders(List<Reservation> orders) {
        this.orders = orders;
    }
@Transactional
    public Object payReservation(Reservation res) {
        Reservation resrv = em.merge(res);
        resrv.setStatus(Status.PAID);
//        prepareOrders();
        return "/user-space/myorder.xhtml?faces-redirect=true";

    }
}
