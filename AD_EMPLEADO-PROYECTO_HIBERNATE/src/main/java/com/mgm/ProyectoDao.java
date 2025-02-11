/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario14
 */
public class ProyectoDao {
    public static Integer addProyecto(Proyecto pro){
        Transaction transaction = null;
        Integer id = null;
        
        try(Session session = Conexion.getSession()){
            transaction = session.beginTransaction();
            session.persist(pro);
            transaction.commit();
            id = pro.getId();
        }catch(Exception e){
            System.err.println("Error al agregar el proyecto: " + e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        }
        return id;
    }
}
