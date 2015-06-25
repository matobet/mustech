package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.AnswerRepository;
import cz.muni.fi.pv243.mustech.model.Answer;
import cz.muni.fi.pv243.mustech.model.Issue;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Answer service implementing and initializing generic service
 * @author Tomas
 */
@Named
@Transactional
public class AnswerService extends AbstractGenericService<Answer, AnswerRepository> {

    @Inject
    private PrincipalChecker<Issue> issuePrincipalChecker;

    @Override
    public boolean canAccess(String principalName, Answer entity) {
        return issuePrincipalChecker.canAccess(principalName, entity.getPoll().getIssue());
    }
}
