package com.mgm;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LibroDao {

    public static List<Libro> getLibros() {
        List<Libro> libros = null;
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            libros = session.createQuery("SELECT l FROM Libro AS l", Libro.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al obtener la lista de libros: " + e.getMessage());
        }

        return libros;
    }

    public static Libro getLibro(int id) {
        Libro libro = null;
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            libro = session.createQuery("SELECT l FROM Libro AS l WHERE l.id = :id", Libro.class)
                           .setParameter("id", id)
                           .getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al obtener el libro: " + e.getMessage());
        }

        return libro;
    }

    public static void addLibro(Libro libro) {
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            session.persist(libro);
            transaction.commit();
            System.out.println("Libro agregado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al agregar el libro: " + e.getMessage());
        }
    }

    public static void updateLibro(Libro libro) {
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            session.merge(libro);
            transaction.commit();
            System.out.println("El libro ha sido actualizado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al actualizar el libro: " + e.getMessage());
        }
    }

    public static void deleteLibro(Libro libro) {
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            session.remove(libro);
            transaction.commit();
            System.out.println("El libro ha sido eliminado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar el libro: " + e.getMessage());
        }
    }
}
