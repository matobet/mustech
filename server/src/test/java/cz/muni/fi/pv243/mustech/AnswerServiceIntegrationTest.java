package cz.muni.fi.pv243.mustech;

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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tomas on 20. 6. 2015.
 */
@RunWith(Arquillian.class)
public class AnswerServiceIntegrationTest {
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
                        IssueService.class,
                        IssueRepository.class,
                        Issue.class,
                        Poll.class,
                        Post.class,
                        Option.class,
                        Answer.class,
                        PrincipalChecker.class,
                        IPermissionService.class,
                        TestPermissionService.class,
                        Service.class,
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
        Answer answer = getSampleAnswer();
        answer.setId(null);

        answerService.saveOrUpdate(answer);

        Assert.assertNotNull("Entity should have been inserted.", answer.getId());
    }

    @Test
    @InSequence(2)
    @Transactional
    public void testFindEntity()
    {
        Answer answer = answerService.findAll().get(0);

        List<Answer> answers = answerService.findAll();
        Assert.assertEquals("Should be same length.", 1, answers.size());

        Answer actualAnswer = answerService.findById(answers.get(0).getId());

        Assert.assertEquals(answer.getOption(),actualAnswer.getOption());
        Assert.assertEquals(answer.getUser(),actualAnswer.getUser());
        Assert.assertEquals(answer.getId(),actualAnswer.getId());
    }

    @Test
    @InSequence(3)
    public void testDelete()
    {
        Answer expected = answerService.findAll().get(0);
        answerService.delete(expected.getId());

        List<Answer> actual = answerService.findAll();

        Assert.assertTrue("Entity should be deleted.", !actual.contains(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNullEntity()
    {
        answerService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullEntity()
    {
        answerService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNullId()
    {
        answerService.findById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullId()
    {
        answerService.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNegativeId()
    {
        answerService.findById(0L);
        answerService.findById(-1L);
        answerService.findById(Long.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNegativeId()
    {
        answerService.delete(0L);
        answerService.delete(-1L);
        answerService.delete(Long.MIN_VALUE);
    }

    private Answer getSampleAnswer()
    {
        Answer answer = new Answer();

        answer.setId(1L);
        answer.setPoll(getAndPersistSamplePoll());
        answer.setOption(getAndPersistSampleOption());
        answer.setUser(getAndPersistSampleUser());

        return answer;
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

    @Transactional
    private Poll getAndPersistSamplePoll() {
        Poll poll = new Poll();
        poll.setAnswers(Collections.emptyList());
        poll.setIssue(null);
        poll.setOptions(Collections.emptyList());
        poll.setQuestion("Question");

        Poll stored = pollService.findById(1L);

        if ( stored == null) {
            pollService.saveOrUpdate(poll);
            stored = pollService.findById(1L);
        }
        return stored;
    }

    @Transactional
    private Option getAndPersistSampleOption() {
        Option option = new Option();
        option.setValue("value");
        option.setAnswers(Collections.emptyList());
        option.setPoll(getAndPersistSamplePoll());

        Option stored = optionService.findById(1L);

        if ( stored == null) {
            optionService.saveOrUpdate(option);
            stored = optionService.findById(1L);
        }
        return stored;
    }
}
