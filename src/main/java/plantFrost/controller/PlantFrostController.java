package plantFrost.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import plantFrost.entity.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

//This is the chapter heading
@OpenAPIDefinition(info = @Info(title = "Plant Frost Date Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")
})
@RequestMapping("/plants")
public interface PlantFrostController {

  // @formatter:off
  @Operation(
      summary = "Return a list of plants",
      description = "Returns a list of plants",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "A list of plants is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Plant.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No plants were found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred", 
              content = @Content(mediaType = "application/json"))
      }
  )
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Plant> fetchPlants();
  // @formatter:on

  
}
