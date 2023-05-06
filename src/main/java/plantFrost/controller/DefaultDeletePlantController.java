package plantFrost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import plantFrost.service.PlantFrostService;

@RestController
public class DefaultDeletePlantController implements DeletePlantController {

  @Autowired
  private PlantFrostService plantService;
  
  @Override
  public void deletePlant(Integer plantId) {
    // TODO Auto-generated method stub
    plantService.deletePlant(plantId);
  }

}
