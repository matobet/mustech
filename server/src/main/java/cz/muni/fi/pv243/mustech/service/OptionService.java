package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.OptionRepository;
import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.Option;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Option service implementing and initializing generic service
 * @author Tomas
 */
@Service
public class OptionService extends AbstractGenericService<Option, OptionRepository> {

    @Inject
    private PrincipalChecker<Issue> issuePrincipalChecker;

    @Override
    public boolean canAccess(String principalName, Option entity) {
        return issuePrincipalChecker.canAccess(principalName, entity.getPoll().getIssue());
    }
}
