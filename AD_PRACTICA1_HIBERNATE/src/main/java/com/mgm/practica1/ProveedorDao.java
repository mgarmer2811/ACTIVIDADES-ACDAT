package com.mgm.practica1;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProveedorDao {
    
    public static Proveedor getProveedorByCif(String cif) {
        Session session = null;
        Transaction transaction = null;
        Proveedor pro = null;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            List<Proveedor> proveedor = session.createQuery("SELECT p FROM Proveedor AS p WHERE p.cif = :cif", Proveedor.class)
                    .setParameter("cif", cif)
                    .getResultList();
            
            if (!proveedor.isEmpty()) {
                pro = proveedor.get(0);
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al recuperar proveedor por CIF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return pro;
    }
    
    public static Integer anadirProveedor(String nombre, String email, String cif) {
        Session session = null;
        Transaction transaction = null;
        Integer id = null;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Proveedor pro = new Proveedor(nombre, email, cif);
            session.persist(pro);
            transaction.commit();
            id = pro.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al añadir un proveedor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return id;
    }
    
    public static Proveedor getProveedorById(int id) {
        Session session = null;
        try {
            session = Conexion.getSession();
            Proveedor pro = session.get(Proveedor.class, id);
            pro.getActividadList().size();
            return pro;
        } catch (Exception e) {
            System.err.println("Error al recuperar proveedor por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
    }
    
    public static boolean modificarProveedor(int id, String nombre, String email, String cif) {
        Session session = null;
        Transaction transaction = null;
        boolean exito = false;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Proveedor pro = new Proveedor(id, nombre, email, cif);
            session.merge(pro);
            transaction.commit();
            exito = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al modificar un proveedor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return exito;
    }
    
    public static boolean borrarProveedor(int id){
        Session session = null;
        Transaction transaction = null;
        boolean exito = false;
        
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            Proveedor pro = session.get(Proveedor.class, id);
            List<Actividad> actividades = session.createQuery("SELECT a FROM Actividad AS a WHERE a.fecha > CURRENT_DATE AND a.proveedor.id = :id",Actividad.class)
                    .setParameter("id", id)
                    .getResultList();
            
            if (actividades.isEmpty()){
                session.remove(pro);
            }
            transaction.commit();
            exito = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al borrar un proveedor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                Conexion.close();
            }
        }
        return exito;
    }
}
