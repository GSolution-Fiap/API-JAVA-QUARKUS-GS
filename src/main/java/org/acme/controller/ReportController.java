package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import org.acme.bo.ReportBO;
import org.acme.entity.ReportEntity;

@Path("/reports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReportController {

    @Inject
    private ReportBO reportBO;

    @GET
    public List<ReportEntity> listAll() {
        return reportBO.listAll();
    }

    @GET
    @Path("/email/{email}")
    public List<ReportEntity> getByEmail(@PathParam("email") String email) {
        return reportBO.getReportsByEmail(email);
    }

    @POST
    public Response create(ReportEntity entity) {
        ReportEntity created = reportBO.createReport(entity);
        URI location = URI.create("/reports/email/" + created.getEmail());
        return Response.created(location).entity(created).build();
    }

    @PUT
    @Path("/email/{email}")
    public Response updateByEmail(@PathParam("email") @Encoded String email, ReportEntity entity) {
        String decodedEmail = java.net.URLDecoder.decode(email, java.nio.charset.StandardCharsets.UTF_8);
        List<ReportEntity> updatedReports = reportBO.updateReportsByEmail(decodedEmail, entity);
        if (updatedReports.isEmpty()) {
            return buildNotFoundResponse(decodedEmail);
        }
        return Response.ok(updatedReports).build();
    }

    @DELETE
    @Path("/id/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        boolean deleted = reportBO.deleteReportById(id);
        if (!deleted) {
            String json = "{\"message\": \"Nenhum report encontrado com o id: " + id + "\"}";
            return Response.status(Response.Status.NOT_FOUND).entity(json).build();
        }
        String json = "{\"message\": \"Report de id " + id + " deletado com sucesso!\"}";
        return Response.ok(json).build();
    }

    private Response buildNotFoundResponse(String email) {
        String json = "{\"message\": \"Nenhum report encontrado com o email: " + email + "\"}";
        return Response.status(Response.Status.NOT_FOUND).entity(json).build();
    }
}
