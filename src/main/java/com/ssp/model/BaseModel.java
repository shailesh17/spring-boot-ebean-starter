package com.ssp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

/**
 * Provides an application level model object where all application specific model objects can
 * be derived. Use this class to share common attributes and functionality of all model objects
 * for the application.
 * 
 * @author shailesh.patel
 */
@MappedSuperclass
public abstract class BaseModel extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    // @Version
    // private Long version;

    @Basic
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedTimestamp
    private Date dateCreated;

    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @UpdatedTimestamp
    private Date dateLastUpdated;

    public final Date getDateCreated() {
        return dateCreated;
    }

    public final Date getDateLastUpdated() {
        return dateLastUpdated;
    }
}