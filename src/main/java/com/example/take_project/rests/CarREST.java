package com.example.take_project.rests;

import com.example.take_project.models.Car;
import com.example.take_project.otherstuff.annotations.Secured;
import com.example.take_project.services.CarServiceInterface;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
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
    @Secured
    public List<Car> getAll(){
        Principal principal = securityContext.getUserPrincipal();
        String userName = principal.getName();
        return carService.getAll();
    }

    @GET
    @Path("/id/{id}")
    public Car getById(@PathParam("id") Long id){
        return carService.getById(id);
    }

    @POST
    @Path("/addnew")
    public void addNew(Car car){
        car.setId(null);
        carService.addNew(car);
    }

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") Long id){
        carService.delete(id);
    }

    @PUT
    @Path("/update")
    public void update(Car car){
        carService.update(car);
    }
}
