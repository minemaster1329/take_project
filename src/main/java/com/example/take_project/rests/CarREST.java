package com.example.take_project.rests;

import com.example.take_project.models.Car;
import com.example.take_project.services.CarServiceInterface;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/car")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarREST {
    @EJB
    CarServiceInterface carService;

    @GET
    @Path("/getAll")
    public List<Car> getAll(){
        return carService.getAll();
    }

    @GET
    @Path("/id/{id}")
    public Car getById(@PathParam("id") Long id){
        return carService.getById(id);
    }

    @POST
    @Path("/addNew")
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
