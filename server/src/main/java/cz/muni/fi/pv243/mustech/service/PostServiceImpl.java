package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.PostRepository;
import cz.muni.fi.pv243.mustech.model.Post;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Tomas on 1. 6. 2015.
 */
@Named
@Transactional
public class PostServiceImpl implements PostService {
    @Inject
    private PostRepository postRepository;

    @Override
    public Post saveOrUpdate(@Valid Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findBy(id);
    }

    @Override
    public void delete(Long id) {
        postRepository.remove(postRepository.findBy(id));
    }
}
