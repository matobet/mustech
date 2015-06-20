package cz.muni.fi.pv243.mustech.dal;

import cz.muni.fi.pv243.mustech.model.User;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface UserRepository extends EntityRepository<User, Long> {
    User findByEmail(String email);
}
