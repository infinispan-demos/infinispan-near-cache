package org.infinispan.reader;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.common.AppData;
import org.infinispan.common.Contributor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

      @Bean
      @Qualifier(AppData.I8N_CONTRIBUTORS_CACHE_NAME)
      public RemoteCache<Integer, Contributor> contributorsCache(RemoteCacheManager cacheManager) {
         return cacheManager.getCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME);
      }
}
