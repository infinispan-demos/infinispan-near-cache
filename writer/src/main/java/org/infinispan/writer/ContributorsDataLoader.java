package org.infinispan.writer;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.common.AppData;
import org.infinispan.common.Contributor;
import org.infinispan.common.ContributorSchemaImpl;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.logging.Logger;

@SpringBootApplication
public class ContributorsDataLoader {

   private Logger logger = Logger.getLogger(ContributorsDataLoader.class.getName());

   public static void main(String[] args) {
      SpringApplication.run(ContributorsDataLoader.class, args);
   }

   @Bean
   public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
      return args -> {

         RemoteCacheManager remoteCacheManager = ctx.getBean(RemoteCacheManager.class);
         logger.info("=========================================");
         logger.info("            LOAD CONTRIBUTORS            ");
         logger.info("=========================================");

         // Register the schema. New Api coming in 16.0
         RemoteCache<String, String> metadataCache = remoteCacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
         GeneratedSchema schema = new ContributorSchemaImpl();
         metadataCache.put(schema.getProtoFileName(), schema.getProtoFile());

         // The cache has been created on first access, after configuring it in the InfinispanConfiguration
         RemoteCache<Integer, Contributor> contributors = remoteCacheManager.getCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME);
         contributors.clear();

         logger.info(AppData.I8N_CONTRIBUTORS_CACHE_NAME + " cache cleanup");

         contributors.putAll(AppData.getContributors());

         logger.info(AppData.I8N_CONTRIBUTORS_CACHE_NAME + " cache has been loaded");
         logger.info(AppData.I8N_CONTRIBUTORS_CACHE_NAME + " cache has size " + contributors.size());
         logger.info("=========================================");

         contributors.stop();
         remoteCacheManager.stop();
      };
   }
}
