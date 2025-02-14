/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.practica1;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
public class ActividadDao {
    public static Integer anadirActividad(String nombre, Date fecha, String ubicacion, int plazas, String cif){
        Session session = null;
        Transaction transaction = null;
        Integer id = null;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Proveedor pro = session.createQuery("SELECT p FROM Proveedor AS p WHERE p.cif = :cif",Proveedor.class)
                    .setParameter("cif", cif)
                    .getSingleResult();
            
            Actividad act = new Actividad(nombre,fecha,ubicacion,plazas);
            act.setProveedor(pro);
            session.persist(act);
            transaction.commit();
            id = act.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al anadir actividad: " + e.getMessage());
            e.printStackTrace();
        } finally{
            if (session != null){
                Conexion.close();
            }
        }
        return id;
    }
    
    public static Actividad getActividadById(int id){
        Session session = null;
        try {
            session = Conexion.getSession();
            Actividad act = session.get(Actividad.class, id);
            act.getCompraList().size();
            return act;
        } catch (Exception e) {
            System.out.println("Error al recuperar actividad: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
    }
    
    public static boolean borrarActividad(int id){
        Session session = null;
        Transaction transaction = null;
        boolean exito = false;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Actividad act = session.get(Actividad.class, id);
            session.remove(act);
            transaction.commit();
            exito = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error el borrar la actividad: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return exito;
    }
    
    public static boolean comprarActividad(int idCli, int idAct){
        Session session = null;
        Transaction transaction = null;
        boolean exito = false;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Actividad act = session.get(Actividad.class, idAct);
            Cliente cli = session.get(Cliente.class, idCli);
            
            if ((act.getPlazasDisponibles() > 0) && (act.getFecha().after(new Date()))) {
                act.setPlazasDisponibles(act.getPlazasDisponibles() - 1);
                Compra compra = new Compra(new Date());
                compra.setActividad(act);
                compra.setCliente(cli);
                
                act.getCompraList().add(compra);
                cli.getCompraList().add(compra);
                session.persist(compra);
                transaction.commit();
                exito = true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al comprar una actividad: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return exito;
    }
    
    public static boolean cancelarCompra(int idCli, int idAct) {
        Session session = null;
        Transaction transaction = null;
        boolean exito = false;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Actividad act = session.get(Actividad.class, idAct);
            Cliente cli = session.get(Cliente.class, idCli);
            
            if (!act.getFecha().before(new Date())) {
                List<Compra> compra = session.createQuery("SELECT c FROM Compra AS c WHERE c.cliente.id = :cliId AND c.actividad.id = :actId",Compra.class)
                        .setParameter("cliId", cli.getId())
                        .setParameter("actId", act.getId())
                        .getResultList();
                
                if (!compra.isEmpty()) {
                    Compra com = compra.get(0);
                    act.setPlazasDisponibles(act.getPlazasDisponibles() + 1);
                    cli.getCompraList().remove(com);
                    act.getCompraList().remove(com);
                    session.merge(cli);
                    session.merge(act);
                    session.remove(com);
                    exito = true;
                }
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al cancelar la compra de una actividad: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return exito;
    }
    
    public static List<Actividad> getFutureActividades(){
        Session session = null;
        try {
            session = Conexion.getSession();
            
            List<Actividad> actividades = session.createQuery("SELECT a FROM Actividad AS a WHERE a.fecha > CURRENT_DATE",Actividad.class)
                    .getResultList();
            return actividades;
        } catch (Exception e) {
            System.out.println("Error al recuperar actividades futuras: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
    }
}
