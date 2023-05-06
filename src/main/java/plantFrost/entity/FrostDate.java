package plantFrost.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrostDate {
  @NotNull
  @Length(max =15)
  @Pattern(regexp = "[A-Z_]*")
  String state;
  
  String firstFrost;
  String lastFrost;
  
  public void setState(String state) {
    this.state = state;
  }
  
  public String getState() {
    return state;
  }
  
  public void setFirstFrost(String firstFrost) {
    this.firstFrost = firstFrost;
  }
  
  public String getFrstFrost() {
    return firstFrost;
  }
  
  public void setLastFrost(String lastFrost) {
    this.lastFrost = lastFrost;
  }
  
  public String getLastFrost() {
    return lastFrost;
  }
  
  @Override
  public String toString() {
    String result = "";
    result += "\n      State planted in = " + state;
    result += "\n      First frost date = " + firstFrost;
    result += "\n      Last frost date = " + lastFrost;
    
    return result;
  }
  
}

