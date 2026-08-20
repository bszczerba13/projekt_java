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

        String baseUrl = System.getProperty("baseUrl");

        if (baseUrl != null && !baseUrl.isBlank()) {
            return baseUrl;
        }

        return switch (getEnvironment()) {
            case LOCAL -> "https://practicesoftwaretesting.com";
            case DOCKER -> "http://angular-ui:4200";
            case CI -> "http://toolshop:4200";
        };
    }

    /**
     * Returns the execution environment.
     *
     * @return configured execution environment
     */
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