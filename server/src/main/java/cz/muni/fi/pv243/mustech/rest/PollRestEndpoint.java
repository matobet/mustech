package cz.muni.fi.pv243.mustech.rest;

import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.Poll;
import cz.muni.fi.pv243.mustech.service.PollService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Tomas on 1. 6. 2015.
 */
@Path("/polls")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PollRestEndpoint {
    @Inject
    private PollService pollService;

    @GET
    @Path("/{id}")
    public Poll getById(@PathParam("id") Long id)
    {
        return pollService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Poll save(@Valid Poll poll)
    {
        poll.setId(null);
        return pollService.saveOrUpdate(poll);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") Long id, @Valid Poll poll)
    {
        poll.setId(id);
        pollService.saveOrUpdate(poll);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id)
    {
        pollService.delete(id);
    }
}
