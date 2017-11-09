package io.github.zy31415.boozoo.database;

import com.google.common.collect.ImmutableMap;
import com.sun.media.jfxmedia.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by zy on 6/25/17.
 */

public class BoozooEMF {

    private BoozooEMF() {}

    private static final String BOOZOOROOT = getBOOZOOROOT();
    private static final String DbUrl = getDbUrl();

    private static String getBOOZOOROOT() {
        String envBOOZOOROOT = System.getenv("BOOZOOROOT");

        if (null == envBOOZOOROOT) {
            return "~/.boozoo/";
        } else {
            return envBOOZOOROOT;
        }
    }

    private static String getDbUrl() {
        Path dbPath = Paths.get(BOOZOOROOT, "db", "boozoo.h2");
        return String.format("jdbc:h2:%s", dbPath.toString());
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
