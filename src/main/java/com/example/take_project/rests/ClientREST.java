package com.example.take_project.rests;

import com.example.take_project.models.Client;
import com.example.take_project.services.ClientServiceInterface;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientREST {
    @EJB
    ClientServiceInterface clientServiceInterface;

    @GET
    @Path("/getall")
    public List<Client> getAll() {
        return clientServiceInterface.getAll();
    }

    @GET
    @Path("/id/{id}")
    public Client getById(@PathParam("id") Long id) {
        return clientServiceInterface.getById(id);
    }

    @POST
    @Path("/addnew")
    public void addNew(Client client) {
        client.setId(null);
        clientServiceInterface.addNew(client);
    }

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") Long id){
        clientServiceInterface.delete(id);
    }

    @PUT
    @Path("/update")
    public void update(Client client){
        clientServiceInterface.update(client);
    }
}
