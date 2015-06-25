package cz.muni.fi.pv243.mustech;

import cz.muni.fi.pv243.mustech.dal.AnswerRepository;
import cz.muni.fi.pv243.mustech.dal.OptionRepository;
import cz.muni.fi.pv243.mustech.dal.PollRepository;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tomas on 20. 6. 2015.
 */
@RunWith(Arquillian.class)
public class PollServiceIntegrationTest {
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
                        IPermissionService.class,
                        TestPermissionService.class,
                        Service.class,
                        AnswerService.class,
                        AnswerRepository.class,
                        Issue.class,
                        Poll.class,
                        Post.class,
                        Option.class,
                        Answer.class,
                        PrincipalChecker.class,
                        PollService.class,
                        PollRepository.class,
                        OptionService.class,
                        OptionRepository.class,
                        UserService.class,
                        UserRepository.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/apache-deltaspike.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AnswerService answerService;

    @Inject
    private UserService userService;

    @Inject
    private OptionService optionService;

    @Inject
    private PollService pollService;

    @Test
    @InSequence(1)
    @Transactional
    public void testSaveEntity()
    {
        Poll poll = getSamplePoll();
        poll.setId(null);

        pollService.saveOrUpdate(poll);

        Assert.assertNotNull("Entity should have been inserted.", poll.getId());
    }

    @Test
    @InSequence(2)
    @Transactional
    public void testFindEntity()
    {
        Poll poll = pollService.findAll().get(0);

        List<Poll> polls = pollService.findAll();
        Assert.assertEquals("Should be same length.", 1, polls.size());

        Poll actualPoll = pollService.findById(polls.get(0).getId());

        Assert.assertEquals(poll.getIssue(), actualPoll.getIssue());
        Assert.assertEquals(poll.getQuestion(), actualPoll.getQuestion());
        Assert.assertEquals(poll.getId(), actualPoll.getId());
    }

    @Test
    @InSequence(3)
    public void testDelete()
    {
        Poll expected = pollService.findAll().get(0);
        pollService.delete(expected.getId());

        List<Poll> actual = pollService.findAll();

        Assert.assertTrue("Entity should be deleted.", !actual.contains(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNullEntity()
    {
        pollService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullEntity()
    {
        pollService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNullId()
    {
        pollService.findById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullId()
    {
        pollService.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNegativeId()
    {
        pollService.findById(0L);
        pollService.findById(-1L);
        pollService.findById(Long.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNegativeId()
    {
        pollService.delete(0L);
        pollService.delete(-1L);
        pollService.delete(Long.MIN_VALUE);
    }

    private Poll getSamplePoll()
    {
        Poll poll = new Poll();

        poll.setId(1L);
        poll.setQuestion("question");
        poll.setIssue(null);
        poll.setAnswers(Collections.emptyList());
        poll.setOptions(Collections.emptyList());

        return poll;
    }
}
