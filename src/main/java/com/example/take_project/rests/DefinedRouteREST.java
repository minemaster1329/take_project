package com.example.take_project.rests;

import com.example.take_project.models.DefinedRoute;
import com.example.take_project.services.DefinedRouteServiceInterface;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/definedRoute")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DefinedRouteREST {
    @EJB
    DefinedRouteServiceInterface definedRouteServiceInterface;

    @GET
    @Path("/getall")
    public List<DefinedRoute> getAll(){
        return definedRouteServiceInterface.getAll();
    }

    @GET
    @Path("/id/{id}")
    public DefinedRoute getById(@PathParam("id") Long id){
        return definedRouteServiceInterface.getById(id);
    }

    @POST
    @Path("/addnew")
    public void addNew(DefinedRoute definedRoute){
        definedRouteServiceInterface.addNew(definedRoute);
    }

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") Long id) {
        definedRouteServiceInterface.delete(id);
    }

    @PUT
    @Path("/update")
    public void update(DefinedRoute definedRoute){
        definedRouteServiceInterface.update(definedRoute);
    }
}
