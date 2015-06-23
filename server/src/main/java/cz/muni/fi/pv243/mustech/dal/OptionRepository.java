package cz.muni.fi.pv243.mustech.dal;

import cz.muni.fi.pv243.mustech.model.Option;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Repository for Option entity
 * Created by Tomas on 30. 5. 2015.
 */
@Repository
public interface OptionRepository extends EntityRepository<Option, Long> {
}
