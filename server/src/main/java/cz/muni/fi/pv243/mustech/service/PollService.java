package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.PollRepository;
import cz.muni.fi.pv243.mustech.model.Poll;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * @author Tomas
 */
@Named
@Transactional
public class PollService extends AbstractGenericService<Poll, PollRepository> {

    @Inject
    private PollRepository pollRepository;

    public PollService() {
        super(Poll.class);
    }

    @Override
    protected PollRepository getRepository() {
        return pollRepository;
    }
}
