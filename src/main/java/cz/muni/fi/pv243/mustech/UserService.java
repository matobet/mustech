package cz.muni.fi.pv243.mustech;

import cz.muni.fi.pv243.mustech.dal.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class UserService {

    @Inject
    private UserRepository userRepository;

    @PostConstruct
    private void initUsers() {
        User user = new User();
        user.setId(1);
        user.setName("Franta");
        //userRepository.save(user);
        userRepository.save(user);
    }
}
