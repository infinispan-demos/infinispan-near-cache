package org.infinispan.common;

import java.util.HashMap;
import java.util.Map;

public final class AppData {

   public static final String I8N_CONTRIBUTORS_CACHE_NAME = "i8n-contributors";

   private AppData() {

   }

   public static Map<Integer, Contributor> getContributors() {
      Map<Integer, Contributor> contributors = new HashMap<>();

      contributors.put(0, new Contributor(0, "Tristan Tarrant"));
      contributors.put(1, new Contributor(1, "Galder Zamarreño"));
      contributors.put(2, new Contributor(2, "Dan Berindei"));
      contributors.put(3, new Contributor(3, "Manik Surtani"));
      contributors.put(4, new Contributor(4, "Sanne Grinovero"));
      contributors.put(5, new Contributor(5, "Adrian Nistor"));
      contributors.put(6, new Contributor(6, "Mircea Markus"));
      contributors.put(7, new Contributor(7, "Will Burns"));
      contributors.put(8, new Contributor(8, "Gustavo Fernandes"));
      contributors.put(9, new Contributor(9, "Pedro Ruivo"));
      contributors.put(10, new Contributor(10, "Vladimir Blagojevic"));
      contributors.put(11, new Contributor(11, "Radim Vansa"));
      contributors.put(12, new Contributor(12, "Ryan Emerson"));
      contributors.put(13, new Contributor(13, "Sebastian Łaskawiec"));
      contributors.put(14, new Contributor(14, "Vojtěch Juránek"));
      contributors.put(15, new Contributor(15, "Pete Muir"));
      contributors.put(16, new Contributor(16, "Martin Gencur"));
      contributors.put(17, new Contributor(17, "Steve Ebersole"));
      contributors.put(18, new Contributor(18, "Kevin Pollet"));
      contributors.put(19, new Contributor(19, "Anna Manukyan"));
      contributors.put(20, new Contributor(20, "Paul Ferraro"));
      contributors.put(21, new Contributor(21, "Marko Lukša"));
      contributors.put(22, new Contributor(22, "Noelo"));
      contributors.put(23, new Contributor(23, "Radoslav Husar"));
      contributors.put(24, new Contributor(24, "Katia Aresti"));
      contributors.put(25, new Contributor(25, "Adrian Cole"));

      return contributors;
   }
}
