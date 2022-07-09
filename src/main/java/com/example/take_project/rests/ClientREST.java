package com.example.take_project.rests;

import com.example.take_project.dto.clientpackage.NewClientPackageDto;
import com.example.take_project.dto.clientpackage.UpdatedClientPackageDto;
import com.example.take_project.dto.exception.ExceptionDto;
import com.example.take_project.models.Client;
import com.example.take_project.models.ClientPackage;
import com.example.take_project.services.ClientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
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
    @Operation(summary = "Returns all clients")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if request completed successfully", content = @Content(schema = @Schema(implementation = Client[].class)))
    })
    public Response getAll() {
        List<Client> clients = clientServiceInterface.getAll();
        return Response.ok(clients).build();
    }

    @GET
    @Path("/id/{id}")
    @Operation(summary = "Returns client with specified id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if client with specified id was found", content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "404", description = "Returned if client with specified id not found")
    })
    public Response getById(@PathParam("id") Long id) {
        Client client = clientServiceInterface.getById(id);
        if (client == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(client).build();
    }

    @POST
    @Path("/addnew")
    @Operation(summary = "Adds new client to database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if client was added successfully"),
            @ApiResponse(responseCode = "400", description = "Returned if client was null"),
            @ApiResponse(responseCode = "500", description = "Returned if something went wrong when adding client")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = Client.class)))
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
    @Operation(summary = "Removes client from database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if client was successfully removed"),
            @ApiResponse(responseCode = "404", description = "Returned if client with specified id was not found")
    })
    public Response delete(@PathParam("id") Long id){
        if (!clientServiceInterface.checkIfEntityWithIdExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        clientServiceInterface.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    @Operation(summary = "Updates client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if client was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Returned if client was null or something went wrong"),
            @ApiResponse(responseCode = "404", description = "Returned if client was not found in database")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = Client.class)))
    public Response update(Client client){
        if (client == null) return Response.status(Response.Status.BAD_REQUEST).build();
        if (!clientServiceInterface.checkIfEntityWithIdExists(client.getId())) return Response.status(Response.Status.NOT_FOUND).build();
        clientServiceInterface.update(client);
        return Response.ok().build();
    }
}
