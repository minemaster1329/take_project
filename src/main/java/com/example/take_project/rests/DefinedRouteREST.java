package com.example.take_project.rests;

import com.example.take_project.models.Client;
import com.example.take_project.models.ClientPackage;
import com.example.take_project.models.DefinedRoute;
import com.example.take_project.services.DefinedRouteServiceInterface;
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

@Path("/definedRoute")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DefinedRouteREST {
    @EJB
    DefinedRouteServiceInterface definedRouteServiceInterface;

    @GET
    @Path("/getall")
    @Operation(summary = "Returns all defined routes")
    @ApiResponse(responseCode = "200", description = "Returned if request completed successfully", content = @Content(schema = @Schema(implementation = DefinedRoute[].class)))
    public Response getAll(){
        List<DefinedRoute> routes = definedRouteServiceInterface.getAll();
        return Response.ok(routes).build();
    }

    @GET
    @Path("/id/{id}")
    @Operation(summary = "Returns defined route with specified ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Returned if the client's packages were retrieved.",
                    content = @Content(schema = @Schema(implementation = DefinedRoute.class))),
            @ApiResponse(responseCode = "404",
                    description = "Returned if specific client was not found")
    })
    public Response getById(@PathParam("id") Long id){
        DefinedRoute definedRoute = definedRouteServiceInterface.getById(id);
        if (definedRoute == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(definedRoute).build();
    }

    @POST
    @Path("/addnew")
    @Operation(summary = "Adds new client to system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if client was added successfully"),
            @ApiResponse(responseCode = "400", description = "Returned if supplied object was null")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = DefinedRoute.class)))
    public Response addNew(DefinedRoute definedRoute){
        if (definedRoute == null) return Response.status(Response.Status.BAD_REQUEST).build();
        definedRoute.setId(null);
        definedRouteServiceInterface.addNew(definedRoute);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Operation(summary = "Removes client from system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if client was removed successfully"),
            @ApiResponse(responseCode = "404", description = "Returned if client with specified id was not found")
    })
    public Response delete(@PathParam("id") Long id) {
        if (!definedRouteServiceInterface.checkIfEntityWithIdExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        definedRouteServiceInterface.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    @Operation(summary = "Updates client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if client was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Returned if supplied object was null"),
            @ApiResponse(responseCode = "404", description = "Returned if client with specified id was not found")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = DefinedRoute.class)))
    public Response update(DefinedRoute definedRoute){
        if (definedRoute == null) return Response.status(Response.Status.BAD_REQUEST).build();
        if (!definedRouteServiceInterface.checkIfEntityWithIdExists(definedRoute.getId())) return Response.status(Response.Status.NOT_FOUND).build();
        definedRouteServiceInterface.update(definedRoute);
        return Response.ok().build();
    }
}
