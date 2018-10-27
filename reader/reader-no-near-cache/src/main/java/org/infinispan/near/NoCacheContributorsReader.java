package org.infinispan.near;

import org.infinispan.reader.ContributorsReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NoCacheContributorsReader extends ContributorsReader {

   public static void main(String[] args) {
      SpringApplication.run(NoCacheContributorsReader.class, args);
   }

}