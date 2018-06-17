package com.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "product_observations")
public class ProductObservation {

    @Id
    @GeneratedValue
    @Column(name = "product_observation_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userConnection_id")
    private UserConnection userConnection;

    private Long prodId;

    private Long startWatchTimestamp;
    private Long endWatchTimestamp;

    public ProductObservation(UserConnection userConnection, Long prodId) {
        this.userConnection = userConnection;
        this.prodId = prodId;
        startWatchTimestamp = System.nanoTime();
    }

    public ProductObservation() {
    }

    public long getId() {
        return id;
    }

    public UserConnection getUserConnection() {
        return userConnection;
    }

    public void setUserConnection(UserConnection userConnection) {
        this.userConnection = userConnection;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getStartWatchTimestamp() {
        return startWatchTimestamp;
    }

    public void setStartWatchTimestamp(Long startWatchTimestamp) {
        this.startWatchTimestamp = startWatchTimestamp;
    }

    public Long getEndWatchTimestamp() {
        return endWatchTimestamp;
    }

    public void setEndWatchTimestamp(Long endWatchTimestamp) {
        this.endWatchTimestamp = endWatchTimestamp;
    }
}
