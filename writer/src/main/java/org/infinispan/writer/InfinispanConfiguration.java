package org.infinispan.writer;

import java.util.logging.Logger;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.common.AppData;
import org.infinispan.common.Contributor;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class InfinispanConfiguration {

    private Logger logger = Logger.getLogger(ContributorsDataLoader.class.getName());

    @Autowired
    private RemoteCacheManager remoteCacheManager;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public InfinispanRemoteCacheCustomizer caches() {
        return b -> {
            b.remoteCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME);
        };
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE - 1)
    public void loadData() {
        logger.info("=========================================");
        logger.info("            LOAD CONTRIBUTORS            ");
        logger.info("=========================================");

        RemoteCache<Integer, Contributor> contributors = remoteCacheManager
            .administration().getOrCreateCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME, "org.infinispan.LOCAL");
        contributors.clear();

        logger.info(AppData.I8N_CONTRIBUTORS_CACHE_NAME + " cache cleanup");

        contributors.putAll(AppData.getContributors());

        logger.info(AppData.I8N_CONTRIBUTORS_CACHE_NAME + " cache has been loaded");
        logger.info(AppData.I8N_CONTRIBUTORS_CACHE_NAME + " cache has size " + contributors.size());
        logger.info("=========================================");
    }
}
