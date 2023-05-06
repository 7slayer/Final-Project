package plantFrost.service;

import java.util.List;
import plantFrost.entity.FrostDate;
import plantFrost.entity.Plant;
import plantFrost.entity.PlantDate;

public interface PlantFrostService {

  Plant addPlant(Plant plant);

  List<Plant> fetchAllPlants();

  Plant fetchPlantById(Integer plantId);
  
  void modifyPlantDetails(Plant plant);

  void deletePlant(Integer plantId);
  
  FrostDate addFrostDate(Integer plantId, String state);

  PlantDate addPlantDate(PlantDate plantDate, Integer plantId);
  
  List<PlantDate> fetchPlantDateById(Integer plantId);

}
