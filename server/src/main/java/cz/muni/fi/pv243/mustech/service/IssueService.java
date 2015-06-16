package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.IssueRepository;
import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.util.UserNotification;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Collections;

/**
 * @author Tomas
 */
@Named
@Transactional
public class IssueService extends AbstractGenericService<Issue, IssueRepository> implements PrincipalChecker<Issue> {

    @Inject
    private IssueRepository issueRepository;

    @Inject
    private Event<UserNotification> issueEvent;

    public IssueService() {
        super(Issue.class);
    }

    @Override
    protected IssueRepository getRepository() {
        return issueRepository;
    }

    @Override
    public boolean canAccess(String principalName, Issue issue) {
        return issue.getCreatedBy().getEmail().equals(principalName);
    }

    @Override
    public Issue saveOrUpdate(Issue issue) {
        issueEvent.fire(new UserNotification(Collections.emptyList(),issue));

        return super.saveOrUpdate(issue);
    }
}
