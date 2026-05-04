package com.practicesoftwaretesting.data;

import com.github.javafaker.Faker;

public class DataGenerator {
    private Faker faker = new Faker();

    public InvalidLoginData invalidLoginDataGenerator(){
        InvalidLoginData data = new InvalidLoginData();
        data.email = faker.internet().emailAddress();
        data.password = faker.internet().password(5,6);
        return data;
    }
}
