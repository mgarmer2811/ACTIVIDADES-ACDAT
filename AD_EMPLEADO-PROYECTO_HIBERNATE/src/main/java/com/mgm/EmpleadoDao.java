package com.mgm;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpleadoDao {

    public static Integer addEmpleado(Empleado em) {
        Transaction transaction = null;
        Integer id = null;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            session.persist(em);
            transaction.commit();
            id = em.getId();
        } catch (Exception e) {
            System.err.println("Error al agregar el empleado: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }
    
    public static Empleado getEmpleadoById(int id) {
        Session session = null;
        try {
            session = Conexion.getSession();
            return session.get(Empleado.class, id);
        } catch (Exception e) {
            System.err.println("Error al recuperar un empleado: " + e.getMessage());
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public static Empleado getEmpleadoByDni(String dni) {
        Transaction transaction = null;
        Empleado em = null;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            List<Empleado> empleado = session.createQuery("SELECT e FROM Empleado AS e WHERE e.dni = :dni", Empleado.class)
                    .setParameter("dni", dni)
                    .getResultList();
            transaction.commit();
            if (!empleado.isEmpty()) {
                em = empleado.get(0);
            }
        } catch (Exception e) {
            System.err.println("Error al recuperar un empleado: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return em;
    }
    
    public static boolean updateEmpleado(Empleado em) {
        Transaction transaction = null;
        boolean exito = false;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            session.merge(em);
            transaction.commit();
            exito = true;
        } catch (Exception e) {
            System.err.println("Error al modificar al empleado: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return exito;
    }
    
    public static boolean fireEmpleado(int id, String fechaFinalizacion) {
        Empleado em = getEmpleadoById(id);
        boolean exito = false;
        if (em != null) {
            em.setFechaFinalizacion(fechaFinalizacion);
            if (updateEmpleado(em)) {
                exito = true;
            }
        } else {
            exito = false;
        }
        return exito;
    }
    
    public static List<Empleado> getActiveEmpleados() {
        Session session = null;
        try {
            session = Conexion.getSession();
            return session.createQuery("SELECT e FROM Empleado AS e WHERE e.fechaFinalizacion IS NULL", Empleado.class)
                    .getResultList();
        } catch (Exception e) {
            System.err.println("Error al recuperar empleados activos: " + e.getMessage());
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public static List<Empleado> getFiredEmpleados() {
        Session session = null;
        try {
            session = Conexion.getSession();
            return session.createQuery("SELECT e FROM Empleado AS e WHERE e.fechaFinalizacion IS NOT NULL", Empleado.class)
                    .getResultList();
        } catch (Exception e) {
            System.err.println("Error al recuperar empleados despedidos: " + e.getMessage());
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
