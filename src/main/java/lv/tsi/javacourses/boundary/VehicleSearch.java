package lv.tsi.javacourses.boundary;

import lv.tsi.javacourses.entity.Vehicle;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

import java.util.logging.Logger;

@ViewScoped
@Named
public class VehicleSearch implements Serializable{
    private static Logger logger = Logger.getLogger("VehicleSearch");
    @PersistenceContext
    private EntityManager em;

    private List<String> models;
    private List<Integer> years;
    private List<String> gearboxs;
    private List<Integer> passengers;
    private List<Vehicle> searchResult;

    private String model;
    private Integer year;
    private String gearbox;
    private Integer passenger;

    public void showAll() {
        searchResult = em.createQuery("select v from Vehicle v").getResultList();
        models = em.createQuery("select distinct v.model from Vehicle v").getResultList();
        years = em.createQuery("select distinct v.year from Vehicle v order by v.year").getResultList();
        gearboxs  = em.createQuery("select distinct v.gearbox from Vehicle v").getResultList();
        passengers = em.createQuery("select distinct v.passengers from Vehicle v").getResultList();
    }



    public void searchCars(){
        logger.info("Model: "+model);
        logger.info("Year: "+year);
        logger.info("Gearbox" + gearbox);
        logger.info("Passenger: "+ passenger);
        searchResult = em.createQuery("select v from Vehicle v where " +
                "v.model = case when :model is null then v.model else :model end and " +
                "v.year = case when :year is null then v.year else :year end and " +
                "v.gearbox = case when :gearbox is null then v.gearbox else :gearbox end and " +
                "v.passengers = case when :passenger is null then v.passengers else :passenger end" )
                .setParameter("model",model)
                .setParameter("year", year)
                .setParameter("gearbox",gearbox)
                .setParameter("passenger",passenger)
                .getResultList();


    }

    public List<String> getGearboxs() {
        return gearboxs;
    }

    public void setGearboxs(List<String> gearboxs) {
        this.gearboxs = gearboxs;
    }

    public List<Integer> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Integer> passengers) {
        this.passengers = passengers;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public Integer getPassenger() {
        return passenger;
    }

    public void setPassenger(Integer passenger) {
        this.passenger = passenger;
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }





    public List<Vehicle> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<Vehicle> searchResult) {
        this.searchResult = searchResult;
    }
}
