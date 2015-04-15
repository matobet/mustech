package cz.muni.fi.pv243.mustech;

import cz.muni.fi.pv243.mustech.dal.UserRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class UserIntegrationTest {

    @Deployment
    public static Archive<?> createArchive() {
        File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeAndTestDependencies()
                .resolve().withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsLibraries(libs)
                .addClasses(Resources.class, User.class, UserRepository.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/apache-deltaspike.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testUsers() {
        User user = new User();
        user.setName("Franta");
        userRepository.save(user);

        assertEquals(user.getId(), userRepository.findByName("Franta").getId());
    }
}
