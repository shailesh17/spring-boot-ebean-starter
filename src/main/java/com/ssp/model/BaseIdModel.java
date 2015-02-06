package com.ssp.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;

/**
 * Provides an application level model object where all application specific model objects can
 * be derived. Use this class to share common attributes and functionality of all model objects
 * for the application.
 * 
 * @author shailesh.patel
 */
@MappedSuperclass
public abstract class BaseIdModel extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Min(0)
    private Long id;

    public final Long getId() {
        return id;
    }
}