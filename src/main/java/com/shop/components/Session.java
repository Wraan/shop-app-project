package com.shop.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session {

    private Long user_id;
    private boolean enable;
    private Date startSession;
    private Date endSession;
    private Date lastAccess;
    private String ip;

    @PostConstruct
    public void init() {
        startSession = Calendar.getInstance().getTime();
        lastAccess = Calendar.getInstance().getTime();
        enable = true;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getStartSession() {
        return startSession;
    }

    public void setStartSession(Date startSession) {
        this.startSession = startSession;
    }

    public Date getEndSession() {
        return endSession;
    }

    public void setEndSession(Date endSession) {
        this.endSession = endSession;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
