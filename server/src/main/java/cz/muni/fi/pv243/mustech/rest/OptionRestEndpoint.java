package cz.muni.fi.pv243.mustech.rest;

import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.Option;
import cz.muni.fi.pv243.mustech.service.OptionService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Tomas on 1. 6. 2015.
 */
@Path("/options")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class OptionRestEndpoint {
    @Inject
    private OptionService optionService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin","user"})
    public Option getById(@PathParam("id") Long id)
    {
        return optionService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user"})
    public void save(@Valid Option option)
    {
        option.setId(null);
        optionService.saveOrUpdate(option);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user"})
    public void update(@PathParam("id") Long id, @Valid Option option)
    {
        option.setId(id);
        optionService.saveOrUpdate(option);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user"})
    public void delete(@PathParam("id") Long id)
    {
        optionService.delete(id);
    }
}
