package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.PollRepository;
import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.Poll;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Poll service implementing and initializing generic service
 * @author Tomas
 */
@Named
@Transactional
public class PollService extends AbstractGenericService<Poll, PollRepository> {

    @Inject
    private PrincipalChecker<Issue> issuePrincipalChecker;

    @Override
    public boolean canAccess(String principalName, Poll entity) {
        return issuePrincipalChecker.canAccess(principalName, entity.getIssue());
    }
}
