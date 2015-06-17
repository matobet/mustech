package cz.muni.fi.pv243.mustech.rest;

import cz.muni.fi.pv243.mustech.model.Post;
import cz.muni.fi.pv243.mustech.service.PostService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Tomas on 1. 6. 2015.
 */
@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PostRestEndpoint {
    @Inject
    private PostService postService;

    @GET
    @Path("/{id}")
    public Post getById(@PathParam("id") Long id)
    {
        return postService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(@Valid Post post)
    {
        post.setId(null);
        postService.saveOrUpdate(post);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") Long id, @Valid Post post)
    {
        post.setId(id);
        postService.saveOrUpdate(post);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id)
    {
        postService.delete(id);
    }
}
