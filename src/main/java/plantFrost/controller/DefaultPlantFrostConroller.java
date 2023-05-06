package plantFrost.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import plantFrost.entity.Plant;
import plantFrost.service.*;

@RestController
public class DefaultPlantFrostConroller implements PlantFrostController {

  @Autowired
  private PlantFrostService plantService;
  
  @Override
  public List<Plant> fetchPlants() {
    // TODO Auto-generated method stub
    return plantService.fetchAllPlants();
  }

}
