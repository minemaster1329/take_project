package com.example.take_project.rests;

import com.example.take_project.models.Car;
import com.example.take_project.services.CarService;
import com.example.take_project.services.CarServiceInterface;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.awt.*;
import java.util.List;

@Path("/car")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarREST {
    @EJB
    CarServiceInterface carService;

    @GET
    @Path("/getall")
    public List<Car> getAllCars(){
        return carService.getAll();
    }

    @GET
    @Path("{id}")
    public Car getById(@PathParam("id") Long id){
        return carService.getById(id);
    }

    @POST
    @Path("/addnew")
    public void addNewCar(Car car){
        car.setId(null);
        carService.add(car);
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteCar(@PathParam("id") Long id){
        carService.delete(id);
    }

    @PUT
    @Path("/update")
    public void updateCar(Car car){
        carService.update(car);
    }
}
