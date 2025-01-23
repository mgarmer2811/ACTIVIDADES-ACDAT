package com.mgm;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Scanner;

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

    public static void addLibro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el título del libro:");
        String titulo = scanner.nextLine();
        System.out.println("Introduzca la fecha de publicación del libro (aaaa-mm-dd):");
        String fechaPublicacion = scanner.nextLine();
        System.out.println("Introduzca el género del libro:");
        String genero = scanner.nextLine();
        System.out.println("Introduzca el ISBN del libro:");
        String isbn = scanner.nextLine();
        System.out.println("Introduzca la editorial del libro:");
        String editorial = scanner.nextLine();
        System.out.println("Introduzca el ID del autor del libro:");
        int idAutor = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            Autor autor = session.get(Autor.class, idAutor);

            if (autor == null) {
                System.out.println("No se encontró un autor con el ID proporcionado.");
                return;
            }

            Libro libro = new Libro(0, titulo, fechaPublicacion, genero, isbn, editorial, autor);
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

    public static void updateLibro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del libro que desea actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            Libro libro = session.get(Libro.class, id);

            if (libro == null) {
                System.out.println("No se encontró un libro con el ID proporcionado.");
                return;
            }

            System.out.println("Título actual: " + libro.getTitulo());
            System.out.println("Introduzca el nuevo título del libro (deje en blanco para no modificar):");
            String titulo = scanner.nextLine();
            if (!titulo.isBlank()) {
                libro.setTitulo(titulo);
            }

            System.out.println("Fecha de publicación actual: " + libro.getFechaPublicacion());
            System.out.println("Introduzca la nueva fecha de publicación (aaaa-mm-dd, deje en blanco para no modificar):");
            String fechaPublicacion = scanner.nextLine();
            if (!fechaPublicacion.isBlank()) {
                libro.setFechaPublicacion(fechaPublicacion);
            }

            System.out.println("Género actual: " + libro.getGenero());
            System.out.println("Introduzca el nuevo género del libro (deje en blanco para no modificar):");
            String genero = scanner.nextLine();
            if (!genero.isBlank()) {
                libro.setGenero(genero);
            }

            System.out.println("ISBN actual: " + libro.getIsbn());
            System.out.println("Introduzca el nuevo ISBN del libro (deje en blanco para no modificar):");
            String isbn = scanner.nextLine();
            if (!isbn.isBlank()) {
                libro.setIsbn(isbn);
            }

            System.out.println("Editorial actual: " + libro.getEditorial());
            System.out.println("Introduzca la nueva editorial del libro (deje en blanco para no modificar):");
            String editorial = scanner.nextLine();
            if (!editorial.isBlank()) {
                libro.setEditorial(editorial);
            }

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

    public static void deleteLibro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del libro que desea eliminar:");
        int idLibro = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            Libro libro = session.get(Libro.class, idLibro);

            if (libro == null) {
                System.out.println("No se encontró un libro con el ID proporcionado.");
                return;
            }

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
