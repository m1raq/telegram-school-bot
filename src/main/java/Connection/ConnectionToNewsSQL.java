package Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConnectionToNewsSQL {


    private static final Session session = initializeSession();

    private static Session initializeSession(){

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("news.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory.openSession();
    }


    public static Session connection(){
        return session;
    }


}
