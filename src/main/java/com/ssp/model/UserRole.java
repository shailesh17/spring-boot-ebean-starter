package com.ssp.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Main Role entity.
 * 
 * @author shailesh.patel
 */
@Entity
@Table(name = "user_role")
// @Inheritance
// @MappedSuperclass
// @DiscriminatorColumn(name="role_type",
// discriminatorType=DiscriminatorType.STRING)
public class UserRole extends BaseIdModel {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Size(min = 2, max = 12)
    @Column(name = "role_type", length = 12, insertable = false, updatable = false)
    private RoleType roleType;

    @Basic
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;

    // Relationships...
    @ManyToOne(fetch = FetchType.EAGER, cascade = {}, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserRole(RoleType rt) {
        setRoleType(rt);
        setDateStart(new Date());
    }

    public UserRole(RoleType rt, User user) {
        this(rt);
        setUser(user);
    }

    public RoleType getRoleType() {
        return roleType;
    }

    private final void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.user.addUserRole(this);
    }

    /**
     * Returns true if this role is currently active determined by whether the current date/time
     * is between date start and date end.
     * 
     * @return
     */
    public boolean isActiveCurrently() {
        Date now = new Date();
        if (now.after(getDateStart())) {
            if (getDateEnd() == null) {
                return true;
            }
            else if (now.before(getDateEnd())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this) //
            .append("id", getId()).append("roleType", getRoleType()) //
            .append("user", getUser()).append("dateStart", getDateStart()) //
            .append("dateEnd", getDateEnd()).append("user", getUser()) //
            .toString(); //
    }

}