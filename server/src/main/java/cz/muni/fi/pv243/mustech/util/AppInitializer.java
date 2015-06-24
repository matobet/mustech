package cz.muni.fi.pv243.mustech.util;

import cz.muni.fi.pv243.mustech.model.*;
import cz.muni.fi.pv243.mustech.service.IssueService;
import cz.muni.fi.pv243.mustech.service.PollService;
import cz.muni.fi.pv243.mustech.service.UserService;
import org.joda.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import static java.util.Arrays.asList;

@Startup
@Singleton
public class AppInitializer {

    @Inject
    private UserService userService;

    @Inject
    private IssueService issueService;

    @Inject
    private PollService pollService;

    @PostConstruct
    private void initUsers() {
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin123");
        admin.setEmail("admin@admin.cz");
        admin.setRole(RoleType.ADMIN);

        User user = new User();
        user.setName("user");
        user.setPassword("user123");
        user.setEmail("user@user.cz");
        user.setRole(RoleType.USER);

        userService.saveOrUpdate(admin);
        userService.saveOrUpdate(user);

        initDemoIssues();
    }

    private void initDemoIssues() {
        Issue dinner = new Issue();
        User user = userService.findByEmail("user@user.cz");

        dinner.setName("Dinner");
        dinner.setDescription("Where should we go eat?");
        dinner.setCreatedAt(new LocalDate(2015, 6, 13).toDate());
        dinner.setExpiresAt(new LocalDate(2015, 12, 31).toDate());
        dinner.setCreatedBy(user);

        Poll poll = initDemoPoll(dinner, "tady ne");
        pollService.saveOrUpdate(poll);

        Poll poll2 = initDemoPoll(dinner, "na pivo");
        pollService.saveOrUpdate(poll2);

        Poll poll3 = initDemoPoll(dinner, "hospoda pod schodem");
        pollService.saveOrUpdate(poll3);

        Poll poll4 = initDemoPoll(dinner, "restaurace nad schodem");
        pollService.saveOrUpdate(poll4);

        issueService.saveOrUpdate(dinner);
        issueService.addConcernedUser(dinner.getId(), user.getId());

        Issue game = new Issue();
        game.setName("Game Evening");
        game.setDescription("What are we gonna play?");
        game.setCreatedAt(new LocalDate(2015, 6, 13).toDate());
        game.setExpiresAt(new LocalDate(2015, 12, 31).toDate());
        game.setCreatedBy(user);

        issueService.saveOrUpdate(game);

        Issue holiday = new Issue();
        holiday.setName("Summer Holiday");
        holiday.setDescription("Turkey, Bali Bali, ...");
        holiday.setCreatedAt(new LocalDate(2015, 6, 13).toDate());
        holiday.setExpiresAt(new LocalDate(2015, 12, 31).toDate());
        holiday.setCreatedBy(user);

        issueService.saveOrUpdate(holiday);

        Issue trip = new Issue();
        trip.setName("School Trip");
        trip.setDescription("Cabine in da woods?");
        trip.setCreatedAt(new LocalDate(2015, 6, 13).toDate());
        trip.setExpiresAt(new LocalDate(2015, 12, 31).toDate());
        trip.setCreatedBy(user);

        issueService.saveOrUpdate(trip);

        Issue adminEvent = new Issue();
        adminEvent.setName("Shouldnt Be Visible to User");
        adminEvent.setDescription("User is not author, so he shouldn't see it");
        adminEvent.setCreatedAt(new LocalDate(2015, 6, 13).toDate());
        adminEvent.setExpiresAt(new LocalDate(2015, 12, 31).toDate());
        adminEvent.setCreatedBy(userService.findByEmail("admin@admin.cz"));

        issueService.saveOrUpdate(adminEvent);

    }

    private Poll initDemoPoll(Issue issue, String name) {
        Poll poll = new Poll();
        poll.setQuestion(name);

        Option yes = new Option();
        yes.setValue("Yes");
        yes.setPoll(poll);
        Option no = new Option();
        no.setValue("No");
        no.setPoll(poll);

        poll.setOptions(asList(yes, no));
        poll.setIssue(issue);

        return poll;
    }

}
