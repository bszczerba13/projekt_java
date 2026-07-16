package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Catalog")
@Feature("Products filtering")
public class FilteringTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Verify products can be filtered by category")
    public void filteringByCategoryTest(){
        String category = "Hammer";
        homePage.filter.filterByCategory(category);
        List<String> productTitles = homePage.getProductTitles();
        for (String title : productTitles){
            Assert.assertTrue(title.contains(category.toLowerCase()));
        }
    }
}
