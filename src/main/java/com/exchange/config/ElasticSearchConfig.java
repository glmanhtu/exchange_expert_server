package com.exchange.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by optimize on 2/8/17.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.exchange.backend.persistence.repositories.elasticsearch")
public class ElasticSearchConfig {
    @Value("${spring.data.elasticsearch.host}")
    private String host;

    @Value("${spring.data.elasticsearch.port}")
    private Integer port;

    @Value("${spring.data.elasticsearch.cluster}")
    private String cluster;

    @Value("${spring.data.elasticsearch.node}")
    private String node;

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }

    @Bean
    public Client client() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", cluster)
                .put("node.name", node).build();
        return TransportClient.builder().settings(settings).build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName(host), port)
                );
    }

}
