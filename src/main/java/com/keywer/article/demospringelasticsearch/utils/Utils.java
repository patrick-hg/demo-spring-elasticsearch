package com.keywer.article.demospringelasticsearch.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Utils {

    private static Random rand = new Random();

    private static final long aDay = TimeUnit.DAYS.toMillis(1);

    public static int randomNum(int max) {
        return rand.nextInt(max);
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static Date oneYearAgo() {
        return new Date(new Date().getTime() - aDay * 365);
    }

    public static String randomDateInRange(int days) {
        long now = new Date().getTime();
        return dateFormat.format(new Date(now - aDay * randomNum(days)));
    }

    public static List tagsFromText(String text) {
        String[] tags = text.split(" ");
        return Arrays.stream(tags)
                .filter(s -> s.startsWith("#"))
                .map(s -> s.substring(1))
                .collect(Collectors.toList());
    }

    public static Pageable pageableOf(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            return Pageable.unpaged();
        }
        return PageRequest.of(pageNum, pageSize);
    }
}
