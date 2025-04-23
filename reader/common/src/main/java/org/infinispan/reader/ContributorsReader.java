package org.infinispan.reader;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.logging.Logger;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.common.Contributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class ContributorsReader implements CommandLineRunner {

   private Logger logger = Logger.getLogger(ContributorsReader.class.getName());

   @Autowired
   private RemoteCache<Integer, Contributor> contributors;

   @Override
   public void run(String... args) {
      logger.info("=========================================");
      logger.info("            READ CONTRIBUTORS            ");
      logger.info("=========================================");
      Random random = new Random();
      Instant start = Instant.now();
      random.ints(10_000, 0, 27).forEach(id -> {
         Contributor contributor = contributors.get(id);
         logger.info("Contributor " + contributor);
      });
      Instant finish = Instant.now();
      long timeElapsed = Duration.between(start, finish).toMillis();
      logger.info(String.format("Completed in %sms", timeElapsed));
      logger.info("=========================================");
   }
}