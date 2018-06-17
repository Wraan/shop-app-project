package com.shop.service;

import com.shop.components.Session;
import com.shop.model.UserConnection;
import com.shop.repository.UserConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserConnectionServices {
    private UserConnectionRepository connectionRepo;
    private Session session;
    private Date requestDate;
    private Date sessionDate;
    private static final long MAX_INACTIVE_SESSION_TIME = 3 * 100000; // time of the session 300s


    @Autowired
    public UserConnectionServices(UserConnectionRepository connectionRepo, Session session, Date requestDate, Date sessionDate) {
        this.connectionRepo = connectionRepo;
        this.session = session;
        this.requestDate = requestDate;
        this.sessionDate = sessionDate;
    }

    private UserConnection userConnection;

    public void startConnection() {
        userConnection = new UserConnection();
        userConnection.setStartSession(sessionDate);
        connectionRepo.save(userConnection);
    }

    public void lastAccess() {
        userConnection.setLastAccess(requestDate);
        connectionRepo.save(userConnection);
    }

    public void endConnection() {
        userConnection.setEndSession(requestDate);
        userConnection.setEnable(false);

        connectionRepo.save(userConnection);
    }

    public void setUser(Long id) {
        userConnection.setUser_id(id);
    }

    public List<UserConnection> getAll() {
        return (List<UserConnection>) connectionRepo.findAll();
    }

    public List<UserConnection> connectionsToClear() {
        List<UserConnection> list = getAll();
        List<UserConnection> connectionList = new ArrayList<>();

        for (UserConnection connection : list) {
            if (connection.getLastAccess().getTime() - connection.getStartSession().getTime() > MAX_INACTIVE_SESSION_TIME
                    || !connection.isEnable()) {

                connectionList.add(connection);
            }
        }
        return connectionList;
    }

    public void deleteList(List<UserConnection> userConnections) {
        connectionRepo.deleteAll(userConnections);
    }

    public UserConnection getUserConnection() {
        return userConnection;
    }

    public void setUserConnection(UserConnection userConnection) {
        this.userConnection = userConnection;
    }

    public String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session connectionSession) {
        this.session = session;
    }
}
