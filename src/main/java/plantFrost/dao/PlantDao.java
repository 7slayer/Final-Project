package plantFrost.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import plantFrost.entity.Plant;
import plantFrost.entity.PlantDate;
import plantFrost.entity.FrostDate;
import plantFrost.exception.DbException;
import plantFrost.util.DaoBase;

public class PlantDao extends DaoBase{

	private static final String FROST_DATES_TABLE = "frost_dates";
	private static final String FROST_PLANTS_TABLE = "frost_plants";
	private static final String PLANT_DATES_TABLE = "plant_date";
	private static final String PLANT_TABLE = "plants";
	
	public Plant insertPlant(Plant plant) {
		// TODO Auto-generated method stub
		
		// @formatter: off
		String sql = " INSERT INTO " + PLANT_TABLE +
				" (plant_name, is_perennial, does_flower, maturity_days)" +
				"VALUES " + "(?, ?, ?, ?)";
		// @formatter: on
		
		try (Connection conn = DbConnection.getConnection()) {		
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				
				setParameter(stmt, 1, plant.getPlantName(), String.class);
				setParameter(stmt, 2, plant.getIsPerennial(), Boolean.class);
				setParameter(stmt, 3, plant.getDoesFlower(), Boolean.class);
				setParameter(stmt, 4, plant.getMaturityDays(), Integer.class);
				
				stmt.executeUpdate();
				
				Integer plantId = getLastInsertId(conn, PLANT_TABLE);
				commitTransaction(conn);
				plant.setPlantId(plantId);
				return plant;
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

	public List<Plant> fetchAllPlants() {
		String sql = " SELECT * FROM " + PLANT_TABLE + " ORDER BY plant_name;";
		
		try (Connection conn = DbConnection.getConnection()) {
			
			startTransaction(conn);
			
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				
				
				try (ResultSet rs = stmt.executeQuery()) {
					
					List<Plant> plants = new ArrayList<Plant>();
					
					while (rs.next()) {
						
						plants.add(extract(rs, Plant.class));
						
					}
					
					return plants;
					
				}
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		} catch(SQLException e) {
			throw new DbException(e);
			
		}
	}

	public Optional<Plant> fetchPlantById(Integer plantId) {
		// TODO Auto-generated method stub
		
		String sql = " SELECT * FROM " + PLANT_TABLE + " WHERE plant_id = ?";
		
				
		try (Connection conn = DbConnection.getConnection()) {		
			startTransaction(conn);
					
			try {
				
				Plant plant = null;
				
				try(PreparedStatement stmt = conn.prepareStatement(sql)) {
					setParameter(stmt, 1, plantId, Integer.class);
					
					try(ResultSet rs = stmt.executeQuery()) {
						if(rs.next()) {
							plant = extract(rs, Plant.class);
						}
					}
				}
				
				if(Objects.nonNull(plant)) {
					plant.getFrostDate().addAll(fetchFrostDatesForPlant(conn, plantId));
					plant.getPlantDates().addAll(fetchPlantDatesForPlant(conn, plantId));
				}
				
				commitTransaction(conn);
				
				return Optional.ofNullable(plant);
				
			} catch (Exception e) {
				e.printStackTrace();
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
			
	}
	
	}

	private List<FrostDate> fetchFrostDatesForPlant(Connection conn, Integer plantId) throws SQLException {
		// TODO Auto-generated method stub
		//@formatter:off
		String sql = "" + "SELECT c.* from " + FROST_DATES_TABLE + " c " + 
				"JOIN " + FROST_PLANTS_TABLE + " pc USING (state) " +
				"WHERE plant_id = ?";
		//@formatter:on
		
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, plantId, Integer.class);
			
			try (ResultSet rs = stmt.executeQuery()) {
				List<FrostDate> frostDates = new LinkedList<FrostDate>();
				
				while(rs.next()) {
					frostDates.add(extract(rs, FrostDate.class));
				}
				
				return frostDates;
			}	
		} 
	}

	   public List<PlantDate> fetchPlantDatesForPlant(Integer plantId) {
	        // TODO Auto-generated method stub
	        // @formatter: off
	        String sql = "SELECT * from " + PLANT_DATES_TABLE + " WHERE plant_id = ?";
	        //@formatter: on
	        try (Connection conn = DbConnection.getConnection()) {
	          startTransaction(conn);
	        
    	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
    	            setParameter(stmt, 1, plantId, Integer.class);
    	            
    	            try (ResultSet rs = stmt.executeQuery()) {
    	                List<PlantDate> plantDates = new ArrayList<PlantDate>();
    	                while (rs.next()) {
    	                    plantDates.add(extract(rs, PlantDate.class));
    	                }
    	                return plantDates;
    	                
    	            }
    	        }   
	        } catch (SQLException e) {
	            throw new DbException(e);
	            
	        }
	    }
	
	private List<PlantDate> fetchPlantDatesForPlant(Connection conn, Integer plantId) throws SQLException {
		// TODO Auto-generated method stub
		// @formatter: off
		String sql = "SELECT * from " + PLANT_DATES_TABLE + " WHERE plant_id = ?";
		//@formatter: on
		
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, plantId, Integer.class);
			
			try (ResultSet rs = stmt.executeQuery()) {
				List<PlantDate> plantDates = new ArrayList<PlantDate>();
				while (rs.next()) {
					plantDates.add(extract(rs, PlantDate.class));
				}
				return plantDates;
				
			}
		}	
	}

	public boolean modifyPlantDetails(Plant plant) {
				// @formatter: off
				String sql = "UPDATE " + PLANT_TABLE + " SET " +
						"plant_name = ?, " +
						"is_perennial = ?, " + 
						"does_flower = ?, " +
						"maturity_days = ? " +
						"WHERE plant_id = ?";
				// @formatter: on
				
				try (Connection conn = DbConnection.getConnection()) {		
					startTransaction(conn);
					
					try(PreparedStatement stmt = conn.prepareStatement(sql)) {
						
						setParameter(stmt, 1, plant.getPlantName(), String.class);
						setParameter(stmt, 2, plant.getIsPerennial(), Boolean.class);
						setParameter(stmt, 3, plant.getDoesFlower(), Boolean.class);
						setParameter(stmt, 4, plant.getMaturityDays(), Integer.class);
						setParameter(stmt, 5, plant.getPlantId(), Integer.class);
						
						Boolean executed = stmt.executeUpdate() == 1;
						
						commitTransaction(conn);
						
						return executed;
							
						
						
						
						
					} catch (Exception e) {
						rollbackTransaction(conn);
						throw new DbException(e);
					}
				} catch (SQLException e) {
					throw new DbException(e);
				}
	}

	public boolean deletePlant(Integer plantId) {
		// @formatter: off
		String sql = "DELETE FROM " + PLANT_TABLE +
				" WHERE plant_id = ?";
		// @formatter: on
		
		try (Connection conn = DbConnection.getConnection()) {		
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				
				setParameter(stmt, 1, plantId, Integer.class);
				
				Boolean executed = stmt.executeUpdate() == 1;
				
				commitTransaction(conn);
				
				return executed;
					
				
				
				
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

  public FrostDate insertFrostDate(Integer plantId, String state) {
    // TODO Auto-generated method stub
    String sql = " INSERT INTO " + FROST_PLANTS_TABLE +
        " (state, plant_id)" +
        "VALUES " + "(?, ?)";
    
    try(Connection conn = DbConnection.getConnection()) {
      startTransaction(conn);
      
      FrostDate frostDate = fetchFrostDate(conn, state).orElseThrow(
          () -> new NoSuchElementException("State with the name " + state + " does not exist"));
      
      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        setParameter(stmt, 1, frostDate.getState(), String.class);
        setParameter(stmt, 2, plantId, Integer.class);
        
        stmt.executeUpdate();
        
        commitTransaction(conn);
        
        return frostDate;
        
      }
      
    } catch (SQLException e) {
      throw new DbException(e);
    }
  }

  private Optional<FrostDate> fetchFrostDate(Connection conn, String state) throws SQLException {
    // TODO Auto-generated method stub
    String sql = "SELECT * FROM " + FROST_DATES_TABLE + " WHERE state = ?";
    

      try {
          
          FrostDate frostDate = null;
          
          try(PreparedStatement stmt = conn.prepareStatement(sql)) {
              setParameter(stmt, 1, state, String.class);
              
              try(ResultSet rs = stmt.executeQuery()) {
                  if(rs.next()) {
                      frostDate = extract(rs, FrostDate.class);
                  }
              }
          }
          
          commitTransaction(conn);
          
          return Optional.ofNullable(frostDate);
          
      } catch (SQLException e) {
          e.printStackTrace();
          rollbackTransaction(conn);
          throw new DbException(e);
      }
  }

  public PlantDate insertPlantDate(PlantDate plantDate, Integer plantId) {
    // TODO Auto-generated method stub
    String sql = " INSERT INTO " + PLANT_DATES_TABLE +
        " (plant_id, planned_date, start_date, maturity_date)" +
        "VALUES " + "(?, ?, ?, ?)";
    
    try(Connection conn = DbConnection.getConnection()) {
      startTransaction(conn);
      
      try(PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        setParameter(stmt, 1, plantId, Integer.class);
        setParameter(stmt, 2, plantDate.getPlannedDate().toString(), String.class);
        setParameter(stmt, 3, plantDate.getStartDate().toString(), String.class);
        setParameter(stmt, 4, plantDate.getMatDate().toString(), String.class);
        
        stmt.executeUpdate();
        
        commitTransaction(conn);
        
        Integer dateId = getLastInsertId(conn, PLANT_DATES_TABLE);
        commitTransaction(conn);
        plantDate.setDateId(dateId);
        
        return plantDate;
        
      } catch (Exception e) {
        rollbackTransaction(conn);
        throw new DbException(e);
      }
      
    } catch (SQLException e) {
      throw new DbException(e);
    }
  }
  
}
