package cz.muni.fi.pv243.mustech;

import cz.muni.fi.pv243.mustech.dal.UserRepository;
import cz.muni.fi.pv243.mustech.model.BaseModel;
import cz.muni.fi.pv243.mustech.model.RoleType;
import cz.muni.fi.pv243.mustech.model.User;
import cz.muni.fi.pv243.mustech.service.AbstractGenericService;
import cz.muni.fi.pv243.mustech.service.GenericService;
import cz.muni.fi.pv243.mustech.service.UserService;
import cz.muni.fi.pv243.mustech.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class UserServiceIntegrationTest {

    @Deployment
    public static Archive<?> createArchive() {
        File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeAndTestDependencies()
                .resolve().withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsLibraries(libs)
                .addClasses(
                        Resources.class,
                        BaseModel.class,
                        User.class,
                        RoleType.class,
                        GenericService.class,
                        AbstractGenericService.class,
                        UserService.class,
                        UserRepository.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/apache-deltaspike.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private UserService userService;

    @Test
    @Transactional
    @InSequence(1)
    public void testSaveAndGet() {
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("123456");
        admin.setEmail("admin@admin.cz");
        admin.setRole(RoleType.ADMIN);

        userService.saveOrUpdate(admin);

        assertThat(admin.getId(), not(nullValue()));
        assertThat(userService.findById(admin.getId()), is(equalTo(admin)));
    }

    @Test
    @Transactional
    @InSequence(2)
    public void testUpdate() {
        User admin = userService.findByEmail("admin@admin.cz");
        admin.setName("user");
        admin.setPassword("123");
        admin.setRole(RoleType.USER);

        userService.saveOrUpdate(admin);

        assertThat(userService.findById(admin.getId()), is(equalTo(admin)));
    }

    @Test
    @Transactional
    @InSequence(3)
    public void testFindByEmail() {
        User admin = userService.findByEmail("admin@admin.cz");

        assertTrue(userService.findById(admin.getId()) != null);
    }

    @Test
    @Transactional
    @InSequence(4)
    public void testDelete() {
        User admin = userService.findByEmail("admin@admin.cz");

        userService.delete(admin.getId());

        assertTrue(userService.findById(admin.getId()) == null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindWithNullId()
    {
        userService.findById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindWithNegativeId()
    {
        userService.findById(-1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindWithNullEmail()
    {
        userService.findByEmail(null);
    }

    @Test
    public void testFindWithFakeEmail()
    {
        Assert.assertNull(userService.findByEmail("123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveorUpdateWithNullArg()
    {
        userService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWithNullId()
    {
        userService.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWithNegativeId()
    {
        userService.findById(-1L);
    }
}
