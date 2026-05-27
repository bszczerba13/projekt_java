package com.practicesoftwaretesting.utils;

import com.aventstack.extentreports.*;
import org.testng.*;

public class TestListener implements ITestListener {

    private static ExtentReports extent =
            ReportManager.getReport();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();


    @Override
    public void onTestStart(ITestResult result) {
        test.set(
                extent.createTest(
                        result.getMethod().getMethodName(),
                        result.getMethod().getDescription()
                )
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}