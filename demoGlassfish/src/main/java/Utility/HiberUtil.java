package Utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HiberUtil {
    public static Session getSession(){
        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml")
                .buildSessionFactory();
        org.hibernate.Session s = sf.openSession();
        return s;
    }
}
