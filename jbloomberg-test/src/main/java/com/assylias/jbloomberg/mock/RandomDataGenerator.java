package com.assylias.jbloomberg.mock;

import java.util.Random;

public class RandomDataGenerator {
    private static final Random _rand = new Random(100);

    public static int strike() {
        return RandomDataGenerator.randomInt(20) * 5;
    }

    public static int randomInt(int max) {
        return RandomDataGenerator._rand.nextInt(max);
    }

    private static char randomChar() {
        return (char) ('A' + RandomDataGenerator._rand.nextInt(24) - 1);
    }

    public static String randomString(int len) {
        StringBuilder result = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            result.append(RandomDataGenerator.randomChar());
        }
        return result.toString();
    }
}
