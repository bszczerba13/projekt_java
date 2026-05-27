package com.practicesoftwaretesting.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReport() {

        if (extent == null) {

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter("reports/report.html");

            reporter.config().setReportName("Practice Software Testing");
            reporter.config().setDocumentTitle("Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Framework", "Selenium + TestNG");
        }

        return extent;
    }
}