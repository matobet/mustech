package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.PostRepository;
import cz.muni.fi.pv243.mustech.model.Post;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * @author Tomas
 */
@Named
@Transactional
public class PostService extends AbstractGenericService<Post, PostRepository> {

    @Inject
    private PostRepository postRepository;

    public PostService() {
        super(Post.class);
    }

    @Override
    protected PostRepository getRepository() {
        return postRepository;
    }
}
