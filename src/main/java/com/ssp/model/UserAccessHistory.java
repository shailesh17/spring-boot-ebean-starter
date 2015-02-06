package com.ssp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Holds user login for history purposes
 * 
 * @author shailesh.patel
 */
@Entity
@Table
public class UserAccessHistory extends BaseIdModel {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccessed;

    @Size(max = 255)
    @Column(length = 255)
    private String twitterAccessToken;

    @Size(max = 100)
    @Column(length = 100)
    private String twitterAccessTokenKey;

    @Temporal(TemporalType.TIMESTAMP)
    private Date twitterAccessTokenDateExpires;

    // Relationships...
    @ManyToOne(fetch = FetchType.EAGER, cascade = {}, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Date getDateAccessed() {
        return dateAccessed;
    }

    public void setDateAccessed(Date dateAccessed) {
        this.dateAccessed = dateAccessed;
    }

    public String getTwitterAccessToken() {
        return twitterAccessToken;
    }

    public void setTwitterAccessToken(String twitterAccessToken) {
        this.twitterAccessToken = twitterAccessToken;
    }

    public String getTwitterAccessTokenKey() {
        return twitterAccessTokenKey;
    }

    public void setTwitterAccessTokenKey(String twitterAccessTokenKey) {
        this.twitterAccessTokenKey = twitterAccessTokenKey;
    }

    public Date getTwitterAccessTokenDateExpires() {
        return twitterAccessTokenDateExpires;
    }

    public void setTwitterAccessTokenDateExpires(Date twitterAccessTokenDateExpires) {
        this.twitterAccessTokenDateExpires = twitterAccessTokenDateExpires;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
