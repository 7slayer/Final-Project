package plantFrost.entity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantFrost.exception.DbException;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantDate {
  Integer plantId;
  Integer dateId;
  
 
  String plannedDate;
  

  String startDate;
  
  String maturityDate;
  
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
  

  public String getPlannedDate() {
    return plannedDate;

  }
  
  public void setPlannedDate(String plannedDate) {
    this.plannedDate = plannedDate;
  }
  

  public String getStartDate() {
    return startDate;
  }
  
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  

  public String getMatDate() {
    return maturityDate;
  }
  
  public void setMatDate(String maturityDate) {
    this.maturityDate = maturityDate;
  }
  
  public Date formatPlannedDate() {
    
    try {
      return new SimpleDateFormat("dd/MM/yyyy").parse(plannedDate);   
  } catch (ParseException e) {
      throw new DbException("Improper date format set for planned date");
  }
    
  }
  
public Date formatStartDate() {
    
    try {
      return new SimpleDateFormat("dd/MM/yyyy").parse(startDate);      
  } catch (ParseException e) {
      throw new DbException("Improper date format set for start date");
  }
    
  }

public Date formatMaturityDate() {
  
  try {
    return new SimpleDateFormat("dd/MM/yyyy").parse(maturityDate);
     
} catch (ParseException e) {
    throw new DbException("Improper date format set for maturity date");
}
  
}
  
  @Override
  public String toString() {
    String result = "";
    result += "\n      Date ID = " + dateId;
    result += "\n      Planned Date = " + plannedDate;
    result += "\n      Start Date = " + startDate;
    result += "\n      Date of maturity = " + maturityDate;
    return result;
  }
  
}