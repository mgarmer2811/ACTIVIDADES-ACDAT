package com.mgm;
import java.io.File;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Usuario14
 */
public class Conexion {
    private static Configuration configuration = null;
    private static SessionFactory sessionFactory = null;
    private static Session session = null;
    
    private static void init(){
        if(configuration == null){
            configuration = new Configuration().configure(new File("hibernate.cfg.xml"));
            configuration.addAnnotatedClass(Autor.class);
            sessionFactory = configuration.buildSessionFactory();
        }
    }
    
    public static Session getSession(){
        if (session == null || !session.isOpen()){
            init();
            session = sessionFactory.openSession();
        }
        return session;
    }
    
    public static void close(){
        session.close();
        sessionFactory.close();
    }
}