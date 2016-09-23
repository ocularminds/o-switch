package com.ocularminds.oswitch.common;

import java.security.SecureRandom;

public final class Identifier {

  private Type type;

  public enum Type {
    LONG, SHORT;
  }

  public Identifier() {
    this(Type.SHORT);
  }

  public Identifier(final Type typ) {
    type = typ;
  }

  public String next() throws Exception {
    String number = "";
    SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
    if (type == Type.LONG) {
      number =
          String.format("%020d",
              new Object[] {Long.valueOf((new Long(Math.abs(random.nextLong()))).longValue())});
    } else {
      number =
          String.format("%010d",
              new Object[] {Integer.valueOf((new Integer(Math.abs(random.nextInt()))).intValue())});
    }
    return number;
  }

}
