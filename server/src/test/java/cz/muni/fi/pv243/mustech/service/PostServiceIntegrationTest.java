package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.*;
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

import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tomas on 20. 6. 2015.
 */
@RunWith(Arquillian.class)
public class PostServiceIntegrationTest {
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
                        AnswerService.class,
                        AnswerRepository.class,
                        IPermissionService.class,
                        TestPermissionService.class,
                        Service.class,
                        Issue.class,
                        Poll.class,
                        Post.class,
                        Option.class,
                        Answer.class,
                        IssueService.class,
                        IssueRepository.class,
                        PrincipalChecker.class,
                        PostService.class,
                        PostRepository.class,
                        OptionService.class,
                        OptionRepository.class,
                        UserService.class,
                        UserRepository.class)
                .addAsResource("META-INF/persistence.xml")
//                .addAsServiceProvider(PrincipalChecker.class,PermissionServiceMy.class)
                .addAsResource("META-INF/apache-deltaspike.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private IssueService issueService;

    @Inject
    private UserService userService;

    @Inject
    private PostService postService;

    @Test
    @InSequence(1)
    @Transactional
    public void testSaveEntity()
    {
        Post post = getSamplePost();
        post.setId(null);

        postService.saveOrUpdate(post);

        Assert.assertNotNull("Entity should have been inserted.", post.getId());
    }

    @Test
    @InSequence(2)
    @Transactional
    public void testFindEntity()
    {
        Post post = postService.findAll().get(0);

        List<Post> posts = postService.findAll();
        Assert.assertEquals("Should be same length.", 1, posts.size());

        Post actualPost = postService.findById(posts.get(0).getId());

        Assert.assertEquals(post.getIssue(), actualPost.getIssue());
        Assert.assertEquals(post.getUser(), actualPost.getUser());
        Assert.assertEquals(post.getText(), actualPost.getText());
        Assert.assertEquals(post.getTimestamp(), actualPost.getTimestamp());
        Assert.assertEquals(post.getId(), actualPost.getId());
    }

    @Test
    @InSequence(3)
    public void testUpdateEntity()
    {
        Post expected = postService.findAll().get(0);
        expected.setText("new text");
        expected.setId(postService.findAll().get(0).getId());

        postService.saveOrUpdate(expected);

        Post actual = postService.findById(expected.getId());

        Assert.assertEquals(expected.getText(),actual.getText());
    }

    @Test
    @InSequence(4)
    public void testDelete()
    {
        Post expected = postService.findAll().get(0);
        postService.delete(expected.getId());

        List<Post> actual = postService.findAll();

        Assert.assertTrue("Entity should be deleted.", !actual.contains(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNullEntity()
    {
        postService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullEntity()
    {
        postService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNullId()
    {
        postService.findById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullId()
    {
        postService.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNegativeId()
    {
        postService.findById(0L);
        postService.findById(-1L);
        postService.findById(Long.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNegativeId()
    {
        postService.delete(0L);
        postService.delete(-1L);
        postService.delete(Long.MIN_VALUE);
    }

    private Post getSamplePost()
    {
        Post post = new Post();

        post.setId(1L);
        post.setText("text");
        post.setIssue(getSampleIssue());
        post.setUser(getAndPersistSampleUser());
        post.setTimestamp(getSampleCalendar().getTime());

        return post;
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

        Issue stored = issueService.findById(1L);

        if ( stored == null) {
            issueService.saveOrUpdate(issue);
            stored = issueService.findById(1L);
        }
        return stored;
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
        calendar.set(2014, 9, 10, 11, 20, 20);

        return calendar;
    }
}

//@Stateless
//class PermissionServiceMy extends PermissionService
//{
//    public PermissionServiceMy() {
//    }
//
//    public <T extends BaseModel> boolean checkAccess(PrincipalChecker<T> checker, T entity) {
//        return true;
//    }
//}
