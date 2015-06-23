package cz.muni.fi.pv243.mustech.dal;

import cz.muni.fi.pv243.mustech.model.Issue;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Repository for Issue entity
 * @author Tomas
 */
@Repository
public interface IssueRepository extends EntityRepository<Issue, Long> {

}
