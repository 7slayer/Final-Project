package plantFrost.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import plantFrost.dao.PlantDao;
import plantFrost.entity.FrostDate;
import plantFrost.entity.Plant;
import plantFrost.entity.PlantDate;
import plantFrost.exception.DbException;

@Service
public class DefaultPlantFrostService implements PlantFrostService {

  PlantDao plantDao = new PlantDao();

  @Override
  public Plant addPlant(Plant plant) {
      // TODO Auto-generated method stub
      
  return plantDao.insertPlant(plant);
  
  }

  @Override
  public List<Plant> fetchAllPlants() {
      // TODO Auto-generated method stub
      return plantDao.fetchAllPlants();
  }

  @Override
  public Plant fetchPlantById(Integer plantId) {
      // TODO Auto-generated method stub
      return plantDao.fetchPlantById(plantId).orElseThrow(
          () -> new NoSuchElementException("Plant with Plant ID=" + plantId + " doesn't exist")
          );
  }
  
  @Override
  public void modifyPlantDetails(Plant plant) {
      if(!plantDao.modifyPlantDetails(plant)) {
          throw new DbException("Plant with ID=" + plant.getPlantId() + " does not exist.");
      }
  }

  @Override
  public void deletePlant(Integer plantId) {
      if (!plantDao.deletePlant(plantId)) {
          throw new DbException("Plant with ID=" + plantId + " does not exist.");
      }
  }
  
  @Override
  public FrostDate addFrostDate(Integer plantId, String state) {
    return plantDao.insertFrostDate(plantId, state);
  }

  @Override
  public PlantDate addPlantDate(PlantDate plantDate, Integer plantId) {
    // TODO Auto-generated method stub
    PlantDate newDate = plantDao.insertPlantDate(plantDate, plantId);
    return newDate;
  }

  @Override
  public List<PlantDate> fetchPlantDateById(Integer plantId) {
    // TODO Auto-generated method stub
    return plantDao.fetchPlantDatesForPlant(plantId);
  }

}
