package lv.tsi.javacourses.boundary;

import lv.tsi.javacourses.control.Util;
import lv.tsi.javacourses.entity.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static lv.tsi.javacourses.entity.Status.CANCELED;

@RequestScoped
@Named
public class VehicleForm implements Serializable {
    private static Logger logger = Logger.getLogger("VehicleForm");
    @PersistenceContext
    private EntityManager em;
    @Inject
    private CurrentUser currentUser;

    private Long carId;
    private Vehicle vehicle;
    private Date startDate;
    private Date endDate;


    @Transactional
    public void searchCar() {
        vehicle = em.find(Vehicle.class, carId);
    }

    @Transactional
    public String reserve(){
        User user = currentUser.getSignedInUser();
        logger.info("Name: "+user.getFullName());
        List<Reservation> reservations = em.createQuery(
                "select r from Reservation r " +
                        "where r.vehicle = :vehicle " +
                        "and r.endDate >= :startDate " +
                        "and r.status <> ('CANCELED,REJCTED')")
                .setParameter("vehicle",vehicle)
                .setParameter("startDate",startDate)
                .getResultList();
        if(!reservations.isEmpty()){
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage("Reservation is not possible on that period"));
            return null;
        }
        logger.info("Make new reservation");
        logger.info("Vehicle: "+vehicle.getModel());
        logger.info("User: "+user.getFullName());
        Reservation reservation = new Reservation();
        reservation.setVehicle(vehicle);
        reservation.setUser(user);
        reservation.setStatus(Status.BOOKED);
        reservation.setStartDate(startDate);
        reservation.setEndtDate(endDate);
        reservation.setRentPrice(vehicle.getRate()*reservation.getPeriodInDays());
        logger.info("Ready to save");
        em.persist(reservation);
        return "/user-space/myorder.xhtml?faces-redirect=true";


    }



    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
