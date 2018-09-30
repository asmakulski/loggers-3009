package com.infoshareacademy.loggers.workshop07;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaLogs {

    public static void main(String[] args) {

        final List<Integer> numbers = IntStream.rangeClosed(1, 10).boxed()
//                LOGGER.debug("Number: " + number)
            .filter(number -> number % 2 == 0)
//                LOGGER.debug("Even: " + number)
            .map(number -> number * 10)
//                LOGGER.debug("Multiplied: " + number)
            .collect(Collectors.toList());

//        LOGGER.info("Final numbers: {}", numbers);
    }
}