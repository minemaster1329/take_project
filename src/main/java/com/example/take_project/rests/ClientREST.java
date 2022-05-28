package com.example.take_project.rests;

import com.example.take_project.dto.exception.ExceptionDto;
import com.example.take_project.models.Client;
import com.example.take_project.services.ClientServiceInterface;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientREST {
    @EJB
    ClientServiceInterface clientServiceInterface;

    @GET
    @Path("/getall")
    public Response getAll() {
        List<Client> clients = clientServiceInterface.getAll();
        return Response.ok(clients).build();
    }

    @GET
    @Path("/id/{id}")
    public Response getById(@PathParam("id") Long id) {
        Client client = clientServiceInterface.getById(id);
        if (client == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(client).build();
    }

    @POST
    @Path("/addnew")
    public Response addNew(Client client) {
        if (client == null) return Response.status(Response.Status.BAD_REQUEST).build();
        try {
            client.setId(null);
            clientServiceInterface.addNew(client);
            return Response.ok().build();
        }
        catch (Exception e){
            return Response.serverError().entity(new ExceptionDto(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id){
        if (!clientServiceInterface.checkIfEntityWithIdExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        clientServiceInterface.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    public Response update(Client client){
        if (client == null) return Response.status(Response.Status.BAD_REQUEST).build();
        if (!clientServiceInterface.checkIfEntityWithIdExists(client.getId())) return Response.status(Response.Status.NOT_FOUND).build();
        clientServiceInterface.update(client);
        return Response.ok().build();
    }
}
