package com.ssp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.ssp.repository.DataAccessConfigService;

@Configuration
public class AppConfiguration {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${app.datasource.read.name}")
    private String readDataSourceName;
    @Value("${app.datasource.readwrite.name}")
    private String readWriteDataSourceName;

    @Bean
    @Primary
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public DataAccessConfigService dataAccessConfigService() {
        log.debug("setting up dataAccessConfigService with: read=" + readDataSourceName
            + ", write=" + readWriteDataSourceName);
        DataAccessConfigService dacs = new DataAccessConfigService(readDataSourceName,
            readWriteDataSourceName);
        return dacs;
    }
}
