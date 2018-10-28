package org.infinispan.near;

import org.infinispan.client.hotrod.configuration.NearCacheMode;
import org.infinispan.reader.AppConfig;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NearAppConfig extends AppConfig {

   @Bean
   public InfinispanRemoteCacheCustomizer customizer() {
      return b -> b.nearCache().maxEntries(30).mode(NearCacheMode.INVALIDATED).validate();
   }
}
