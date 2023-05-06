package plantFrost.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import plantFrost.entity.PlantDate;
import plantFrost.service.PlantFrostService;

@RestController
public class DefaultPlantDatesController implements PlantDatesController {

  @Autowired
  private PlantFrostService plantService;
  
  @Override
  public List<PlantDate> fetchPlantDates(Integer plantId) {
    // TODO Auto-generated method stub
    return plantService.fetchPlantDateById(plantId);
  }

}
