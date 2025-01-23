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

    public static void addAutor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el nombre del autor:");
        String nombre = scanner.nextLine();
        System.out.println("Introduzca la fecha de nacimiento del autor (aaaa-mm-dd):");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Introduzca la nacionalidad del autor:");
        String nacionalidad = scanner.nextLine();
        System.out.println("Introduzca el número de obras que ha realizado el autor:");
        int numeroObras = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.println("Introduzca una pequeña biografía del autor:");
        String biografia = scanner.nextLine();

        Autor autor = new Autor(nombre, fechaNacimiento, nacionalidad, numeroObras, biografia);
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

    public static void updateAutor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del autor que desea actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            Autor autor = session.get(Autor.class, id);

            if (autor == null) {
                System.out.println("No se encontró un autor con el ID proporcionado.");
                return;
            }

            System.out.println("Nombre actual: " + autor.getNombre());
            System.out.println("Fecha de nacimiento actual: " + autor.getFechaNacimiento());
            System.out.println("Nacionalidad actual: " + autor.getNacionalidad());
            System.out.println("Número de obras actual: " + autor.getNumeroObras());
            System.out.println("Biografía actual: " + autor.getBiografia());

            System.out.println("Introduzca el nuevo nombre del autor (deje en blanco para no modificar):");
            String nombre = scanner.nextLine();
            if (!nombre.isBlank()) {
                autor.setNombre(nombre);
            }

            System.out.println("Introduzca la nueva fecha de nacimiento del autor (aaaa-mm-dd, deje en blanco para no modificar):");
            String fechaNacimiento = scanner.nextLine();
            if (!fechaNacimiento.isBlank()) {
                autor.setFechaNacimiento(fechaNacimiento);
            }

            System.out.println("Introduzca la nueva nacionalidad del autor (deje en blanco para no modificar):");
            String nacionalidad = scanner.nextLine();
            if (!nacionalidad.isBlank()) {
                autor.setNacionalidad(nacionalidad);
            }

            System.out.println("Introduzca el nuevo número de obras realizadas por el autor (deje en blanco para no modificar):");
            String numeroObrasInput = scanner.nextLine();
            if (!numeroObrasInput.isBlank()) {
                autor.setNumeroObras(Integer.parseInt(numeroObrasInput));
            }

            System.out.println("Introduzca la nueva biografía del autor (deje en blanco para no modificar):");
            String biografia = scanner.nextLine();
            if (!biografia.isBlank()) {
                autor.setBiografia(biografia);
            }

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

    public static void deleteAutor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el ID del autor que desea eliminar:");
        int idAutor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Transaction transaction = null;

        try (Session session = Conexion.getSession()) {
            transaction = session.beginTransaction();
            Autor autor = session.get(Autor.class, idAutor);

            if (autor == null) {
                System.out.println("No se encontró un autor con el ID proporcionado.");
                return;
            }

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
