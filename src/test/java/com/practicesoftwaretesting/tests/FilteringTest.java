package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FilteringTest extends BaseTest {

    @Test
    public void filteringByCategoryTest(){
        String category = "Hammer";
        homePage.filter.filterByCategory(category);
        List<String> productTitles = homePage.getProductTitles();
        for (String title : productTitles){
            Assert.assertTrue(title.contains(category.toLowerCase()));
        }
    }
}
