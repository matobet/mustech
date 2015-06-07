package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.UserRepository;
import cz.muni.fi.pv243.mustech.model.User;

import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public abstract class BaseService {

    @Inject
    protected UserRepository userRepository;

    @Context
    private HttpServletRequest request;

    protected User getCurrentUser() {
        String email = request.getRemoteUser();
        return userRepository.findByEmail(email);
    }
}
