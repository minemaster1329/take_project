package com.example.take_project.rests;

import com.example.take_project.dto.clientpackage.NewRouteCarsDefinedRouteDto;
import com.example.take_project.models.Client;
import com.example.take_project.models.DefinedRoute;
import com.example.take_project.models.Route;
import com.example.take_project.otherstuff.exceptions.EntityNotFoundException;
import com.example.take_project.services.RouteServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.example.take_project.otherstuff.exceptions.EntityNotFoundException;
import java.util.List;
import com.example.take_project.models.Car;
@Path("/route")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteREST {
    @EJB
    RouteServiceInterface routeServiceInterface;

    @GET
    @Path("/getall")
    @Operation(summary = "Returns route with specified ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Returned if the client's packages were retrieved.",
                    content = @Content(schema = @Schema(implementation = DefinedRoute.class)))
    })
    public Response getAll(){
        List<Route> routes = routeServiceInterface.getAll();
        return Response.ok(routes).build();
    }

    @GET
    @Path("/id/{id}")
    @Operation(summary = "Returns client with specified id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if route with specified id was found", content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "404", description = "Returned if route with specified id not found")
    })
    public Response getById(@PathParam("id") Long id){
        Route route = routeServiceInterface.getById(id);
        if (route == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(route).build();
    }

    @POST
    @Path("/addnew")
    @Operation(summary = "Adds new client to database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if route was added successfully"),
            @ApiResponse(responseCode = "500", description = "Returned if something went wrong when adding client")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = NewRouteCarsDefinedRouteDto.class)))
    public Response addNew(NewRouteCarsDefinedRouteDto newRouteCarsDefinedRouteDto) throws EntityNotFoundException {
        newRouteCarsDefinedRouteDto.setId(null);
        routeServiceInterface.addNew(newRouteCarsDefinedRouteDto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Operation(summary = "Removes client from database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if route was successfully removed"),
            @ApiResponse(responseCode = "404", description = "Returned if route with specified id was not found")
    })
    public Response delete(@PathParam("id") Long id){
        if (!routeServiceInterface.checkIfEntityWithIdExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        routeServiceInterface.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    @Operation(summary = "Updates client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if route was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Returned if route was null or something went wrong"),
            @ApiResponse(responseCode = "404", description = "Returned if route was not found in database")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = Route.class)))
    public Response update(Route route){
        if (route == null) return Response.status(Response.Status.BAD_REQUEST).build();
        if (!routeServiceInterface.checkIfEntityWithIdExists(route.getId())) return Response.status(Response.Status.NOT_FOUND).build();
        routeServiceInterface.update(route);
        return Response.ok().build();
    }

    @GET
    @Path("/forcar/{carId}/")
    @Operation(summary = "Returns route for specified car")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if car with specified id was not found"),
            @ApiResponse(responseCode = "404", description = "Returned if route was not found in database")
    })
    public Response getRoutesForCar(@PathParam("carId") Long carId){
        if (carId == null) return Response.status(Response.Status.BAD_REQUEST).build();
        try {
            List<Route> routes = routeServiceInterface.getRoutesForCar(carId);
            return Response.ok(routes).build();
        }
        catch (EntityNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/forcarinday/{carId}/{day}/{month}/{year}")
    @Operation(summary = "Returns route for specified car")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if car with specified id was not found"),
            @ApiResponse(responseCode = "404", description = "Returned if route was not found in database"),
            @ApiResponse(responseCode = "400", description = "Returned if one of parameter was null")
    })
    public Response getRoutesForCarInSpecifiedDay(@PathParam("carId") Long carId, @PathParam("day") Integer day, @PathParam("month") Integer month, @PathParam("year") Integer year){
        System.out.println("Entered endpoint");
        if (carId == null || day == null || month == null || year == null) return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            List<Route> routes = routeServiceInterface.getRoutesForCarInSpecifiedDay(carId, day, month, year);
            return Response.ok(routes).build();
        }
        catch (EntityNotFoundException e){
            System.out.println("Car not found");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
