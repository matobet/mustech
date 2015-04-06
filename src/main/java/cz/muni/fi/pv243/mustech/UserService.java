package cz.muni.fi.pv243.mustech;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
