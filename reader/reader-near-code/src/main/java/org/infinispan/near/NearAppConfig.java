package org.infinispan.near;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.NearCacheMode;
import org.infinispan.common.AppData;
import org.infinispan.common.Contributor;
import org.infinispan.common.ContributorSchemaImpl;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.reader.AppConfig;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NearAppConfig extends AppConfig {

   @Value("${demo.max-entries}")
   private Integer maxEntries;

   @Bean
   @Qualifier(AppData.I8N_CONTRIBUTORS_CACHE_NAME)
   public RemoteCache<Integer, Contributor> contributorsCache(RemoteCacheManager cacheManager) {
      return cacheManager.getCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME);
   }

   @Bean
   public InfinispanRemoteCacheCustomizer customizer() {
      return b -> b
              .addContextInitializer(new ContributorSchemaImpl())
              .remoteCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME)
                  .marshaller(ProtoStreamMarshaller.class)
                  .nearCacheMaxEntries(maxEntries)
                  .nearCacheMode(NearCacheMode.INVALIDATED)
              .validate();
   }
}
