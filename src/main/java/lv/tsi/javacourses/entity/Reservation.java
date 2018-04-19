package lv.tsi.javacourses.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private User user;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column
    private double rentPrice;

    private static Logger logger = Logger.getLogger("Reservation");
    public long getPeriodInDays() {
        long diff = endDate.getTime() - startDate.getTime();
        logger.info("Diff: "+diff);
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndtDate() {
        return endDate;
    }

    public void setEndtDate(Date endtDate) {
        this.endDate = endtDate;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }
}
