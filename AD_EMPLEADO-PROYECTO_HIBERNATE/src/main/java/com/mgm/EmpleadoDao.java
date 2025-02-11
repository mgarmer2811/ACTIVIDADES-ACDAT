/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario14
 */
public class EmpleadoDao {
    public static Integer addEmpleado(Empleado em){
        Transaction transaction = null;
        Integer id = null;
        
        try(Session session = Conexion.getSession()){
            transaction = session.beginTransaction();
            session.persist(em);
            transaction.commit();
            id = em.getId();
        }catch(Exception e){
            System.err.println("Error al agregar el empleado: " + e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        }
        return id;
    }
    
    public static Empleado getEmpleadoById(int id){
        try(Session session = Conexion.getSession()){
            return session.get(Empleado.class, id);
        }catch(Exception e){
            System.err.println("Error al recuperar un empleado: " + e.getMessage());
            return null;
        }
    }
    
    public static Empleado getEmpleadoByDni(String dni){
        Transaction transaction = null;
        Empleado em = null;
        
        try(Session session = Conexion.getSession()){
            transaction = session.beginTransaction();
            List<Empleado> empleado = session.createQuery("SELECT e FROM Empleado AS e WHERE e.dni = :dni",Empleado.class)
                    .setParameter("dni", dni)
                    .getResultList();
            transaction.commit();
            if(!empleado.isEmpty()){
                em = empleado.get(0);
            }
        }catch(Exception e){
            System.err.println("Error al recuperar un empleado: " + e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        }
        return em;
    }
    
    public static boolean updateEmpleado(Empleado em){
        Transaction transaction = null;
        boolean exito = false;
        
        try(Session session = Conexion.getSession()){
            transaction = session.beginTransaction();
            session.merge(em);
            transaction.commit();
            exito = true;
        }catch(Exception e){
            System.err.println("Error al modificar al empleado: " + e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        }
        return exito;
    }
    
    public static boolean fireEmpleado(int id, String fechaFinalizacion){
        Empleado em = getEmpleadoById(id);
        boolean exito = false;
        
        if(em != null){
            em.setFechaFinalizacion(fechaFinalizacion);
            if(updateEmpleado(em)){
                exito = true;
            }
        }
        else{
            exito = false;
        }
        return exito;
    }
    
    public static List<Empleado> getActiveEmpleados(){
        try(Session session = Conexion.getSession()){
            return session.createQuery("SELECT e FROM Empleado AS e WHERE fecha_finalizacion = :fechaFinalizacion",Empleado.class)
                    .setParameter("fechaFinalizacion", null)
                    .getResultList();
        }catch(Exception e){
            System.err.println("Error al recuperar empleados activos: " + e.getMessage());
            return null;
        }
    }
    
    public static List<Empleado> getFiredEmpleados(){
        try(Session session = Conexion.getSession()){
            return session.createQuery("SELECT e FROM Empleado AS e WHERE fecha_finalizacion IS NOT NULL",Empleado.class)
                    .getResultList();
        }catch(Exception e){
            System.err.println("Error al recuperar empleados despedidos: " + e.getMessage());
            return null;
        }
    }
}
