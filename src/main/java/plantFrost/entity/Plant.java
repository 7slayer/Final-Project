package plantFrost.entity;

import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.LinkedList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plant {
  private Integer plantId;
  
  @Length(max =30)
  @Pattern(regexp = "[A-Z_]*")
  private String plantName;
  
  private Boolean isPerennial;
  private Boolean doesFlower;
  
  private Integer maturityDays;

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

  public Integer getMaturityDays() {
    return maturityDays;
  }

  public void setMaturityDays(Integer matDays) {
    this.maturityDays = matDays;
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
    result += "\n   days to mature = " + maturityDays;
    
    result += "\n   dates planted:";
    
    for(PlantDate plantDate : plantDates) {
      result += plantDate.toString();
    }
    
    result += "\n   frost dates:";
    
    for(FrostDate frostDates : frostDates) {
      result += "\n   " + frostDates.toString();
    }

    return result;
  }
}
