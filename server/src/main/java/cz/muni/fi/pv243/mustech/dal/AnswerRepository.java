package cz.muni.fi.pv243.mustech.dal;

import cz.muni.fi.pv243.mustech.model.Answer;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Created by Tomas on 30. 5. 2015.
 */
@Repository
public interface AnswerRepository extends EntityRepository<Answer, Long>
{
}
