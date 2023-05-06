package plantFrost.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

// This is the chapter heading
@OpenAPIDefinition(info = @Info(title = "Update Plant Service"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
@RequestMapping("/updateplant")
public interface UpdatePlantController {

  // @formatter:off
  @Operation(
      summary = "Update a plant",
      description = "adds a plant and returns the added plant",
      responses = {
          @ApiResponse(
              responseCode = "201", 
              description = "The plant has been updated", 
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
      },
      parameters = {
          @Parameter(name = "plantId", 
              allowEmptyValue = false, 
              required = true, 
              description = "The id of a plant i.e. '4'"),
          @Parameter(name = "plantName", 
              allowEmptyValue = false, 
              required = true, 
              description = "The name of a plant i.e. 'Carrot'"),
          @Parameter(name = "isPerennial", 
              allowEmptyValue = false, 
              required = true, 
              description = "Does the plant come back every year i.e. 'true/false'"),
          @Parameter(name = "doesFlower", 
              allowEmptyValue = false, 
              required = true, 
              description = "Does this plant flower i.e. 'true/false'"),
          @Parameter(name = "maturityDays", 
              allowEmptyValue = false, 
              required = true, 
              description = "number of days till maturity i.e. '112'"),
      }
  )
  @PutMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  void updatePlant(@RequestParam(required = true) Integer plantId, @RequestParam(required = true) String plantNaem,@RequestParam(required = true) Boolean isPerennial,@RequestParam(required = true) Boolean doesFlower,@RequestParam(required = true) Integer maturityDays);
  // @formatter:on
}
