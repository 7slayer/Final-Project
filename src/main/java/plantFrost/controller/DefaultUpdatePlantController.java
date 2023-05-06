package plantFrost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import plantFrost.entity.FrostDate;
import plantFrost.entity.Plant;
import plantFrost.service.PlantFrostService;

@RestController
public class DefaultUpdatePlantController implements UpdatePlantController {

  @Autowired
  private PlantFrostService plantService;
  
  @Transactional
  @Override
  public void updatePlant(Integer plantId, String plantName, Boolean isPerennial,
      Boolean doesFlower, Integer maturityDays) {
    
    Plant plant = new Plant();
    plant.setPlantId(plantId);
    plant.setPlantName(plantName);
    plant.setIsPerennial(isPerennial);
    plant.setDoesFlower(doesFlower);
    plant.setMaturityDays(maturityDays);
    plantService.modifyPlantDetails(plant);
  }

}
