package com.studies.domain.utils;

import java.util.Random;

public class RandomStringGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int size) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            builder.append(CHARACTERS.charAt(randomIndex));
        }

        return builder.toString();
    }
}
