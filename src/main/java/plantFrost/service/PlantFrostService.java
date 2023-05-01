package plantFrost.service;

import java.util.List;
import java.util.NoSuchElementException;
import plantFrost.dao.*;
import plantFrost.entity.Plant;
import plantFrost.exception.*;

public class PlantFrostService {
  private PlantDao plantDao = new PlantDao();

  public Plant addPlant(Plant plant) {
      // TODO Auto-generated method stub
      
  return plantDao.insertPlant(plant);
  
  }

  public List<Plant> fetchAllPlants() {
      // TODO Auto-generated method stub
      return plantDao.fetchAllPlants();
  }

  public Plant fetchPlantById(Integer plantId) {
      // TODO Auto-generated method stub
      return plantDao.fetchPlantById(plantId).orElseThrow(
          () -> new NoSuchElementException("Plant with Plant ID=" + plantId + " doesn't exist")
          );
  }
  
  public void modifyPlantDetails(Plant plant) {
      if(!plantDao.modifyPlantDetails(plant)) {
          throw new DbException("Plant with ID=" + plant.getPlantId() + " does not exist.");
      }
  }

  public void deletePlant(Integer plantId) {
      if (!plantDao.deletePlant(plantId)) {
          throw new DbException("Plant with ID=" + plantId + " does not exist.");
      }
      
      
  }

}
