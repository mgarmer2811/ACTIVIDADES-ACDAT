/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.practica1;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
public class ClienteDao {
    public static Cliente getClienteByEmail(String email){
        Session session = null;
        Cliente cli = null;
        
        try {
            session = Conexion.getSession();
            List <Cliente> cliente = session.createQuery("SELECT c FROM Cliente AS c WHERE c.email = :email",Cliente.class)
                    .setParameter("email", email)
                    .getResultList();
            if (!cliente.isEmpty()) {
                cli = cliente.get(0);
            }
            return cli;
        } catch (Exception e) {
            System.out.println("Error al recuperar cliente: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if(session != null) {
                Conexion.close();
            }
        }
    }
    
    public static Integer anadirCliente(String nombre, String email){
        Session session = null;
        Transaction transaction = null;
        Integer id = null;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Cliente cli = new Cliente(nombre,email);
            session.persist(cli);
            transaction.commit();
            id = cli.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al anadir cliente: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return id;
    }
    
    public static Cliente getClienteById(int id){
        Session session = null;
        try {
            session = Conexion.getSession();
            Cliente cli = session.get(Cliente.class,id);
            cli.getCompraList().size();
            return cli;
        } catch (Exception e) {
            System.out.println("Error al recuperar cliente: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
    }
    
    public static boolean modificarCliente(int id, String nombre, String email){
        Session session = null;
        Transaction transaction = null;
        boolean exito = false;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Cliente cli = new Cliente(id,nombre,email);
            session.merge(cli);
            transaction.commit();
            exito = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al modificar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return exito;
    }
    
    public static boolean borrarCliente(int id){
        Session session = null;
        Transaction transaction = null;
        boolean exito = false;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Cliente cli = session.get(Cliente.class, id);
            List<Actividad> actividades = session.createQuery("SELECT a FROM Actividad AS a WHERE a.fecha > CURRENT_DATE",Actividad.class)
                    .getResultList();
            
            if (actividades.isEmpty()) {
                session.remove(cli);
                exito = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al borrar el cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return exito;
    }
    
    public static List<Cliente> getClientes(){
        Session session = null;
        try {
            session = Conexion.getSession();
            
            List<Cliente> clientes = session.createQuery("SELECT c FROM Cliente AS c",Cliente.class)
                    .getResultList();
            return clientes;
        } catch (Exception e) {
            System.out.println("Error al recuperar clientes: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
    }
}
