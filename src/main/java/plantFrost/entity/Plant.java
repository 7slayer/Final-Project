package plantFrost.entity;

import java.util.List;
import java.util.LinkedList;

public class Plant {
  private Integer plantId;
  private String plantName;
  private Boolean isPerennial;
  private Boolean doesFlower;
  private Integer matDays;

  private List<FrostDate> frostDates = new LinkedList<>();
  private List<PlantDate> plantDates = new LinkedList<>();

  public List<FrostDate> getFrostDate() {
    return frostDates;
  }
  
  public Integer getPlantId() {
    return plantId;
  }

  public void setPlantId(Integer plantId) {
    this.plantId = plantId;
  }

  public String getPlantName() {
    return plantName;
  }

  public void setPlantName(String plantName) {
    this.plantName = plantName;
  }

  public Boolean getIsPerennial() {
    return isPerennial;
  }

  public void setIsPerennial(Boolean isPerennial) {
    this.isPerennial = isPerennial;
  }

  public Boolean getDoesFlower() {
    return doesFlower;
  }

  public void setDoesFlower(Boolean doesFlower) {
    this.doesFlower = doesFlower;
  }

  public Integer getMatDays() {
    return matDays;
  }

  public void setMatDays(Integer matDays) {
    this.matDays = matDays;
  }
  
  public List<PlantDate> getPlantDates() {
    return plantDates;
  }

  @Override
  public String toString() {
    String result = "";
    
    result += "\n   ID = " + plantId;
    result += "\n   name = " + plantName;
    result += "\n   is a perennial = " + isPerennial;
    result += "\n   does flower = " + doesFlower;
    result += "\n   days to mature = " + matDays;
    
    result += "\n   dates planted:";
    
    for(PlantDate plantDate : plantDates) {
      result += "\n      " + plantDate;
    }
    
    result += "\n   frost dates:";
    
    for(FrostDate frostDates : frostDates) {
      result += "\n      " + frostDates;
    }

    return result;
  }
}
