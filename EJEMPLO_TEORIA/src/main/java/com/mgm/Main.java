/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Usuario14
 */
public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure(new File("hibernate.cfg.xml"));
        configuration.addAnnotatedClass(Cliente.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        
        try{
//            transaction = session.beginTransaction();
//            Cliente cliente = new Cliente("Carlos Martinez","carlos@gmail.com","2024-01-16",null);
//            session.persist(cliente);
//            transaction.commit();
//            System.out.println("Cliente guardado correctamente");

//            transaction = session.beginTransaction();
//            Cliente cliente = new Cliente();
//            cliente.setId(1);
//            cliente.setNombre("Francisquito");
//            cliente.setEmail("carlos@gmail.com");
//            session.merge(cliente);
//            transaction.commit();
//            System.out.println("cliente modificado correctamente");

             
        }
        catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally{
            session.close();
            sessionFactory.close();
        }
    }
}
