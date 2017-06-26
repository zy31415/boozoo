package io.github.zy31415.boozoo.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by zy on 6/25/17.
 */

public class EmProvider2 {

    public static final boolean DEBUG = true;

    private static EntityManagerFactory emf;

    private EmProvider2() {}


    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("BoozooDB");
        }

        if (DEBUG) {
            System.out.println("factory created on: " + new Date());
        }

        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (emf.isOpen() || emf != null) {
            emf.close();
        }

        emf = null;

        if (DEBUG) {
            System.out.println("EMF closed at: " + new Date());
        }
    }
}
