package cz.muni.fi.pv243.mustech;

import cz.muni.fi.pv243.mustech.dal.IssueRepository;
import cz.muni.fi.pv243.mustech.dal.UserRepository;
import cz.muni.fi.pv243.mustech.model.*;
import cz.muni.fi.pv243.mustech.service.*;
import cz.muni.fi.pv243.mustech.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Tomas on 22. 6. 2015.
 */
@RunWith(Arquillian.class)
public class ExpiredIssueBatchIntegrationTest {

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
                        IssueService.class,
                        IssueRepository.class,
                        ExpiredIssueBatchTrigger.class,
                        ExpiredIssueBatchProcessor.class,
                        DeleteIssueBatchWriter.class,
                        IssueBatchReader.class,
                        Issue.class,
                        Poll.class,
                        Post.class,
                        Option.class,
                        Answer.class,
                        PrincipalChecker.class,
                        UserService.class,
                        UserRepository.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/batch-jobs/expiredIssueBatchJob.xml")
                .addAsResource("META-INF/apache-deltaspike.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private UserService userService;

    @Inject
    private IssueService issueService;

    @Inject
    private ExpiredIssueBatchTrigger batchTrigger;

    @Test
    @InSequence(1)
    public void testBatch() {
        Issue i1 = new Issue();
        Issue i2 = new Issue();
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c.set(2010, 10, 10);

        i1.setName("name1");
        i1.setPolls(Collections.EMPTY_LIST);
        i1.setDescription("Desc 1");
        i1.setCreatedAt(c.getTime());
        i1.setPosts(Collections.EMPTY_LIST);
        i1.setCreatedBy(getAndPersistSampleUser());

        i2.setName("name1");
        i2.setPolls(Collections.EMPTY_LIST);
        i2.setDescription("Desc 1");
        i2.setCreatedAt(c.getTime());
        i2.setPosts(Collections.EMPTY_LIST);
        i2.setCreatedBy(getAndPersistSampleUser());

        c2.add(Calendar.DATE, -35);
        i1.setExpiresAt(c2.getTime());
        i2.setExpiresAt(new Date());
        issueService.saveOrUpdate(i1);
        issueService.saveOrUpdate(i2);

        Assert.assertEquals("Should have 2 items.", 2, issueService.findAll().size());

        batchTrigger.runBatch();
    }

    @Test
    @InSequence(2)
    public void testAfterBatch() {

        try {
            Thread.sleep(50000L);
        } catch (InterruptedException e) {
        }

        List<Issue> actual = issueService.findAll();

        Assert.assertEquals("Should have 1 item.", 1, actual.size());
    }

    @Transactional
    private User getAndPersistSampleUser() {
        User user = new User();
        user.setName("name");
        user.setEmail("sampleUser@user.cz");
        user.setPassword("123");
        user.setRole(RoleType.USER);

        User stored = userService.findById(1L);

        if (stored == null) {
            userService.saveOrUpdate(user);
            stored = userService.findById(1L);
        }
        return stored;
    }
}
