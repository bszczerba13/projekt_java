package com.practicesoftwaretesting.data;

import com.practicesoftwaretesting.utils.CsvReader;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() throws Exception {
        return CsvReader.getCsvData("src/test/resources/users.csv");
    }
}
