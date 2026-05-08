package com.practicesoftwaretesting.data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;

public class DataGenerator {
    private final Faker faker = new Faker();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public InvalidLoginData invalidLoginDataGenerator(){
        InvalidLoginData data = new InvalidLoginData();
        data.email = faker.internet().emailAddress();
        data.password = faker.internet().password(5,6);
        return data;
    }

    public RegistrationData registrationData(){
        RegistrationData data = new RegistrationData();
        data.firstName = faker.address().firstName();
        data.lastName = faker.address().lastName();
        data.dateOfBirth = sdf.format(faker.date().birthday(18, 70));
        data.postalCode = faker.address().zipCode();
        data.houseNumber = String.valueOf(faker.number().numberBetween(1,100));
        data.phoneNumber = faker.numerify("#########");
        data.email = faker.internet().emailAddress();
        data.password = faker.internet().password(10,11,true,true,true);
        data.street = faker.address().streetName();
        data.city = faker.address().cityName();
        data.state = faker.address().state();
        return data;
    }
}