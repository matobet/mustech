package cz.muni.fi.pv243.mustech.ws;

import cz.muni.fi.pv243.mustech.model.Issue;
import cz.muni.fi.pv243.mustech.model.Post;
import cz.muni.fi.pv243.mustech.service.PrincipalChecker;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/ws")
@Singleton
public class WebsocketEndpoint {

    @Inject
    private PrincipalChecker<Issue> issueChecker;

    @Inject
    private PrincipalChecker<Post> postChecker;

    /** map from connected user principals to websocket sessions */
    private Map<String, Session> sessions = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connection established: " + session.getUserPrincipal().getName());
        sessions.put(session.getUserPrincipal().getName(), session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Disconnected: " + session.getUserPrincipal().getName());
        sessions.remove(session.getUserPrincipal().getName());
    }

    public void onIssue(@Observes Issue issue) {
        System.out.println("issue event!");
        sessions.entrySet().stream()
                .filter(session -> issueChecker.canAccess(session.getKey(), issue))
                .forEach(session -> {
                    // TODO: send actual issue
                    session.getValue().getAsyncRemote().sendObject("new issue");
                });
    }

    public void onPost(@Observes Post post) {
        sessions.entrySet().stream()
                .filter(session -> postChecker.canAccess(session.getKey(), post))
                .forEach(session -> {
                    // TODO: send actual post
                    session.getValue().getAsyncRemote().sendObject("new post");
                });
    }
}
