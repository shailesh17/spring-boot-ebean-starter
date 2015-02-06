package com.ssp.repository;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;

/**
 * Used to centralize the necessary database configuration and provide single reference to all
 * code need reference to Ebean data access services.
 * 
 * @author shailesh.patel
 */
public class DataAccessConfigService {

    private final EbeanServer readEbeanServer;
    private final EbeanServer writeEbeanServer;

    public DataAccessConfigService(String readDataSourceName, String readWriteDataSourceName) {
        this.readEbeanServer = EbeanServerFactory.create(readDataSourceName);
        this.writeEbeanServer = EbeanServerFactory.create(readWriteDataSourceName);
    }

    private DataAccessConfigService(EbeanServer rEbServer, EbeanServer wEbServer) {
        this.readEbeanServer = rEbServer;
        this.writeEbeanServer = wEbServer;
    }

    public static DataAccessConfigService setup(String readDataSourceName,
        String readWriteDataSourceName) throws Exception {

        DataAccessConfigService instance = new DataAccessConfigService(
            EbeanServerFactory.create(readDataSourceName),
            EbeanServerFactory.create(readWriteDataSourceName));

        return instance;
    }

    public EbeanServer getReadEbeanServer() {
        return readEbeanServer;
    }

    public EbeanServer getWriteEbeanServer() {
        return writeEbeanServer;
    }
}
