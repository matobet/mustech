package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.Poll;

import javax.validation.Valid;

/**
 * Created by Tomas on 1. 6. 2015.
 */
public interface PollService {
    Poll saveOrUpdate(@Valid Poll poll);
    Poll findById(Long id);
    void delete(Long id);
}
