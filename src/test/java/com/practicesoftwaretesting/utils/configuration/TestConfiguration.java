package com.practicesoftwaretesting.utils.configuration;

/**
 * Utility class responsible for providing
 * runtime configuration for test execution.
 *
 * <p>Configuration values are read from JVM system
 * properties. If a property is not provided,
 * a default value is used.</p>
 */
public final class TestConfiguration {

    /**
     * Prevent instantiation of utility class.
     */
    private TestConfiguration() {
        throw new UnsupportedOperationException(
                "This is a utility class and cannot be instantiated."
        );
    }

    /**
     * Returns the browser configured for test execution.
     *
     * @return browser name
     */
    public static String getBrowser() {
        return System.getProperty("browser", "chrome");
    }

    /**
     * Returns whether the browser should run in headless mode.
     *
     * @return true if headless mode is enabled
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );
    }

    /**
     * Returns the base URL configured for test execution.
     *
     * @return application base URL
     */
    public static String getBaseUrl() {
        return System.getProperty(
                "baseUrl",
                "https://practicesoftwaretesting.com"
        );
    }

    public static Environment getEnvironment() {
        String environment = System.getProperty("environment", "local");

        return switch (environment.toLowerCase()) {
            case "docker" -> Environment.DOCKER;
            case "ci" -> Environment.CI;
            case "local" -> Environment.LOCAL;
            default -> throw new IllegalArgumentException(
                    "Unsupported environment: " + environment
            );
        };
    }

}