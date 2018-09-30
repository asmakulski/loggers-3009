package com.infoshareacademy.loggers.workshop06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.function.Supplier;

public class Slf4jLogger {

    private static final Random RANDOM_GENERATOR = new Random();
    private static final int NO_ARGS_DEFINED = 0;
    private static final int ENGINES_ARGS_INDEX = 0;
    private static final Logger LOG = LoggerFactory.getLogger(Slf4jLogger.class);

    private static int numberOfActiveEngines = 0;

    public static void main(final String[] args) {

        if (args.length == NO_ARGS_DEFINED) {
            LOG.error("No engines found! Spacecraft grounded.");
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
                LOG.warn("Problem with engine " + engineNumber + ", please check again!");
                restartEngine(engineNumber);
            }
        }

        LOG.info("Number of active engines: " + numberOfActiveEngines + '/' + numberOfSpacecraftEngines);
    }

    private static boolean startEngine(final int engineNumber, final boolean firstAttempt) {
        LOG.debug("Starting engine: " + engineNumber);

        final boolean engineStarted = RANDOM_GENERATOR.nextBoolean();
        Supplier<String> engineStatusSupplier = () -> "Engine " + engineNumber + (engineStarted ? "" : " not") +  " started" ;

        LOG.info(engineStatusSupplier.get());

        if (!engineStarted && !firstAttempt) {
            LOG.error("Engine {} is not working. Mission failed!", engineNumber);
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
