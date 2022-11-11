package com.eriko.util;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory sesionFactory;

    static {
        try {
            Configuration configuration = new Configuration() ;
            configuration.configure();
            sesionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex){
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sesionFactory;
    }
}
