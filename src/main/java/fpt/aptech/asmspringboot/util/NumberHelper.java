package fpt.aptech.asmspringboot.util;

import java.util.Random;

public class NumberHelper {
    public static int generateRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}