package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.PostRepository;
import cz.muni.fi.pv243.mustech.model.Post;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Post service implementing and initializing generic service
 * @author Tomas
 */
@Service
public class PostService extends AbstractGenericService<Post, PostRepository> implements PrincipalChecker<Post> {

    @Inject
    private Event<Post> postEvent;

    @Override
    public void saveOrUpdate(Post post) {
        super.saveOrUpdate(post);

        postEvent.fire(post);
    }

    @Override
    public boolean canAccess(String principalName, Post post) {
        return post.getUser().getEmail().equals(principalName) || post.getIssue().getCreatedBy().getEmail().equals(principalName);
    }
}
