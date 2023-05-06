package plantFrost.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

//This is the chapter heading
@OpenAPIDefinition(info = @Info(title = "Delete Plant Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")
})
@RequestMapping("/deleteplant")
public interface DeletePlantController {

  // @formatter:off
  @Operation(
      summary = "Delete a plant",
      description = "Delete a plant by plant id",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "The plant is deleted", 
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
              description = "The plant id i.e. '2'")
      }
  )
  @DeleteMapping
  @ResponseStatus(code = HttpStatus.OK)
  void deletePlant(@RequestParam(required = true) Integer plantId);
  // @formatter:on

  
}
