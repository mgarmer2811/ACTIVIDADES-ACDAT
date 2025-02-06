package com.mgm;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario14
 */
public class AutorDao {
    
    public static List<Autor> getAutores() {
        List<Autor> autores = null;
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            autores = session.createQuery("SELECT a FROM Autor AS a", Autor.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al obtener la lista de autores: " + e.getMessage());
        }

        return autores;
    }

    public static Autor getAutor(int id) {
        Autor autor = null;
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            autor = session.createQuery("SELECT a FROM Autor AS a WHERE a.id = :id", Autor.class)
                           .setParameter("id", id)
                           .getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al obtener el autor: " + e.getMessage());
        }

        return autor;
    }

    public static void addAutor(Autor autor) {
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            session.persist(autor);
            transaction.commit();
            System.out.println("Autor agregado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al agregar el autor: " + e.getMessage());
        }
    }

    public static void updateAutor(Autor autor) {
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            session.merge(autor);
            transaction.commit();
            System.out.println("El autor ha sido actualizado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al actualizar el autor: " + e.getMessage());
        }
    }

    public static void deleteAutor(Autor autor) {
        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();

            session.remove(autor);
            transaction.commit();
            System.out.println("El autor ha sido eliminado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar el autor: " + e.getMessage());
        }
    }
}
