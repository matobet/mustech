package cz.muni.fi.pv243.mustech;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface UserRepository extends EntityRepository<User, Integer> {
    User findByName(String name);
}
