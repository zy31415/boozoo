package io.github.zy31415.boozoo.database;

import com.google.common.collect.ImmutableMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by zy on 6/25/17.
 */

public class BoozooEMF {

    private BoozooEMF() {}

    private static Integer port = 9123;

    public static void setPort(Integer port) {
        BoozooEMF.port = port;
    }

    static final Logger logger = LogManager.getLogger();

    private static String getDbUrl() {
        return String.format("jdbc:h2:tcp://localhost:%d/./boozoo.h5", port);
    }

    private static EntityManagerFactory emf = null;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (null == emf) {
            emf = Persistence.createEntityManagerFactory(
                    "BoozooDB",
                    ImmutableMap.of("hibernate.hikari.dataSource.url", getDbUrl()));
        }
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (null == emf){
            return;
        }
        if (emf.isOpen()) {
            emf.close();
        }
        emf = null;
    }
}
