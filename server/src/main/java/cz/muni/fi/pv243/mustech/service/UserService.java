package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.UserRepository;
import cz.muni.fi.pv243.mustech.model.User;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

/**
 * User service implementing and initializing generic service
 * @author Milan
 */
@Named
@Transactional
public class UserService extends AbstractGenericService<User, UserRepository> {

    public User findByEmail(String email) {
        if(email == null)
        {
            throw new IllegalArgumentException("email cannot be null");
        }

        try
        {
            return repository.findByEmail(email);
        } catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public boolean canAccess(String principalName, User entity) {
        return principalName.equals(entity.getEmail());
    }
}
