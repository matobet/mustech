package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.IssueRepository;
import cz.muni.fi.pv243.mustech.dal.UserRepository;
import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.User;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Tomas
 */
@Named
@Transactional
public class IssueService extends AbstractGenericService<Issue, IssueRepository> implements PrincipalChecker<Issue> {

    @Inject
    private IssueRepository issueRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private Event<Issue> issueEvent;

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
    public void saveOrUpdate(Issue issue) {
        super.saveOrUpdate(issue);

        issueEvent.fire(issue);
    }

    public void addConcernedUser(Long issueId, Long userId){
        Issue issue = issueRepository.findBy(issueId);
        User user = userRepository.findById(userId);

        issue.getConcernedUsers().add(user);
        user.getIssues().add(issue);
        issueRepository.save(issue);
        userRepository.save(user);
    }

    public void removeConcernedUser(Long issueId, Long userId){
        Issue issue = issueRepository.findBy(issueId);
        User user = userRepository.findById(userId);
        
        issue.getConcernedUsers().remove(user);
        user.getIssues().remove(issue);
        issueRepository.save(issue);
        userRepository.save(user);
    }
}
