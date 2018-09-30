package com.infoshareacademy.loggers.workshop01;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SystemLogger {

    private static final int MINIMAL_COUNTDOWN_VALUE = 0;
    private static final int COUNTDOWN_INTERVAL = 1;

    public static void main(final String[] args) throws InterruptedException {
        System.out.println("Welcome to ISA Spaceship program");

        final Scanner in = new Scanner(System.in);

        System.out.println("Please enter countdown value:");
        final int countdown = in.nextInt();
        System.out.println("The countdown value is "+countdown);

        if (countdown <= MINIMAL_COUNTDOWN_VALUE) {
            System.err.println("You have entered value greater than 0. The application will exit");
            System.exit(-1);
        }

        System.out.println("Starting countdown...");
        for (int number = countdown; number > MINIMAL_COUNTDOWN_VALUE; number--) {
            System.out.println(number);
            TimeUnit.SECONDS.sleep(COUNTDOWN_INTERVAL);
        }
        System.out.println("Launch!");
    }
}