package com.ssp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "twitter_id"))
public class User extends BaseIdModel {

    private static final long serialVersionUID = 1L;

    @Basic
    @NotNull
    @Size(min = 5, max = 50)
    @Column(length = 100)
    private String fullName;

    @Basic
    @NotNull
    @Size(min = 5, max = 50)
    @Column(length = 50)
    private String twitterId;

    @Basic
    @NotNull
    @Size(min = 5, max = 64)
    @Column(length = 64)
    private String twitterName;

    @Basic
    @Column
    private Boolean twitterVerified;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    // private static Finder<Long, User> find = new Finder<Long,
    // User>(Long.class, User.class);

    public User() {
        setDateRegistered(new Date());
    }

    public User(String twitterId, String twitterName, String fullName) {
        this();
        setTwitterId(twitterId);
        setTwitterName(twitterName);
        setFullName(fullName);
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public void setTwitterName(String twitterName) {
        this.twitterName = twitterName;
    }

    public Boolean getTwitterVerified() {
        return twitterVerified;
    }

    public void setTwitterVerified(Boolean twitterVerified) {
        this.twitterVerified = twitterVerified;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public boolean hasUserRole(RoleType roleType) {
        UserRole ur = getUserRoleForType(roleType);
        if (ur != null && ur.isActiveCurrently()) {
            return true;
        }
        return false;
    }

    public UserRole getUserRoleForType(RoleType roleType) {
        UserRole candidRole = null;
        if (this.userRoles != null) {
            for (UserRole ur : this.userRoles) {
                if (ur.getRoleType() == roleType) {
                    if (ur.isActiveCurrently()) {
                        return ur;
                    }
                    else {
                        // keep looking for active role, but save this in case
                        if (candidRole == null) {
                            candidRole = ur;
                        }
                    }
                }
            }
        }
        return candidRole; // can be the inactive role or null
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    /**
     * Returns a set of the roleTypes that the user currently has.
     * 
     * @return
     */
    public Set<RoleType> getActiveUserRoleTypes() {
        Set<RoleType> rtypes = new HashSet<RoleType>();

        for (UserRole ur : this.userRoles) {
            if (ur.isActiveCurrently()) {
                rtypes.add(ur.getRoleType());
            }
        }
        return rtypes;
    }

    public void addUserRole(UserRole aUserRole) {
        if (this.userRoles == null) {
            this.userRoles = new HashSet<UserRole>();
        }
        this.userRoles.add(aUserRole);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this) //
            .append("id", getId()) //
            .append("dateLastUpdated", getDateLastUpdated()) //
            .append("twitterId", getTwitterId()) //
            .append("twitterName", getTwitterName()) //
            .append("fullName", getFullName()) //
            .append("twitterVerified", getTwitterVerified()) //
            .append("dateRegistered", getDateRegistered()) //
            .append("userRoles", getUserRoles()) //
            .toString(); //
    }
}