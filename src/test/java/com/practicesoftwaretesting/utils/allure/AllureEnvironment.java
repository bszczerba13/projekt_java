package com.practicesoftwaretesting.utils.allure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class responsible for creating the
 * environment.properties file for the Allure report.
 *
 * <p>The environment information is generated once before the test
 * suite execution. Creating this file must never affect test execution.</p>
 */
public final class AllureEnvironment {

    /**
     * Prevent instantiation of utility class.
     */
    private AllureEnvironment() {
        throw new UnsupportedOperationException(
                "This is a utility class and cannot be instantiated."
        );
    }

    /**
     * Creates the environment.properties file
     * used by the Allure report.
     */
    public static void writeEnvironment() {

        Properties properties = new Properties();

        properties.setProperty("Browser", "Chrome");
        properties.setProperty("Java", System.getProperty("java.version"));
        properties.setProperty("OS", System.getProperty("os.name"));

        try (FileOutputStream output =
                     new FileOutputStream("allure-results/environment.properties")) {

            properties.store(output, "Allure Environment");

        } catch (IOException e) {
            // Reporting must never affect test execution.
        }
    }
}