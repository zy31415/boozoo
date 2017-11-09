package io.github.zy31415.boozoo.database;

import com.google.common.collect.ImmutableMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by zy on 6/25/17.
 */

public class BoozooEMF {

    private BoozooEMF() {}

    final static Logger logger = LogManager.getLogger();

    private static final String BOOZOOROOT = getBOOZOOROOT();
    private static final String DbUrl = getDbUrl();

    private static String getBOOZOOROOT() {
        String booZooRoot = System.getenv("BOOZOOROOT");

        if (null == booZooRoot) {
            booZooRoot = "~/.boozoo/";
        }

        logger.info("BOOZOOROOT is {}", booZooRoot);
        return booZooRoot;

    }

    private static String getDbUrl() {
        Path dbPath = Paths.get(BOOZOOROOT, "db", "boozoo.h2");
        String dbUrl = String.format("jdbc:h2:%s", dbPath.toString());
        logger.info("Database URL is {}", dbUrl);
        return dbUrl;
    }

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "BoozooDB",
            ImmutableMap.of("hibernate.hikari.dataSource.url", DbUrl));

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
        emf = null;
    }
}
