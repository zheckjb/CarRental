package lv.tsi.javacourses.boundary;

import lv.tsi.javacourses.entity.Vehicle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@RequestScoped
@Named
public class VehicleForm implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private CurrentUser currentUser;

    private Long carId;
    private Vehicle vehicle;

    @Transactional
    public void searchCar() {
        vehicle = em.find(Vehicle.class, carId);
    }

@Transactional
public void reserve(){

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
