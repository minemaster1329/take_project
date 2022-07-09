package com.example.take_project.rests;

import com.example.take_project.models.Car;
import com.example.take_project.services.CarServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;
import java.util.List;

@Path("/car")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarREST {
    @EJB
    CarServiceInterface carService;

    @Context
    SecurityContext securityContext;

    @GET
    @Path("/getall")
    @Operation(summary = "Returns all cars")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if request completed successfully", content = @Content(schema = @Schema(implementation = Car[].class)))
    })
    @Tags({
            @Tag(name = "car"),
            @Tag(name = "getall")
    })
    public Response getAll(){
//        Principal principal = securityContext.getUserPrincipal();
//        String userName = principal.getName();
        List<Car> cars = carService.getAll();
        return Response.ok(cars).build();
    }

    @GET
    @Path("/id/{id}")
    @Operation(summary = "Returns car with specified id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if car with specified id found", content = @Content(schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "400", description = "Returned if id parameter was null"),
            @ApiResponse(responseCode = "404", description = "Returned if car with specified id not found")
    })
    @Tags({
            @Tag(name = "getbyid"),
            @Tag(name = "car")
    })
    public Response getById(@PathParam("id") @Parameter(description = "Car's id") Long id){
        if (id == null) return Response.status(Response.Status.BAD_REQUEST).build();
        Car c = carService.getById(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(c).build();
    }

    @POST
    @Path("/addnew")
    @Operation(summary = "Adds new car to database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if car was added successfully"),
            @ApiResponse(responseCode = "400", description = "Returned if car was null")
    })
    @Tags({
            @Tag(name = "car"),
            @Tag(name = "addnew")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = Car.class)))
    public Response addNew(@Parameter(description = "New car object", content = @Content(schema = @Schema(implementation = Car.class))) Car car){
        if (car == null) return Response.status(Response.Status.BAD_REQUEST).build();
        car.setId(null);
        carService.addNew(car);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Operation(summary = "Removes specified car from database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if car was successfully removed"),
            @ApiResponse(responseCode = "400", description = "Returned if id was null"),
            @ApiResponse(responseCode = "404", description = "Returned if car with specified id was not found")
    })
    public Response delete(@PathParam("id") @Parameter(description = "Car's id") Long id){
        if (id == null) return Response.status(Response.Status.BAD_REQUEST).build();
        if (!carService.checkIfEntityWithIdExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        carService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    @Operation(summary = "Updates specified car")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if car was successfully updated"),
            @ApiResponse(responseCode = "400", description = "Returned if car was null"),
            @ApiResponse(responseCode = "404", description = "Returned if car was not found in database")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = Car.class)))
    public Response update(@Parameter(description = "Updated car object", content = @Content(schema = @Schema(implementation = Car.class))) Car car){
        if (car == null) return Response.status(Response.Status.BAD_REQUEST).build();
        if (!carService.checkIfEntityWithIdExists(car.getId())) return Response.status(Response.Status.NOT_FOUND).build();
        carService.update(car);
        return Response.ok().build();
    }
}
