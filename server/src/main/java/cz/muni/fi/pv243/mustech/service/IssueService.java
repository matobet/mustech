package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.Issue;

import javax.validation.Valid;

/**
 * Created by Tomas on 26. 5. 2015.
 */

/**
 * Issue related services.
 */
public interface IssueService {
    Issue saveOrUpdate(@Valid Issue issue);
    Issue findById(Long id);
    void delete(Long id);
}
