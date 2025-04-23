package org.infinispan.writer;

import org.infinispan.common.AppData;
import org.infinispan.common.ContributorSchemaImpl;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class InfinispanConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public InfinispanRemoteCacheCustomizer configuration() {
        URI cacheConfigUri = loadConfigURI();

        return b ->{
                b.addContextInitializer(new ContributorSchemaImpl());
                b.marshaller(ProtoStreamMarshaller.class)
                        .remoteCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME)
                        .configurationURI(cacheConfigUri);
        };
    }

    private URI loadConfigURI() {
        URI cacheConfigUri;
        try {
            cacheConfigUri = this.getClass().getClassLoader().getResource("contributors.json").toURI();
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
        return cacheConfigUri;
    }
}
