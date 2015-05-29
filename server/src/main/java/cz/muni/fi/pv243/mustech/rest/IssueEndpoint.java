package cz.muni.fi.pv243.mustech.rest;

import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Tomas on 26. 5. 2015.
 */
@Path("/issues")
@Produces(MediaType.APPLICATION_JSON)
public class IssueEndpoint {
    private final Logger log = LoggerFactory.getLogger(IssueEndpoint.class);

    @Inject
    private IssueService issueService;

    @GET
    @Path("/{id}")
    public Issue getById(@PathParam("id") Long id)
    {
        log.info("Getting: " + id);
        return issueService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Issue save(Issue issue)
    {
        return issueService.saveOrUpdate(issue);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Issue issue)
    {
        issueService.saveOrUpdate(issue);
    }
}
