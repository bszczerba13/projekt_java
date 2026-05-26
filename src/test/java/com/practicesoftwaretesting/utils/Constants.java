package com.practicesoftwaretesting.utils;

public final class Constants {

    private Constants(){
        throw new UnsupportedOperationException("Utility class");
    }

    // Timeouts
    public static final long DEFAULT_TIMEOUT = 10;
    public static final long QUICK_TIMEOUT = 2;

    // Validation messages
    public static final String EMPTY_CART_MESSAGE = "The cart is empty";
    public static final String PAYMENT_SUCCESS_MESSAGE = "success";
    public static final String ORDER_CONFIRMATION_MESSAGE = "your invoice number";
    public static final String INVALID_LOGIN_MESSAGE = "Invalid email or password";
    public static final String MISSING_EMAIL_MESSAGE = "Email is required";

    // User roles
    public static final String ADMIN_ROLE = "admin";
    public static final String USER_ROLE = "user";

    // Account page titles
    public static final String ADMIN_PAGE_TITLE = "Sales over the years";
    public static final String USER_PAGE_TITLE = "My account";

    // Payment methods
    public static final String CREDIT_CARD = "credit-card";

    // Sorting

    public static final String SORT_PRICE_ASC = "price,asc";
    public static final String SORT_PRICE_DESC = "price,desc";
}
