package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
  private static Properties properties;

  public static String getTestLogin() {
    return getProperties().getProperty("testLogin");
  }

  public static String getDefaultPassword() {
    return getProperties().getProperty("testPassword");
  }

  public static String getGNCBaseUrl() {
    return getProperties().getProperty("gncBaseUrl");
  }

  public static long getTimeout() {
    return Long.parseLong(getProperties().getProperty("timeOutInMilliseconds"));
  }

  public static String getRecentlyViewedCookieName() {
    return getProperties().getProperty("recentlyViewedCookie");
  }

  private static Properties getProperties() {
    if (properties == null) {
      properties = new Properties();
      try {
        properties.load(new FileInputStream("test/Properties.properties"));
      } catch (IOException e) {
        System.err.print(e);
      }
    }
    return properties;
  }
}
