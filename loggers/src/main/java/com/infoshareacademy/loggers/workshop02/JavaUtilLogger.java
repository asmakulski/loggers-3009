package com.infoshareacademy.loggers.workshop02;

import java.util.Random;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class JavaUtilLogger {

    private static final Random RANDOM_GENERATOR = new Random();
    private static final int NO_ARGS_DEFINED = 0;
    private static final int ENGINES_ARGS_INDEX = 0;
    private static final Logger LOG = Logger.getLogger(JavaUtilLogger.class.getName());

    private static int numberOfActiveEngines = 0;

    public static void main(final String[] args) {

        if (args.length == NO_ARGS_DEFINED) {
            LOG.severe("No engines found! Spacecraft grounded.");
            System.exit(-1);
        }

        final int numberOfSpacecraftEngines = Integer.parseInt(args[ENGINES_ARGS_INDEX]);
        LOG.info("Starting " + numberOfSpacecraftEngines + " spacecraft engines ...");

        for (int engineNumber = 1; engineNumber <= numberOfSpacecraftEngines; engineNumber++) {
            final boolean engineStarted = startEngine(engineNumber, true);

            if (engineStarted) {
                LOG.info("Engine " + engineNumber + " started successfully");
                activateFullPower(engineNumber);
            } else {
                LOG.warning("Problem with engine " + engineNumber + ", please check again!");
                restartEngine(engineNumber);
            }
        }

        LOG.info("Number of active engines: " + numberOfActiveEngines + '/' + numberOfSpacecraftEngines);
    }

    private static boolean startEngine(final int engineNumber, final boolean firstAttempt) {
        LOG.info("Starting engine: " + engineNumber);

        final boolean engineStarted = RANDOM_GENERATOR.nextBoolean();
        Supplier<String> engineStatusSupplier = () -> "Engine " + engineNumber + (engineStarted ? "" : " not") +  " started" ;

        LOG.info(engineStatusSupplier);

        if (!engineStarted && !firstAttempt) {
            LOG.info("Engine " + engineNumber + " is not working. Mission failed!");
        }
        return engineStarted;
    }

    private static void activateFullPower(final int engineNumber) {
        LOG.info("Activating full power on engine: " + engineNumber);
        numberOfActiveEngines++;
    }

    private static void restartEngine(final int engineNumber) {
        LOG.info("Restarting engine: " + engineNumber);
        startEngine(engineNumber, false);
    }
}
