package org.infinispan.reader;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.common.AppData;
import org.infinispan.common.Contributor;
import org.infinispan.common.ContributorSchemaImpl;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

public class AppConfig {

   @Bean
   @Qualifier(AppData.I8N_CONTRIBUTORS_CACHE_NAME)
   public RemoteCache<Integer, Contributor> contributorsCache(RemoteCacheManager cacheManager) {
      return cacheManager.getCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME);
   }

   @Bean
   @Order(Ordered.HIGHEST_PRECEDENCE)
   public InfinispanRemoteCacheCustomizer configuration() {
      return b ->{
         b.addContextInitializer(new ContributorSchemaImpl());
         b.remoteCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME)
                 .marshaller(ProtoStreamMarshaller.class);
      };
   }
}
