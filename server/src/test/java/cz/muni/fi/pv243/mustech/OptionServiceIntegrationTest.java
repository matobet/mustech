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
public class OptionServiceIntegrationTest
{
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
                        Issue.class,
                        Poll.class,
                        Post.class,
                        Option.class,
                        Answer.class,
                        IssueService.class,
                        IssueRepository.class,
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
    private IssueService issueService;

    @Inject
    private UserService userService;

    @Inject
    private OptionService optionService;

    @Test
    @InSequence(1)
    @Transactional
    public void testSaveEntity()
    {
        Option option = getSampleOption();
        option.setId(null);

        optionService.saveOrUpdate(option);

        Assert.assertNotNull("Entity should have been inserted.", option.getId());
    }

    @Test
    @InSequence(2)
    @Transactional
    public void testFindEntity()
    {
        Option option = optionService.findAll().get(0);

        List<Option> options = optionService.findAll();
        Assert.assertEquals("Should be same length.", 1, options.size());

        Option actualOption = optionService.findById(options.get(0).getId());

        Assert.assertEquals(option.getPoll(), actualOption.getPoll());
        Assert.assertEquals(option.getValue(), actualOption.getValue());
        Assert.assertEquals(option.getId(), actualOption.getId());

        List<Answer> expectedAnswers = new ArrayList<>(option.getAnswers());
        expectedAnswers.removeAll(actualOption.getAnswers());
        Assert.assertTrue("Unexpected post occured.", expectedAnswers.isEmpty());
    }

    @Test
    @InSequence(3)
    public void testUpdateEntity()
    {
        Option expected = optionService.findAll().get(0);
        expected.setValue("new value");
        expected.setId(optionService.findAll().get(0).getId());

        optionService.saveOrUpdate(expected);

        Option actual = optionService.findById(expected.getId());

        Assert.assertEquals(expected.getValue(), actual.getValue());
    }

    @Test
    @InSequence(4)
    public void testDelete()
    {
        Option expected = optionService.findAll().get(0);
        optionService.delete(expected.getId());

        List<Option> actual = optionService.findAll();

        Assert.assertTrue("Entity should be deleted.", !actual.contains(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNullEntity()
    {
        optionService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullEntity()
    {
        optionService.saveOrUpdate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNullId()
    {
        optionService.findById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullId()
    {
        optionService.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNegativeId()
    {
        optionService.findById(0L);
        optionService.findById(-1L);
        optionService.findById(Long.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNegativeId()
    {
        optionService.delete(0L);
        optionService.delete(-1L);
        optionService.delete(Long.MIN_VALUE);
    }

    private Option getSampleOption()
    {
        Option option = new Option();

        option.setId(1L);
        option.setValue("text");
        option.setAnswers(Collections.emptyList());
        option.setPoll(null);

        return option;
    }
}

