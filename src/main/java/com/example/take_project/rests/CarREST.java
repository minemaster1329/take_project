package com.example.take_project.rests;

import com.example.take_project.models.Car;
import com.example.take_project.services.CarServiceInterface;
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
    public Response getAll(){
//        Principal principal = securityContext.getUserPrincipal();
//        String userName = principal.getName();
        List<Car> cars = carService.getAll();
        return Response.ok(cars).build();
    }

    @GET
    @Path("/id/{id}")
    public Response getById(@PathParam("id") Long id){
        Car c = carService.getById(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(c).build();
    }

    @POST
    @Path("/addnew")
    public Response addNew(Car car){
        car.setId(null);
        carService.addNew(car);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        if (!carService.checkIfEntityWithIdExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        carService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    public Response update(Car car){
        if (!carService.checkIfEntityWithIdExists(car.getId())) return Response.status(Response.Status.NOT_FOUND).build();
        carService.update(car);
        return Response.ok().build();
    }
}
