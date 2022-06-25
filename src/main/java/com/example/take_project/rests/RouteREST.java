package com.example.take_project.rests;

import com.example.take_project.dto.clientpackage.NewRouteCarsDefinedRouteDto;
import com.example.take_project.models.Route;
import com.example.take_project.services.RouteServiceInterface;
import jakarta.ejb.EJB;
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
    public Response getAll(){
        List<Route> routes = routeServiceInterface.getAll();
        return Response.ok(routes).build();
    }

    @GET
    @Path("/id/{id}")
    public Response getById(@PathParam("id") Long id){
        Route route = routeServiceInterface.getById(id);
        if (route == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(route).build();
    }

    @POST
    @Path("/addnew")
    public Response addNew(NewRouteCarsDefinedRouteDto newRouteCarsDefinedRouteDto) throws EntityNotFoundException {
        newRouteCarsDefinedRouteDto.setId(null);
        routeServiceInterface.addNew(newRouteCarsDefinedRouteDto);
        return Response.ok().build();
    }




    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        if (!routeServiceInterface.checkIfEntityWithIdExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        routeServiceInterface.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    public Response update(Route route){
        if (route == null) return Response.status(Response.Status.BAD_REQUEST).build();
        if (!routeServiceInterface.checkIfEntityWithIdExists(route.getId())) return Response.status(Response.Status.NOT_FOUND).build();
        routeServiceInterface.update(route);
        return Response.ok().build();
    }

    @GET
    @Path("/forcar/{carId}/")
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



}
