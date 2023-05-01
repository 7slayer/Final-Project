package plantFrost.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import plantFrost.entity.Plant;
import plantFrost.entity.PlantDate;
import plantFrost.entity.FrostDate;
import plantFrost.exception.DbException;
import plantFrost.util.DaoBase;

public class PlantDao extends DaoBase {

	private static final String FROST_DATES_TABLE = "frost_dates";
	private static final String FROST_PLANTS_TABLE = "frost_plants";
	private static final String PLANT_DATES_TABLE = "plant_date";
	private static final String PLANT_TABLE = "plants";
	
	public Plant insertPlant(Plant plant) {
		// TODO Auto-generated method stub
		
		// @formatter: off
		String sql = " INSERT INTO " + PLANT_TABLE +
				" (plant_name, annual_type, flowering, maturity_days)" +
				"VALUES " + "(?, ?, ?, ?)";
		// @formatter: on
		
		try (Connection conn = DbConnection.getConnection()) {		
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				
				setParameter(stmt, 1, plant.getPlantName(), String.class);
				setParameter(stmt, 2, plant.getIsPerennial(), Boolean.class);
				setParameter(stmt, 3, plant.getDoesFlower(), Boolean.class);
				setParameter(stmt, 4, plant.getMatDays(), Integer.class);
				
				stmt.executeUpdate();
				
				Integer projectId = getLastInsertId(conn, PLANT_TABLE);
				commitTransaction(conn);
				plant.setPlantId(projectId);
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
		
		String sql = " SELECT * FROM " + PLANT_TABLE + " WHERE project_id = ?";
		
				
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
						"annual_type = ?, " + 
						"flowering = ?, " +
						"maturity_days = ?";
				// @formatter: on
				
				try (Connection conn = DbConnection.getConnection()) {		
					startTransaction(conn);
					
					try(PreparedStatement stmt = conn.prepareStatement(sql)) {
						
						setParameter(stmt, 1, plant.getPlantName(), String.class);
						setParameter(stmt, 2, plant.getIsPerennial(), Boolean.class);
						setParameter(stmt, 3, plant.getDoesFlower(), Boolean.class);
						setParameter(stmt, 4, plant.getMatDays(), Integer.class);
						
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
}
