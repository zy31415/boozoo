package io.github.zy31415.boozoo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Configuration {

    private static final Logger logger = LogManager.getLogger();

    public static final String BOOZOOROOT = getBOOZOOROOT();

    private static String getBOOZOOROOT() {
        String booZooRoot = System.getenv("BOOZOOROOT");
        if (null == booZooRoot) {
            booZooRoot = "~/.boozoo/";
        }
        logger.info("BOOZOOROOT is {}", booZooRoot);
        return booZooRoot;
    }
}
