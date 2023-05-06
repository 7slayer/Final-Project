package plantFrost.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
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
@OpenAPIDefinition(info = @Info(title = "Plant Frost Date Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")
})
@RequestMapping("/addstate")
public interface AddFrostDateController {

  // @formatter:off
  @Operation(
      summary = "Add a frost dates to plant",
      description = "adds frost dates to plant by plant id and state",
      responses = {
          @ApiResponse(
              responseCode = "201", 
              description = "The added frost dates are returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = FrostDate.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No states were found with the input criteria", 
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
              description = "The plant id i.e. '2'"),
          @Parameter(name = "state", 
              allowEmptyValue = false, 
              required = true, 
              description = "The state to be added i.e. 'New York'")
      }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  FrostDate addFrostDate(@RequestParam(required = true) Integer plantId, @RequestParam(required = true) String state);
  // @formatter:on

  
}
