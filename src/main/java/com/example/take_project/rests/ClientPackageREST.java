package com.example.take_project.rests;

import com.example.take_project.models.ClientPackage;
import com.example.take_project.services.ClientPackageServiceInterface;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientPackage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientPackageREST {
    @EJB
    ClientPackageServiceInterface clientPackageService;

    @GET
    @Path("/getall")
    public List<ClientPackage> getAll(){
        return clientPackageService.getAll();
    }

    @GET
    @Path("/id/{id}")
    public Response getById(@PathParam("id") Long id){
        ClientPackage cp = clientPackageService.getById(id);
        if (cp == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(cp).build();
    }

    @POST
    @Path("/addnew")
    public Response addNew(ClientPackage clientPackage){
        clientPackage.setId(null);
        clientPackageService.addNew(clientPackage);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        if (!clientPackageService.checkIfEntityWithIdExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        clientPackageService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    public Response update(ClientPackage cp){
        if (!clientPackageService.checkIfEntityWithIdExists(cp.getId())) return Response.status(Response.Status.NOT_FOUND).build();
        clientPackageService.update(cp);
        return Response.ok().build();
    }
}
