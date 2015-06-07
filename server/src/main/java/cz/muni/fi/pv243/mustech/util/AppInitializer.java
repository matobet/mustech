package cz.muni.fi.pv243.mustech.util;

import cz.muni.fi.pv243.mustech.model.RoleType;
import cz.muni.fi.pv243.mustech.model.User;
import cz.muni.fi.pv243.mustech.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;


@Startup
@Singleton
public class AppInitializer {

    @Inject
    private UserService userService;

    @PostConstruct
    private void initUsers() {
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@admin.cz");
        admin.setRole(RoleType.ADMIN);

        User user = new User();
        user.setName("user");
        user.setPassword("user");
        user.setEmail("user@user.cz");
        user.setRole(RoleType.USER);

        userService.saveOrUpdate(admin);
        userService.saveOrUpdate(user);
    }
}
