package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.IssueRepository;
import cz.muni.fi.pv243.mustech.model.Issue;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * @author Tomas
 */
@Named
@Transactional
public class IssueService extends AbstractGenericService<Issue, IssueRepository> {

    @Inject
    private IssueRepository issueRepository;

    public IssueService() {
        super(Issue.class);
    }

    @Override
    protected IssueRepository getRepository() {
        return issueRepository;
    }
}
