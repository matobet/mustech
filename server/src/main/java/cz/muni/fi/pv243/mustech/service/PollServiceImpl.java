package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.PollRepository;
import cz.muni.fi.pv243.mustech.model.Poll;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Tomas on 1. 6. 2015.
 */
@Named
@Transactional
public class PollServiceImpl implements PollService {
    @Inject
    private PollRepository pollRepository;

    @Override
    public Poll saveOrUpdate(@Valid Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public Poll findById(Long id) {
        return pollRepository.findBy(id);
    }

    @Override
    public void delete(Long id) {
        pollRepository.remove(pollRepository.findBy(id));
    }
}
