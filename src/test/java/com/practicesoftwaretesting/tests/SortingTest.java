package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class SortingTest extends BaseTest {

    @Test(description = "Verify products can be sorted by price ascending")
    public void sortPriceLowToHighTest(){
        homePage.sortPriceLowToHigh();
        List<Double> prices = homePage.getProductPrices();
        List<Double> sortedPrices = prices.stream().sorted().toList();
        Assert.assertEquals(prices,sortedPrices);
    }

    @Test(description = "Verify products can be sorted by price descending")
    public void sortPriceHighToLowTest(){
        homePage.sortPriceHighToLow();
        List<Double> prices = homePage.getProductPrices();
        List<Double> sortedPrices = prices.stream().sorted(Comparator.reverseOrder()).toList();
        Assert.assertEquals(prices,sortedPrices);
    }
}
