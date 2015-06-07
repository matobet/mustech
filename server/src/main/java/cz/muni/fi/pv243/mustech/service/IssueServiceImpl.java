package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.IssueRepository;
import cz.muni.fi.pv243.mustech.model.Issue;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Tomas on 26. 5. 2015.
 */
@Named
@Transactional
public class IssueServiceImpl extends BaseService implements IssueService {
    @Inject
    private IssueRepository issueRepository;

    @Override
    public Issue saveOrUpdate(@Valid Issue issue) {
        issue.setCreatedAt(new Date());
        issue.setCreatedBy(getCurrentUser());

        return issueRepository.save(issue);
    }

    @Override
    public Issue findById(Long id)
    {
        return issueRepository.findBy(id);
    }

    @Override
    public void delete(Long id) {
        issueRepository.remove(issueRepository.findBy(id));
    }

    @Override
    public List<Issue> findAll() {
        return issueRepository.findAll();
    }
}
