package com.ssp.repository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Expression;
import com.ssp.model.BaseIdModel;

/**
 * Provides common functionality for repository classes.
 * 
 * @author shailesh.patel
 */
public abstract class BaseRepository<T extends BaseIdModel, PK extends Serializable> {

    protected Logger log = LoggerFactory.getLogger(getClass());
    public static final int MAx_ROWS_DEFAULT = 1000;

    @Autowired
    protected DataAccessConfigService daConfig;

    private final Class<T> persistentClass;

    protected BaseRepository(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public DataAccessConfigService getDataAccessConfig() {
        return this.daConfig;
    }

    public T save(T item) {
        EbeanServer ws = this.daConfig.getWriteEbeanServer();
        ws.beginTransaction();
        ws.save(item);
        ws.commitTransaction();
        return item;
    }

    /**
     * Finds the single result given the where clause expression.
     * 
     * @param where
     * @return
     */
    public T findSingleResult(Expression where) {
        List<T> results = getDataAccessConfig().getReadEbeanServer() //
            .find(persistentClass) //
            .where(where) //
            .setMaxRows(1) //
            .findList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    /**
     * Finds a list of results given the where clause expression.
     * 
     * @returns always a not-nulll of results
     */
    public List<T> findList(Expression where) {
        List<T> results = getDataAccessConfig().getReadEbeanServer() //
            .find(persistentClass) //
            .where(where) //
            .setMaxRows(getMaxRows()) //
            .findList();

        if (results == null || results.isEmpty()) {
            return Collections.emptyList();
        }
        return results;
    }

    /**
     * Finds all records for this model, upto the set max.
     * 
     * @return
     */
    public List<T> findAll() {
        return getDataAccessConfig().getReadEbeanServer() //
            .find(persistentClass) //
            .setMaxRows(getMaxRows()) //
            .findList();
    }

    protected int getMaxRows() {
        return MAx_ROWS_DEFAULT;
    }
}
