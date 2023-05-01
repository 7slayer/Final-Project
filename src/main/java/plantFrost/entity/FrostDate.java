package plantFrost.entity;

import java.util.Date;

public class FrostDate {
  String state;
  Date firstFrost;
  Date lastFrost;
  
  public void setState(String state) {
    this.state = state;
  }
  
  public String getState() {
    return state;
  }
  
  public void setFirstFrost(Date firstFrost) {
    this.firstFrost = firstFrost;
  }
  
  public Date getFrstFrost() {
    return firstFrost;
  }
  
  public void setLastFrost(Date lastFrost) {
    this.lastFrost = lastFrost;
  }
  
  public Date getLastFrost() {
    return lastFrost;
  }
}

