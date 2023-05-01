package plantFrost.entity;

import java.util.Date;

public class PlantDate {
  Integer plantId;
  Integer dateId;
  Date plannedDate;
  Date startDate;
  Date matDate;
  
  public Integer getPlantId() {
    return plantId;
  }

  public void setPlantId(Integer plantId) {
    this.plantId = plantId;
  }
  
  public Integer getdateId() {
    return dateId;
  }
  
  public void setDateId(Integer dateId) {
    this.dateId = dateId;
  }
  

  public Date getPlannedDate() {
    return plannedDate;
  }
  
  public void setPlannedDate(Date plannedDate) {
    this.plannedDate = plannedDate;
  }
  

  public Date getStartDate() {
    return startDate;
  }
  
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  

  public Date getMatDate() {
    return matDate;
  }
  
  public void setMatDate(Date matDate) {
    this.matDate = matDate;
  }
  
}