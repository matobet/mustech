package cz.muni.fi.pv243.mustech.util;

import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.User;

import java.util.List;

/**
 * Created by Tomas on 13. 6. 2015.
 */
public class UserNotification {
    private final List<User> users;
    private final Issue issue;

    public UserNotification(List<User> users, Issue issue) {
        this.users = users;
        this.issue = issue;
    }

    public List<User> getUsers() {
        return users;
    }

    public Issue getIssue() {
        return issue;
    }
}
