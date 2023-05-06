package plantFrost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import plantFrost.entity.FrostDate;
import plantFrost.entity.Plant;
import plantFrost.service.PlantFrostService;

@RestController
public class DefaultAddPlantController implements AddPlantController {

  @Autowired
  private PlantFrostService plantService;
  
  @Transactional
  @Override
  public Plant addPlant(String plantName, Boolean isPerennial, Boolean doesFlower,
      Integer maturityDays) {
    // TODO Auto-generated method stub
    Plant plant = new Plant();
    plant.setPlantName(plantName);
    plant.setIsPerennial(isPerennial);
    plant.setDoesFlower(doesFlower);
    plant.setMaturityDays(maturityDays);
    return plantService.addPlant(plant);
  }

}
