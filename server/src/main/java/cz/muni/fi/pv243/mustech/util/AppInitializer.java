package cz.muni.fi.pv243.mustech.util;

import cz.muni.fi.pv243.mustech.model.*;
import cz.muni.fi.pv243.mustech.service.IssueService;
import cz.muni.fi.pv243.mustech.service.UserService;
import org.joda.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;


@Startup
@Singleton
public class AppInitializer {

    @Inject
    private UserService userService;
    @Inject
    private IssueService issueService;

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
        dinner.setName("Dinner");
        dinner.setDescription("Where should we go eat?");
        dinner.setCreatedAt(new LocalDate(2015, 6, 13).toDate());
        dinner.setExpiresAt(new LocalDate(2015, 12, 31).toDate());
        dinner.setCreatedBy(userService.findByEmail("user@user.cz"));
        dinner.setPolls(initDemoPolls(dinner));

        issueService.saveOrUpdate(dinner);

        Issue game = new Issue();
        game.setName("Game Evening");
        game.setDescription("What are we gonna play?");
        game.setCreatedAt(new LocalDate(2015, 6, 13).toDate());
        game.setExpiresAt(new LocalDate(2015, 12, 31).toDate());
        game.setCreatedBy(userService.findByEmail("user@user.cz"));

        issueService.saveOrUpdate(game);

        Issue holiday = new Issue();
        holiday.setName("Summer Holiday");
        holiday.setDescription("Turkey, Bali Bali, ...");
        holiday.setCreatedAt(new LocalDate(2015, 6, 13).toDate());
        holiday.setExpiresAt(new LocalDate(2015, 12, 31).toDate());
        holiday.setCreatedBy(userService.findByEmail("user@user.cz"));

        issueService.saveOrUpdate(holiday);

        Issue trip = new Issue();
        trip.setName("School Trip");
        trip.setDescription("Cabine in da woods?");
        trip.setCreatedAt(new LocalDate(2015, 6, 13).toDate());
        trip.setExpiresAt(new LocalDate(2015, 12, 31).toDate());
        trip.setCreatedBy(userService.findByEmail("user@user.cz"));

        issueService.saveOrUpdate(trip);
    }

    private List<Poll> initDemoPolls(Issue issue) {
        Poll poll = new Poll();
        poll.setQuestion("K5 Steak House");

        Option yes = new Option();
        yes.setValue("Yes");
        yes.setPoll(poll);
        Option no = new Option();
        no.setValue("No");
        no.setPoll(poll);

        poll.setOptions(asList(yes, no));
        poll.setIssue(issue);

        return Collections.singletonList(poll);
    }

}
