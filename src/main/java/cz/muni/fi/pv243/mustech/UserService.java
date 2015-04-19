package cz.muni.fi.pv243.mustech;

import cz.muni.fi.pv243.mustech.dal.UserRepository;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class UserService {

    @Inject
    private UserRepository userRepository;
    @Inject
    private PartitionManager partitionManager;

    @PostConstruct
    private void initUsers() {
        User user = new User();
        user.setId(1);
        user.setName("Franta");
        userRepository.save(user);

        IdentityManager identityManager = this.partitionManager.createIdentityManager();
        org.picketlink.idm.model.basic.User admin = new org.picketlink.idm.model.basic.User("test");
        admin.setFirstName("test");
        admin.setLastName("Administrator");
        identityManager.add(admin);
        Password password = new Password("12345678");
        identityManager.updateCredential(admin, password);
    }
}
