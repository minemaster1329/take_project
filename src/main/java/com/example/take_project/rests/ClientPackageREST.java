package com.example.take_project.rests;

import com.example.take_project.models.ClientPackage;
import com.example.take_project.services.ClientPackageServiceInterface;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/clientPackage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientPackageREST {
    @EJB
    ClientPackageServiceInterface clientPackageService;

    @GET
    @Path("/getAll")
    public List<ClientPackage> getAll(){
        return clientPackageService.getAll();
    }

    @GET
    @Path("/id/{id}")
    public ClientPackage getById(@PathParam("id") Long id){
        return clientPackageService.getById(id);
    }

    @POST
    @Path("/addNew")
    public void addNew(ClientPackage clientPackage){
        clientPackage.setId(null);
        clientPackageService.addNew(clientPackage);
    }

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") Long id){
        clientPackageService.delete(id);
    }

    @PUT
    @Path("/update")
    public void update(ClientPackage cp){
        clientPackageService.update(cp);
    }
}
