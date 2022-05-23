package com.example.take_project.rests;

import com.example.take_project.models.Route;
import com.example.take_project.services.RouteServiceInterface;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/route")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteREST {
    @EJB
    RouteServiceInterface routeServiceInterface;

    @GET
    @Path("/getAll")
    public List<Route> getAll(){
        return routeServiceInterface.getAll();
    }

    @GET
    @Path("/id/{id}")
    public Route getById(@PathParam("id") Long id){
        return routeServiceInterface.getById(id);
    }

    @POST
    @Path("/addNew")
    public void addNew(Route route){
        route.setId(null);
        routeServiceInterface.addNew(route);
    }

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") Long id){
        routeServiceInterface.delete(id);
    }

    @PUT
    @Path("/update")
    public void update(Route route){
        routeServiceInterface.update(route);
    }
}
