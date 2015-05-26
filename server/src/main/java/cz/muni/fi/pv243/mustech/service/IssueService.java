package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.Issue;

/**
 * Created by Tomas on 26. 5. 2015.
 */

/**
 * Issue related services.
 */
public interface IssueService {
    Issue saveOrUpdate(Issue issue);
    Issue findById(Long id);
}
