package com.practicesoftwaretesting.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PasswordGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int PASSWORD_LENGTH = 12;
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*";

    private static final String ALL_CHARACTERS =
            LOWERCASE + UPPERCASE + DIGITS + SPECIAL;

    private PasswordGenerator() {
    }

    public static String generatePassword() {

        List<Character> password = new ArrayList<>();

        password.add(randomCharacter(LOWERCASE));
        password.add(randomCharacter(UPPERCASE));
        password.add(randomCharacter(DIGITS));
        password.add(randomCharacter(SPECIAL));

        while (password.size() < PASSWORD_LENGTH) {
            password.add(randomCharacter(ALL_CHARACTERS));
        }

        Collections.shuffle(password, RANDOM);

        StringBuilder result = new StringBuilder();

        for (Character character : password) {
            result.append(character);
        }

        return result.toString();
    }

    private static char randomCharacter(String characters) {
        return characters.charAt(RANDOM.nextInt(characters.length()));
    }
}
