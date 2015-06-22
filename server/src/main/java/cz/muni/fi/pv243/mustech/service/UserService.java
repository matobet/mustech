package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.UserRepository;
import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.User;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * @author Milan
 */
@Named
@Transactional
public class UserService extends AbstractGenericService<User, UserRepository> {

    @Inject
    private UserRepository userRepository;

    public UserService() {
        super(User.class);
    }

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
