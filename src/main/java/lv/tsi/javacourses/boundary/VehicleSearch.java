package lv.tsi.javacourses.boundary;

import lv.tsi.javacourses.entity.Vehicle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RequestScoped
@Named
public class VehicleSearch {
    private static Logger logger = Logger.getLogger("VehicleSearch");
    @PersistenceContext
    private EntityManager em;

    private List<String> itemModel;
    private List<String> itemYear;
    private List<Vehicle> searchResult;

    private String parModel;
    private String parYear;

    public void showAll() {
        searchResult = em.createQuery("select v from Vehicle v").getResultList();
    }

    public void listModels() {
        itemModel = em.createQuery("select distinct v.model from Vehicle v").getResultList();
    }

    public void listYears() {
        itemYear = em.createQuery("select distinct v.year from Vehicle v").getResultList();
    }


    public void searchCars(){
        logger.info("Model: "+parModel);
        logger.info("yesr: "+parYear);
    }

    public String getParModel() {
        return parModel;
    }

    public void setParModel(String parModel) {
        this.parModel = parModel;
    }

    public String getParYear() {
        return parYear;
    }

    public void setParYear(String parYear) {
        this.parYear = parYear;
    }

    public List<String> getItemYear() {
        return itemYear;
    }

    public void setItemYear(List<String> itemYear) {
        this.itemYear = itemYear;
    }

    public List<String> getItemModel() {
        return itemModel;
    }

    public void setItemModel(List<String> itemModel) {
        this.itemModel = itemModel;
    }

    public List<Vehicle> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<Vehicle> searchResult) {
        this.searchResult = searchResult;
    }
}
