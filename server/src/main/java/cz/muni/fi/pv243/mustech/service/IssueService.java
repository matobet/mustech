package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.IssueRepository;
import cz.muni.fi.pv243.mustech.dal.UserRepository;
import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.User;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Issue service implementing and initializing generic service and own methods
 * @author Tomas
 */
@Service
public class IssueService extends AbstractGenericService<Issue, IssueRepository> implements PrincipalChecker<Issue> {

    @Inject
    private UserRepository userRepository;

    @Inject
    private Event<Issue> issueEvent;

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
        Issue issue = repository.findBy(issueId);
        User user = userRepository.findBy(userId);

        issue.getConcernedUsers().add(user);
        user.getIssues().add(issue);
        repository.save(issue);
        userRepository.save(user);
    }

    public void removeConcernedUser(Long issueId, Long userId){
        Issue issue = repository.findBy(issueId);
        User user = userRepository.findBy(userId);

        issue.getConcernedUsers().remove(user);
        user.getIssues().remove(issue);
        repository.save(issue);
        userRepository.save(user);
    }
}
