package cz.muni.fi.pv243.mustech.ws;

import cz.muni.fi.pv243.mustech.model.BaseModel;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ServerEndpoint(value = "/ws", encoders = JsonEncoder.class)
@Singleton
public class WebsocketEndpoint {

    @Inject
    private PrincipalChecker<Issue> issueChecker;

    @Inject
    private PrincipalChecker<Post> postChecker;

    /** map from connected user principals to websocket sessions */
    private ConcurrentMap<String, Session> sessions = new ConcurrentHashMap<>();

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
        sendPushUpdate(issue, issueChecker);
    }

    public void onPost(@Observes Post post) {
        System.out.println("post event!");
        sendPushUpdate(post, postChecker);
    }

    /** sends via websocket newly created entity to users that can access it */
    private <T extends BaseModel> void sendPushUpdate(T entity, PrincipalChecker<T> checker) {
        sessions.entrySet().stream()
                .filter(session -> checker.canAccess(session.getKey(), entity))
                .forEach(session -> {
                    session.getValue().getAsyncRemote().sendObject(entity);
                });
    }
}
