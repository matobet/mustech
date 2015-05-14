package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.User;

import java.util.List;

/**
 * @author Milan
 */
public interface UserService {

    User save(User user);
    void remove(User user);
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll();
}
