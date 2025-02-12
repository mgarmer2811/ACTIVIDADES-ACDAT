package com.mgm;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProyectoDao {

    public static Integer addProyecto(Proyecto pro) {
        Transaction transaction = null;
        Integer id = null;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            session.persist(pro);
            transaction.commit();
            id = pro.getId();
        } catch (Exception e) {
            System.err.println("Error al agregar el proyecto: " + e.getMessage());
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
    
    public static Integer addProyectoWithEmpleados(Proyecto pro, List<Empleado> empleados) {
        Transaction transaction = null;
        Integer id = null;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            
            // Reanexamos cada empleado para que estén en el contexto de la sesión actual.
            List<Empleado> managedEmpleados = new ArrayList<>();
            for (Empleado emp : empleados) {
                Empleado managedEmp = (Empleado) session.merge(emp);
                managedEmpleados.add(managedEmp);
                
                if (managedEmp.getProyectoList() == null) {
                    managedEmp.setProyectoList(new ArrayList<>());
                }
                managedEmp.getProyectoList().add(pro);
            }
            pro.setEmpleadoList(managedEmpleados);
            session.persist(pro);
            id = pro.getId();
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Error al agregar el proyecto: " + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }
    
    public static Proyecto getProyectoByNombre(String nombre) {
        Transaction transaction = null;
        Proyecto pro = null;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            List<Proyecto> proyecto = session.createQuery("SELECT p FROM Proyecto AS p WHERE nombre = :nombre", Proyecto.class)
                                             .setParameter("nombre", nombre)
                                             .getResultList();
            transaction.commit();
            if (!proyecto.isEmpty()) {
                pro = proyecto.get(0);
            }
        } catch (Exception e) {
            System.err.println("Error al recuperar un proyecto: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return pro;
    }
    
    public static Proyecto getProyectoById(int id) {
        Session session = null;
        Proyecto pro = null;
        try {
            session = Conexion.getSession();
            pro = session.get(Proyecto.class, id);
        } catch (Exception e) {
            System.err.println("Error al recuperar un proyecto: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return pro;
    }
    
    public static boolean updateProyecto(Proyecto pro) {
        Transaction transaction = null;
        boolean exito = false;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            session.merge(pro);
            transaction.commit();
            exito = true;
        } catch (Exception e) {
            System.err.println("Error al modificar el proyecto: " + e.getMessage());
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
    
    public static boolean addEmpleadoToProyecto(Proyecto pro, Empleado em) {
        Transaction transaction = null;
        boolean exito = false;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();

            em = (Empleado) session.merge(em);
            pro = (Proyecto) session.merge(pro);
            if (!em.getProyectoList().contains(pro)) {
                em.getProyectoList().add(pro);
                session.merge(em);
            }

            if (!pro.getEmpleadoList().contains(em)) {
                pro.getEmpleadoList().add(em);
                session.merge(pro);
            }

            transaction.commit();
            exito = true;
        } catch (Exception e) {
            System.err.println("Error al anadir empleado al proyecto: " + e.getMessage());
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
    
    public static boolean addEmpleadosToProyecto(Proyecto pro, List<Empleado> empleados) {
        Transaction transaction = null;
        boolean exito = false;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            pro = (Proyecto) session.merge(pro);
            
            for (Empleado emp : empleados) {
                emp = (Empleado) session.merge(emp);
                if (!emp.getProyectoList().contains(pro)) {
                    emp.getProyectoList().add(pro);
                    session.merge(emp);
                }
            }

            for (Empleado emp : empleados) {
                if (!pro.getEmpleadoList().contains(emp)) {
                    pro.getEmpleadoList().add(emp);
                    session.merge(pro);
                }
            }

            transaction.commit();
            exito = true;
        } catch (Exception e) {
            System.err.println("Error al anadir empleados al proyecto: " + e.getMessage());
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
    
    public static boolean removeFromProyecto(Proyecto pro, Empleado em) {
        Transaction transaction = null;
        boolean exito = false;
        Session session = null;
        try {
            session = Conexion.getSession();
            transaction = session.beginTransaction();
            pro = (Proyecto) session.merge(pro);
            em = (Empleado) session.merge(em);
            
            if (pro.getEmpleadoList().contains(em)) {
                pro.getEmpleadoList().remove(em);
                session.merge(pro);
            }
            
            if (em.getProyectoList().contains(pro)) {
                em.getProyectoList().remove(pro);
                session.merge(em);
            }

            transaction.commit();
            exito = true;
        } catch (Exception e) {
            System.out.println("Error al quitar el empleado del proyecto: " + e.getMessage());
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

    
    public static List<Proyecto> getFutureProyectos(){
        Session session = null;
        try{
            session = Conexion.getSession();
            return session.createQuery(
                    "SELECT p FROM Proyecto p WHERE FUNCTION('STR_TO_DATE', p.fechaInicio, '%Y-%m-%d') > CURRENT_DATE",
                    Proyecto.class
            ).getResultList();
        }catch(Exception e){
            System.err.println("Error al recuperar proyectos futuros: " + e.getMessage());
            return null;
        }finally{
            if(session != null){
                session.close();
            }
        }
    }
}
