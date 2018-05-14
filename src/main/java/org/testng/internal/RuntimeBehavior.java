package org.testng.internal;

import java.util.TimeZone;

/**
 * This class houses handling all JVM arguments by TestNG
 */
public final class RuntimeBehavior {

    public static final String TESTNG_LISTENERS_ALWAYSRUN = "testng.listeners.alwaysrun";
    public static final String TESTNG_THREAD_AFFINITY = "testng.thread.affinity";
    public static final String TESTNG_MODE_DRYRUN = "testng.mode.dryrun";

    private RuntimeBehavior() {
    }

    /**
     * @return - returns <code>true</code> if we would like to run in the Dry mode and
     * <code>false</code> otherwise.
     */
    public static boolean isDryRun() {
        String value = System.getProperty(TESTNG_MODE_DRYRUN, "false");
        return Boolean.parseBoolean(value);
    }

    /**
     * @return - returns the {@link TimeZone} corresponding to the JVM argument
     * <code>-Dtestng.timezone</code> if it was set. If not set, it returns the default
     * timezone pertaining to the user property <code>user.timezone</code>
     */
    public static TimeZone getTimeZone() {
        String timeZone = System.getProperty("testng.timezone", "");
        if (timeZone.trim().isEmpty()) {
            return TimeZone.getDefault();
        }
        return TimeZone.getTimeZone(timeZone);
    }

  /**
   * @return - <code>true</code> if we would like to invoke applicable listeners for tests that
   *     would be skipped by TestNG due to a configuration failure (or) due to a upstream dependency
   *     failure.
   */
  public static boolean invokeListenersForSkippedTests() {
    return Boolean.parseBoolean(System.getProperty(TESTNG_LISTENERS_ALWAYSRUN, "false"));
  }

  /**
   * @return - <code>true</code> if we would like to enforce Thread affinity when dealing with the
   *     below two variants of execution models:
   *     <ul>
   *       <li>Ordering priority
   *       <li>Ordering by dependsOnMethods (will not work with dependency on multiple methods)
   *     </ul>
   */
  public static boolean enforceThreadAffinity() {
    return Boolean.parseBoolean(System.getProperty(TESTNG_THREAD_AFFINITY, "false"));
  }
}
