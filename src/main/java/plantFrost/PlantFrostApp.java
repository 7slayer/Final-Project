package plantFrost;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import plantFrost.entity.*;
import plantFrost.service.*;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import plantFrost.exception.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Calendar;

@SpringBootApplication(scanBasePackages = {"plantFrost"})
public class PlantFrostApp {
  
  private Scanner in = new Scanner(System.in);
  
  //  @formatter: off
  private List<String> operations = List.of(
      "1) Add a plant",
      "2) List of plants",
      "3) Select a plant",
      "4) Update plant details",
      "5) Delete a plant",
      "6) Add a state to plant",
      "7) Add a date you planted"
  );
  //  @formatter: on
  
  Plant curPlant = null;
  
  DefaultPlantFrostService plantService;

  public static void main(String[] args) {
    
    SpringApplication.run(PlantFrostApp.class, args);
    
    new PlantFrostApp().processUserSelections();
  }
  
  private void processUserSelections() {
    // TODO Auto-generated method stub
    boolean done = false; //This is an active flag that is use full for exiting a loop
    
    while (!done) {
        
        try {
            printOperation();
            int selection = getUserSelection();
            
            
            switch (selection) {
            
            case -1:
                done = exitMenue();
                break;
                
            default:
                System.out.println(selection + " is not a valid selection try again");
                break;
            case 1:
                createPlant();
                break;
                
            case 2:
                listPlants();
                break;
                
            case 3:
                selectPlant();
                break;
                
            case 4:
                updatePlantDetails();
                break;
                
            case 5:
                deletePlant();
                
            case 6:
                addState();
              
            case 7:
              addPlantDate();
                
            }
            
            

        } catch (Exception e) {
            System.out.println("\nError: " + e + " Try again.");
        }
    }
}
  
  private void addPlantDate() {
    // TODO Auto-generated method stub
    if(Objects.isNull(curPlant)) {
      System.out.println("\nPlease select a plant before adding a date");
      return;
    }
    
      Date plannedDate = getDateInput("When do you plan to plant this plant? (DD/MM/YYYY)");
      Date startDate = getDateInput("When was the plant actually planted? (DD/MM/YYYY)");
      
      PlantDate plantDate = new PlantDate();
      
      plantDate.setPlannedDate(plannedDate.toString());
      plantDate.setStartDate(startDate.toString());
      plantDate.setMatDate(add(startDate, Calendar.DAY_OF_MONTH, curPlant.getMaturityDays()).toString());
      
      
      
      PlantDate dbDate = plantService.addPlantDate(plantDate, curPlant.getPlantId());
      System.out.println("You have sucessfully planted on " + dbDate);
  }

  private Date getDateInput(String prompt) {
    // TODO Auto-generated method stub
      String input = getStringInput(prompt);
      
      if(Objects.isNull(input)) {
          return null;
      }
      
      try {
          return new SimpleDateFormat("dd/MM/yyyy").parse(input);
      } catch (ParseException e) {
          throw new DbException(input + " is not a valid date.");
      }
  }

  private void addState() {
    // TODO Auto-generated method stub
    if(Objects.isNull(curPlant)) {
      System.out.println("\nPlease select a plant before adding a state");
      return;
    }
    
    boolean responded = false;
    String state = "";
    while (!responded) {
      state = getStringInput("What state is this plant being planted in?");
      
      List<FrostDate> curFrostDates = curPlant.getFrostDate();
      
      for (int i = 0; i < curFrostDates.size(); i++) {
        if (!(state.equals(curFrostDates.get(i).getState()))) {
          responded = true;
        } else {
          System.out.println("Theis plant has already been planted in " + state);
        }
      }
    }
    
    FrostDate dbFrostDate = plantService.addFrostDate(curPlant.getPlantId(), state);
    
    System.out.println("Succesfully added " + dbFrostDate.toString());
    
    curPlant.getFrostDate().add(dbFrostDate);
    
  }

  private void deletePlant() {
    // TODO Auto-generated method stub
    listPlants();
    
    Integer plantId = getIntInput("Enter the ID of the plant to be deleted");
    
    plantService.deletePlant(plantId); 
        
    System.out.println("Plant with the plant Id of " + plantId + " has been deleted");
  }

  private void updatePlantDetails() {
    // TODO Auto-generated method stub
    if(Objects.isNull(curPlant)) {
      System.out.println("\nPlease select a plant");
      return;
  }
  
  String plantName = getStringInput("Enter the plant name [" + curPlant.getPlantName() + "]");
  Boolean isPerennial = getBooleanInput("Enter if the plant is a perennial [" + curPlant.getIsPerennial() + "]");
  Boolean doesFlower = getBooleanInput("Enter if the plant flowers [" + curPlant.getDoesFlower() + "]");
  Integer matDays = getIntInput("Enter the amount of days till maturity [" + curPlant.getMaturityDays() + "]");
  
  Plant plant = new Plant();
  
  plant.setPlantId(curPlant.getPlantId());
  plant.setPlantName(Objects.isNull(plantName) ? curPlant.getPlantName() : plantName);
  plant.setIsPerennial(Objects.isNull(isPerennial) ? curPlant.getIsPerennial() : isPerennial);
  plant.setDoesFlower(Objects.isNull(doesFlower) ? curPlant.getDoesFlower() : doesFlower);
  plant.setMaturityDays(Objects.isNull(matDays) ? curPlant.getMaturityDays() : matDays);


  plantService.modifyPlantDetails(plant);
  
  curPlant = plantService.fetchPlantById(curPlant.getPlantId());
  }

  private void selectPlant() {
    // TODO Auto-generated method stub
    listPlants();
    
    Integer plantId = getIntInput("Enter a plant ID to select a plant");
    
    
    curPlant = plantService.fetchPlantById(plantId);
    
    if(Objects.isNull(curPlant)) {
        System.out.println("\nInvalid plant ID selected");
    }
  }

  private void listPlants() {
    // TODO Auto-generated method stub
    List<Plant> plants = plantService.fetchAllPlants();
    
    plants.forEach(plant -> System.out.println("\nPlant Name: " + plant.getPlantName() +
            "\nPlant ID: " + plant.getPlantId() + "\nIs Perennial: " + plant.getIsPerennial() +
            "\nDoes Flower: " + plant.getDoesFlower() + "\nDays to Mature: " + plant.getMaturityDays()
            ));
  }

  private void printOperation() {
    // TODO Auto-generated method stub
    System.out.println("\nThese are the available selections. Press the Enter key to quit: ");
    operations.forEach(line -> System.out.println(" " + line));
    if(Objects.isNull(curPlant)) {
        System.out.println("\nYou are not working with a plant");
    } else {
        System.out.println("\nYou are working with plant: " + curPlant.toString());
    }
}
  
  private int getUserSelection() {
    // TODO Auto-generated method stub
    
    
    Integer input = getIntInput("Enter a menu selection");
    
    return Objects.isNull(input) ? -1 : input;
}
  
  private Integer getIntInput(String prompt) {
    // TODO Auto-generated method stub
    String input = getStringInput(prompt);
    
    if(Objects.isNull(input)) {
        return null;
    }
    
    try {
        return Integer.valueOf(input);
    } catch (NumberFormatException e) {
        throw new DbException(input + " is not a valid number.");
    }

  }
  
  private String getStringInput(String prompt) {
    // TODO Auto-generated method stub
    System.out.print(prompt + ": ");
    String input = in.nextLine();
    return input.isBlank() ? null : input.trim();
  }
  
  private boolean exitMenue() {
    // TODO Auto-generated method stub
    System.out.println("Bye, happy planting ;)");
    
    return true;
}
  
  private void createPlant() {
    // TODO Auto-generated method stub
    String plantName = getStringInput("Enter the plant name");
    Boolean isPerennial = getBooleanInput("Is this plant a perennial");
    Boolean doesFlower = getBooleanInput("Does this plant flower");
    Integer matDays = getIntInput("Enter the amount of days till the plant is mature");
    
    Plant plant = new Plant();
    
    plant.setPlantName(plantName);
    plant.setIsPerennial(isPerennial);
    plant.setDoesFlower(doesFlower);
    plant.setMaturityDays(matDays);
    
    Plant dbPlant = plantService.addPlant(plant);
    
    System.out.println("You have sucessfully created plant: " + dbPlant);
    
}

  private Boolean getBooleanInput(String prompt) {
    // TODO Auto-generated method stub
    prompt += " (yes/no)";
    while (true) {
      String input = getStringInput(prompt);
      System.out.println(input);
  
        if (input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y")) {
          return true;
        } else if (input.equals("no") || input.equals("No") || input.equals("n") || input.equals("No")) {
          return false;
        } else {
          System.out.println("Please respond with a yes or no.");
        }
    }

  }
  
  private static Date add(Date date, int calendarField, int amount) {
    if (date == null) {
        throw new IllegalArgumentException("The date must not be null");
    }
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(calendarField, amount);
    return c.getTime();
}

}
