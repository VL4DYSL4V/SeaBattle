package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtils {

    private static final SessionFactory SESSION_FACTORY;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        SESSION_FACTORY = configuration.buildSessionFactory();
    }

    public static Session getSession(){
        return SESSION_FACTORY.openSession();
    }
}
