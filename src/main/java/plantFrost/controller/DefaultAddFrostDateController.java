package plantFrost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import plantFrost.entity.FrostDate;
import plantFrost.service.PlantFrostService;

@RestController
public class DefaultAddFrostDateController implements AddFrostDateController {

  @Autowired
  private PlantFrostService plantService;
  
  @Transactional
  @Override
  public FrostDate addFrostDate(@RequestParam Integer plantId, String state) {
    // TODO Auto-generated method stub
    
    return plantService.addFrostDate(plantId, state);
  }

}
