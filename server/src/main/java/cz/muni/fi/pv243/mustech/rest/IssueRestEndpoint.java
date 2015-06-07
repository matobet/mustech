package cz.muni.fi.pv243.mustech.rest;

import com.fasterxml.jackson.annotation.JsonRootName;
import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Tomas on 26. 5. 2015.
 */
@Path("/issues")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class IssueRestEndpoint {
    private final Logger log = LoggerFactory.getLogger(IssueRestEndpoint.class);

    @Inject
    private IssueService issueService;

    @GET
    public Issues getAll() {
        return new Issues(issueService.findAll());
    }

    @GET
    @Path("/{id}")
    public Issue getById(@PathParam("id") Long id)
    {
        log.info("Getting: " + id);
        return issueService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Issue save(@Valid Issue issue)
    {
        issue.setId(null);
        return issueService.saveOrUpdate(issue);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") Long id, @Valid Issue issue)
    {
        issue.setId(id);
        issueService.saveOrUpdate(issue);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id)
    {
        issueService.delete(id);
    }

    @JsonRootName("issues")
    class Issues extends ArrayList<Issue> {
        public Issues(Collection<Issue> issues) {
            addAll(issues);
        }
    }
}
