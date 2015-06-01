package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.IssueRepository;
import cz.muni.fi.pv243.mustech.model.Issue;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Tomas on 26. 5. 2015.
 */
@Named
@Transactional
public class IssueServiceImpl implements IssueService {
    @Inject
    private IssueRepository issueRepository;

    @Override
    public Issue saveOrUpdate(@Valid Issue issue)
    {
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
}
