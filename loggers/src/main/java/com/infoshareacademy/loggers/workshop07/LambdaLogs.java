package com.infoshareacademy.loggers.workshop07;

import com.infoshareacademy.loggers.workshop06.Slf4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaLogs {

    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jLogger.class);

    public static void main(String[] args) {

        final List<Integer> numbers = IntStream.rangeClosed(1, 10).boxed()
                .peek(number -> LOGGER.debug("Number: " + number))
            .filter(number -> number % 2 == 0)
                .peek(number -> LOGGER.debug("Even: " + number))
            .map(number -> number * 10)
                .peek(number -> LOGGER.debug("Multiplied: " + number))
            .collect(Collectors.toList());
        LOGGER.info("Final numbers: {}", numbers);
    }
}