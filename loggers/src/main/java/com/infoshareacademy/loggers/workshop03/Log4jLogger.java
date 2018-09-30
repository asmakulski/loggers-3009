package com.infoshareacademy.loggers.workshop03;

import java.util.Random;
import java.util.function.Supplier;

public class Log4jLogger {

    private static final Random RANDOM_GENERATOR = new Random();
    private static final int NO_ARGS_DEFINED = 0;
    private static final int ENGINES_ARGS_INDEX = 0;

    private static int numberOfActiveEngines = 0;

    public static void main(final String[] args) {

        if (args.length == NO_ARGS_DEFINED) {
            // LOGGER.("No engines found! Spacecraft grounded.");
            System.exit(-1);
        }

        final int numberOfSpacecraftEngines = Integer.parseInt(args[ENGINES_ARGS_INDEX]);
        // LOGGER.("Starting " + numberOfSpacecraftEngines + " spacecraft engines ...");

        for (int engineNumber = 1; engineNumber <= numberOfSpacecraftEngines; engineNumber++) {
            final boolean engineStarted = startEngine(engineNumber, true);

            if (engineStarted) {
                // LOGGER.("Engine " + engineNumber + " started successfully");
                activateFullPower(engineNumber);
            } else {
                // LOGGER.("Problem with engine " + engineNumber + ", please check again!");
                restartEngine(engineNumber);
            }
        }

        // LOGGER.("Number of active engines: " + numberOfActiveEngines + '/' + numberOfSpacecraftEngines);
    }

    private static boolean startEngine(final int engineNumber, final boolean firstAttempt) {
        // LOGGER.("Starting engine: " + engineNumber);

        final boolean engineStarted = RANDOM_GENERATOR.nextBoolean();
        Supplier<String> engineStatusSupplier = () -> "Engine " + engineNumber + (engineStarted ? "" : " not") +  " started" ;

        // LOGGER.(engineStatusSupplier);

        if (!engineStarted && !firstAttempt) {
            // LOGGER.("Engine " + engineNumber + " is not working. Mission failed!");
        }
        return engineStarted;
    }

    private static void activateFullPower(final int engineNumber) {
        // LOGGER.("Activating full power on engine: " + engineNumber);
        numberOfActiveEngines++;
    }

    private static void restartEngine(final int engineNumber) {
        // LOGGER.("Restarting engine: " + engineNumber);
        startEngine(engineNumber, false);
    }
}
