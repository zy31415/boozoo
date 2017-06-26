package io.github.zy31415.boozoo.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by zy on 6/25/17.
 */

public class EmProvider {

    private static final EmProvider singleton = new EmProvider();

    public static final boolean DEBUG = true;

    private EntityManagerFactory emf;

    private EmProvider () {}

    public static EmProvider getInstance() {
        return singleton;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("BoozooDB");
        }

        if (DEBUG) {
            System.out.println("factory created on: " + new Date());
        }

        return emf;
    }

    public void closeEmf() {
        if (emf.isOpen() || emf != null) {
            emf.close();
        }

        emf = null;

        if (DEBUG) {
            System.out.println("EMF closed at: " + new Date());
        }
    }
}
