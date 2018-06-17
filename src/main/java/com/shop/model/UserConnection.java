package com.shop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class UserConnection {
    @Id
    @GeneratedValue
    private Long id;
    private Long user_id;
    private boolean enable;
    private Date startSession;
    private Date endSession;
    private Date lastAccess;
    private String ip;

    @OneToMany(mappedBy = "userConnection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductObservation> productObservations = new ArrayList<>();

    public UserConnection() {
    }

    public UserConnection(Long user_id, boolean enable, Date startSession, Date endSession, Date lastAccess, String ip, List<ProductObservation> productObservations) {
        this.user_id = user_id;
        this.enable = enable;
        this.startSession = startSession;
        this.endSession = endSession;
        this.lastAccess = lastAccess;
        this.ip = ip;
        this.productObservations = productObservations;
    }

    public List<ProductObservation> getProductObservations() {
        return productObservations;
    }

    public void setProductObservations(List<ProductObservation> productObservations) {
        this.productObservations = productObservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
