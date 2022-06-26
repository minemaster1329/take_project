package com.example.take_project.rests;

import com.example.take_project.dto.clientpackage.NewClientPackageDto;
import com.example.take_project.dto.clientpackage.UpdatedClientPackageDto;
import com.example.take_project.dto.exception.ExceptionDto;
import com.example.take_project.models.Client;
import com.example.take_project.models.ClientPackage;
import com.example.take_project.otherstuff.exceptions.EntityNotFoundException;
import com.example.take_project.services.ClientPackageServiceInterface;
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

@Path("/clientPackage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientPackageREST {
    @EJB
    ClientPackageServiceInterface clientPackageService;

    @GET
    @Path("/getall")
    @Operation(summary = "Returns all packages")
    @ApiResponse(responseCode = "200", description = "Returned if request completed successfully", content = @Content(schema = @Schema(implementation = ClientPackage[].class)))
    @Tags({
            @Tag(name = "clientpackage"),
            @Tag(name = "getall")
    })
    public List<ClientPackage> getAll(){
        return clientPackageService.getAll();
    }

    @GET
    @Path("/clientId/{id}")
    @Operation(summary = "Returns all packages owned by specific client.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Returned if the client's packages were retrieved.",
                    content = @Content(schema = @Schema(implementation = ClientPackage.class))),
            @ApiResponse(responseCode = "400",
                    description = "Returned if passed id was null"),
            @ApiResponse(responseCode = "404",
                    description = "Returned if specific owner was not found")
    })
    public Response getClientsPackages(@PathParam("id") Long ownerID){
        if(ownerID == null) return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            List<ClientPackage> packages = clientPackageService.getAllOwnedByClient(ownerID);
            return Response.ok(packages).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/id/{id}")
    @Operation(summary = "Returns package with specified id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if package with specified id was found", content = @Content(schema = @Schema(implementation = ClientPackage.class))),
            @ApiResponse(responseCode = "400", description = "Returned if id parameter was null"),
            @ApiResponse(responseCode = "404", description = "Returned if car with specified id not found")
    })
    @Tags({
            @Tag(name = "clientpackage"),
            @Tag(name = "getbyid")
    })
    public Response getById(@PathParam("id") Long id){
        if (id == null) return Response.status(Response.Status.BAD_REQUEST).build();
        ClientPackage cp = clientPackageService.getById(id);
        if (cp == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(cp).build();
    }

    @POST
    @Path("/addnew")
    @Operation(summary = "Adds new package to database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if package was added successfully"),
            @ApiResponse(responseCode = "400", description = "Returned if package was null"),
    })
    @Tags({
            @Tag(name = "clientpackage"),
            @Tag(name = "addnew")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = NewClientPackageDto.class)))
    public Response addNew(NewClientPackageDto clientPackage){
        if (clientPackage == null) return Response.status(Response.Status.BAD_REQUEST).build();
        try {
            clientPackageService.addNew(clientPackage);
            return Response.ok().build();
        }
        catch (EntityNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Operation(summary = "Removes package from database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if package was successfully removed"),
            @ApiResponse(responseCode = "400", description = "returned if package ID was null"),
            @ApiResponse(responseCode = "404", description = "Returned if package with specified id was not found")
    })
    public Response delete(@PathParam("id") Long id){
        if (id == null) return Response.status(Response.Status.BAD_REQUEST).build();
        if (!clientPackageService.checkIfEntityWithIdExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        clientPackageService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    @Operation(summary = "Updates package")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returned if package was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Returned if package was null or something went wrong"),
            @ApiResponse(responseCode = "404", description = "Returned if package was not found in database")
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = UpdatedClientPackageDto.class)))
    public Response update(UpdatedClientPackageDto cp){
        if (cp == null) return Response.status(Response.Status.BAD_REQUEST).build();
        if (!clientPackageService.checkIfEntityWithIdExists(cp.getId())) return Response.status(Response.Status.NOT_FOUND).build();
        try {
            clientPackageService.update(cp);
        }
        catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionDto(e.getMessage())).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/assignroute/{packageId}/{routeId}")
    public Response assignRoute(@PathParam("packageId") Long packageId, @PathParam("routeId") Long routeId){
        if (packageId == null || routeId == null) return Response.status(Response.Status.BAD_REQUEST).build();
        try {
            clientPackageService.assignRouteToPackage(packageId, routeId);
            return Response.ok().build();
        }
        catch (EntityNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/getforcarandroute/{carId}/{routeId}")
    public Response getForCarAndRoute(@PathParam("carId") Long carId, @PathParam("routeId") Long routeId){
        if (carId == null || routeId == null) return Response.status(Response.Status.BAD_REQUEST).build();
        try {
            List<ClientPackage> output = clientPackageService.getPackagesForCarAndRoute(carId, routeId);
            return Response.ok(output).build();
        }
        catch (EntityNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
