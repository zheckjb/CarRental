package lv.tsi.javacourses.entity;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
        @Id
        @GeneratedValue
        private Long id;
        @Column(nullable = false)
        private String model;
        @Column(nullable = false)
        private int year;
        @Column(length = 50, nullable = false)
        private String description;
        @Column(nullable = false)
        private int doors;
        @Column(nullable = false)
        private int passengers;
        @Column(nullable = false)
        private String gearbox;
        @Column(nullable = false)
        private boolean climat;
        @Column(nullable = false)
        private int tank;
    @Column(nullable = false)
    private double rate;

    private String descr;
    private String accontrol;

    public String getAccontrol() {
        setAccontrol(isClimat() ? "Yes" : "No");
        return accontrol;
    }

    public void setAccontrol(String accontrol) {
        this.accontrol = accontrol;
    }

    public String getDescr() {
        setDescr(String.format("%s, %s, year %d, doors: %d",model,gearbox,year,doors));
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public boolean isClimat() {
        return climat;
    }

    public void setClimat(boolean climat) {
        this.climat = climat;
    }

    public int getTank() {
        return tank;
    }

    public void setTank(int tank) {
        this.tank = tank;
    }
}
