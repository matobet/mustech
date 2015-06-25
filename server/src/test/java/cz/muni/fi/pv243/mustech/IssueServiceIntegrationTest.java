package cz.muni.fi.pv243.mustech;

import cz.muni.fi.pv243.mustech.dal.IssueRepository;
import cz.muni.fi.pv243.mustech.dal.UserRepository;
import cz.muni.fi.pv243.mustech.model.*;
import cz.muni.fi.pv243.mustech.service.*;
import cz.muni.fi.pv243.mustech.util.Resources;
import org.apache.deltaspike.core.api.config.view.metadata.SkipMetaDataMerge;
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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

/**
 * Created by Tomas on 18. 6. 2015.
 */
@RunWith(Arquillian.class)
public class IssueServiceIntegrationTest {
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
                        IPermissionService.class,
                        TestPermissionService.class,
                        Service.class,
                        Issue.class,
                        Poll.class,
                        Post.class,
                        Option.class,
                        Answer.class,
                        PrincipalChecker.class,
                        UserService.class,
                        UserRepository.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/apache-deltaspike.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private IssueService issueService;

    @Inject
    private UserService userService;

    @Test
    @InSequence(1)
    @Transactional
    public void testSaveEntity()
    {
        Issue issue = getSampleIssue();
        issue.setId(null);

        issueService.saveOrUpdate(issue);

        Assert.assertNotNull("Entity should have been inserted.", issue.getId());
    }

    @Test
    @InSequence(2)
    @Transactional
    public void testFindEntity()
    {
        Issue issue = getSampleIssue();

        List<Issue> issues = issueService.findAll();
        Assert.assertEquals("Should be same length.", 1, issues.size());

        Issue actualIssue = issueService.findById(issues.get(0).getId());

        assertDeepEquals(issue, actualIssue);
    }

    @Test
    @InSequence(3)
    public void testUpdateEntity()
    {
        Issue expected = getSampleIssue();
        expected.setDescription("New description");
        expected.setName("New name");
        expected.setId(issueService.findAll().get(0).getId());

        issueService.saveOrUpdate(expected);

        Issue actual = issueService.findById(expected.getId());

        assertDeepEquals(expected, actual);
    }

    @Test
    @InSequence(4)
    public void testDelete()
    {
        Issue expected = issueService.findAll().get(0);
        issueService.delete(expected.getId());

        List<Issue> actual = issueService.findAll();

        Assert.assertTrue("Entity should be deleted.", !actual.contains(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNullEntity()
    {
        issueService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullEntity()
    {
        issueService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNullId()
    {
        issueService.findById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullId()
    {
        issueService.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNegativeId()
    {
        issueService.findById(0L);
        issueService.findById(-1L);
        issueService.findById(Long.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNegativeId()
    {
        issueService.delete(0L);
        issueService.delete(-1L);
        issueService.delete(Long.MIN_VALUE);
    }

    private void assertDeepEquals(Issue expected, Issue actual)
    {
        Calendar expectedCal = Calendar.getInstance();
        Calendar actualCal = Calendar.getInstance();
        expectedCal.setTime(expected.getCreatedAt());
        actualCal.setTime(actual.getCreatedAt());

        assertDateEqual(expectedCal, actualCal);

        Assert.assertEquals(expected.getCreatedBy(), actual.getCreatedBy());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getName(),actual.getName());

        expectedCal.setTime(expected.getExpiresAt());
        actualCal.setTime(actual.getExpiresAt());

        assertDateEqual(expectedCal, actualCal);

        List<Post> expectedPosts = new ArrayList<>(expected.getPosts());
        expectedPosts.removeAll(actual.getPosts());
        Assert.assertTrue("Unexpected post occured.", expectedPosts.isEmpty());

        List<Poll> expectedPolls = new ArrayList<>(expected.getPolls());
        expectedPolls.removeAll(actual.getPolls());
        Assert.assertTrue("Unexpected poll occured.", expectedPolls.isEmpty());
    }

    private void assertDateEqual(Calendar expectedCal, Calendar actualCal) {
        Assert.assertEquals(expectedCal.get(Calendar.YEAR), actualCal.get(Calendar.YEAR));
        Assert.assertEquals(expectedCal.get(Calendar.MONTH),actualCal.get(Calendar.MONTH));
        Assert.assertEquals(expectedCal.get(Calendar.DATE),actualCal.get(Calendar.DATE));
    }

    private Issue getSampleIssue()
    {
        Issue issue = new Issue();
        issue.setCreatedAt(getSampleCalendar().getTime());
        issue.setId(1L);
        issue.setCreatedBy(getAndPersistSampleUser());
        issue.setDescription("Description");
        issue.setExpiresAt(getSampleCalendar().getTime());
        issue.setName("Issue");
        issue.setPolls(Collections.emptyList());
        issue.setPosts(Collections.emptyList());

        return issue;
    }

    @Transactional
    private User getAndPersistSampleUser() {
        User user = new User();
        user.setName("name");
        user.setEmail("sampleUser@user.cz");
        user.setPassword("123");
        user.setRole(RoleType.USER);

        User stored = userService.findById(1L);

        if ( stored == null) {
            userService.saveOrUpdate(user);
            stored = userService.findById(1L);
        }
        return stored;
    }

    private Calendar getSampleCalendar()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014,9,10,11,20,20);

        return calendar;
    }
}
