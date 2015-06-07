package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.Post;

import javax.validation.Valid;

/**
 * Created by Tomas on 1. 6. 2015.
 */
public interface PostService {
    Post saveOrUpdate(@Valid Post post);
    Post findById(Long id);
    void delete(Long id);
}
