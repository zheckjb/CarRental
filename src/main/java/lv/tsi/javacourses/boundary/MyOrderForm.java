package lv.tsi.javacourses.boundary;

import lv.tsi.javacourses.entity.Reservation;
import lv.tsi.javacourses.entity.Status;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        orders = em.createQuery("select r from Reservation r " +
                "where r.user = :user").setParameter("user",currentUser.getSignedInUser()).getResultList();

    }

    public void cancelReservation(Reservation res) {
        Reservation resrv = em.merge(res);
        resrv.setStatus(Status.CANCELED);
        prepareOrders();
    }

    public List<Reservation> getOrders() {
        return orders;
    }

    public void setOrders(List<Reservation> orders) {
        this.orders = orders;
    }
}
