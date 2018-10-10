package org.infinispan.writer;

import java.util.logging.Logger;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.common.AppData;
import org.infinispan.common.Contributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContributorsDataLoader implements CommandLineRunner {

   private Logger logger = Logger.getLogger(ContributorsDataLoader.class.getName());

   @Autowired
   private RemoteCacheManager cacheManager;

   @Override
   public void run(String... args) throws Exception {
      logger.info("=========================================");
      logger.info("            LOAD CONTRIBUTORS            ");
      logger.info("=========================================");
      RemoteCache<Integer, Contributor> contributors = cacheManager.administration()
            .getOrCreateCache(AppData.I8N_CONTRIBUTORS_CACHE_NAME, "default");
      logger.info(AppData.I8N_CONTRIBUTORS_CACHE_NAME + " cache has been created");
      contributors.putAll(AppData.getContributors());
      logger.info(AppData.I8N_CONTRIBUTORS_CACHE_NAME + " cache has been loaded");
      logger.info(AppData.I8N_CONTRIBUTORS_CACHE_NAME + " cache has size " + contributors.size());
      logger.info("=========================================");
   }

   public static void main(String[] args) {
      SpringApplication.run(ContributorsDataLoader.class, args);
   }
}
