package org.infinispan.common;

import java.io.Serializable;

public class Contributor implements Serializable {
   private int code;
   private String name;

   public Contributor(Integer code, String name) {
      this.code = code;
      this.name = name;
   }

   @Override
   public String toString() {
      return code + " " + name;
   }
}
