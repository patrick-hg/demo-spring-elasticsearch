package com.keywer.article.demospringelasticsearch.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utils {

    private static Random rand = new Random();

    private static final long aDay = TimeUnit.DAYS.toMillis(1);

    public static int randomNum(int max) {
        return rand.nextInt(max);
    }

    public static Date oneYearAgo() {
        return new Date(new Date().getTime() - aDay * 365);
    }

    public static Date randomDateInRange(int days) {
        long now = new Date().getTime();
        return new Date(now - aDay * randomNum(days));
    }

    private final static List<String> localizationSamples = Arrays.asList("Paris, France", "Lyon, France", "Marseille, France", "Rome, Italie", "Berlin, Allemagne", "Londres, Angletaire");
}
