package com.practicesoftwaretesting.utils.retry;

import org.testng.ITestResult;
import org.testng.IRetryAnalyzer;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            return true;
        }

        return false;
    }
}