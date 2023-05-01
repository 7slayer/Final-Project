package plantFrost;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import plantFrost.entity.*;
import plantFrost.service.*;
import java.util.Scanner;
import plantFrost.exception.*;
public class PlantFrostApp {
  
  private Scanner in = new Scanner(System.in);
  
  //  @formatter: off
  private List<String> operations = List.of(
      "1) Add a plant",
      "2) List of plants",
      "3) Select a plant",
      "4) Update plant details",
      "5) Delete a plant"
  );
  //  @formatter: on
  
  Plant curPlant = null;
  
  private PlantFrostService plantService = new PlantFrostService();
  

  public static void main(String[] args) {
    // TODO Auto-generated method stub
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
                //listPlants();
                break;
                
            case 3:
                //selectPlant();
                break;
                
            case 4:
                //updatePlantDetails();
                break;
                
            case 5:
                //1deletePlant();
                
            }
            
            

        } catch (Exception e) {
            System.out.println("\nError: " + e + " Try again.");
        }
    }
}
  
  private void printOperation() {
    // TODO Auto-generated method stub
    System.out.println("\nThese are the available selections. Press the Enter key to quit: ");
    operations.forEach(line -> System.out.println(" " + line));
    if(Objects.isNull(curPlant)) {
        System.out.println("\nYou are not working with a plant");
    } else {
        System.out.println("\nYou are working with plant: " + curPlant);
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
    plant.setMatDays(matDays);
    
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

}
