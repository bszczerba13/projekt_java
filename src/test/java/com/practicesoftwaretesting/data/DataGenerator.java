package com.practicesoftwaretesting.data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Generates random test data using Faker library.
 */
public class DataGenerator {
    private final Faker faker = new Faker();

    /**
     * Generates invalid login data.
     *
     * @return InvalidLoginData object
     */
    public InvalidLoginData invalidLoginDataGenerator(){
        InvalidLoginData data = new InvalidLoginData();
        data.email = faker.internet().emailAddress();
        data.password = faker.internet().password(5,6);
        return data;
    }

    /**
     * Generates valid registration data.
     *
     * @return RegistrationData object
     */
    public RegistrationData registrationData(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

    /**
     * Generates random checkout data.
     *
     * @return OrderData object
     */
    public OrderData orderData(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        OrderData data = new OrderData();
        data.firstName = faker.address().firstName();
        data.lastName = faker.address().lastName();
        data.email = faker.internet().emailAddress();
        data.postalCode = faker.address().zipCode();
        data.houseNumber = String.valueOf(faker.number().numberBetween(1,100));
        data.creditCardNumber = faker.numerify("####-####-####-####");
        data.expirationDate = sdf.format(faker.date().future(1000, TimeUnit.DAYS));
        data.cvv = faker.numerify("###");
        data.street = faker.address().streetName();
        data.city = faker.address().cityName();
        data.state = faker.address().state();
        return data;
    }
}