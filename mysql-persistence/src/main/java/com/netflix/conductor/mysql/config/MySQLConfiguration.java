/*
 * Copyright 2020 Netflix, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.netflix.conductor.mysql.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.conductor.dao.EventHandlerDAO;
import com.netflix.conductor.dao.ExecutionDAO;
import com.netflix.conductor.dao.MetadataDAO;
import com.netflix.conductor.dao.PollDataDAO;
import com.netflix.conductor.dao.QueueDAO;
import com.netflix.conductor.dao.RateLimitingDAO;
import com.netflix.conductor.mysql.dao.MySQLExecutionDAO;
import com.netflix.conductor.mysql.dao.MySQLMetadataDAO;
import com.netflix.conductor.mysql.dao.MySQLQueueDAO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "db", havingValue = "mysql")
public class MySQLConfiguration {

    @Bean
    public DataSource dataSource(MySQLProperties properties) {
        return new MySQLDataSourceProvider(properties).get();
    }

    @Bean
    public MetadataDAO mySqlMetadataDAO(ObjectMapper objectMapper, DataSource dataSource, MySQLProperties properties) {
        return new MySQLMetadataDAO(objectMapper, dataSource, properties);
    }

    @Bean
    public EventHandlerDAO mySqlEventHandlerDAO(ObjectMapper objectMapper, DataSource dataSource,
        MySQLProperties properties) {
        return new MySQLMetadataDAO(objectMapper, dataSource, properties);
    }

    @Bean
    public ExecutionDAO mySqlExecutionDAO(ObjectMapper objectMapper, DataSource dataSource) {
        return new MySQLExecutionDAO(objectMapper, dataSource);
    }

    @Bean
    public RateLimitingDAO mySqlRateLimitingDAO(ObjectMapper objectMapper, DataSource dataSource) {
        return new MySQLExecutionDAO(objectMapper, dataSource);
    }

    @Bean
    public PollDataDAO mySqlPollDataDAO(ObjectMapper objectMapper, DataSource dataSource) {
        return new MySQLExecutionDAO(objectMapper, dataSource);
    }

    @Bean
    public QueueDAO mySqlQueueDAO(ObjectMapper objectMapper, DataSource dataSource) {
        return new MySQLQueueDAO(objectMapper, dataSource);
    }
}