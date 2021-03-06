package cz.muni.fi.pv243.mustech.rest;

import cz.muni.fi.pv243.mustech.model.Answer;
import cz.muni.fi.pv243.mustech.service.AnswerService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Rest endpoint for Answer entity
 * Created by Tomas on 1. 6. 2015.
 */
@RequestScoped
@Path("/answers")
@Produces(MediaType.APPLICATION_JSON)
public class AnswerRestEndpoint {
    @Inject
    private AnswerService answerService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin","user"})
    public Answer getById(@PathParam("id") Long id)
    {
        return answerService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user"})
    public void save(@Valid Answer answer)
    {
        answer.setId(null);
        answerService.saveOrUpdate(answer);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user"})
    public void update(@PathParam("id") Long id, @Valid Answer answer)
    {
        answer.setId(id);
        answerService.saveOrUpdate(answer);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin","user"})
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id)
    {
        answerService.delete(id);
    }
}
