package cz.muni.fi.pv243.mustech.dal;

import cz.muni.fi.pv243.mustech.model.Option;
import cz.muni.fi.pv243.mustech.model.Post;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Repository for Post entity
 * Created by Tomas on 30. 5. 2015.
 */
@Repository
public interface PostRepository extends EntityRepository<Post, Long> {
}
