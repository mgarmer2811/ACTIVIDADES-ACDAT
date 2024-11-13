/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario14
 */
public class GestionCliente {

    public static void main(String[] args) {
        int opcion = 0;
        Conexion conexion = null;
        try{
            conexion = new Conexion();
            System.out.println("Se ha establecido la conexion correctamente");
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error estableciendo la conexion con la base de datos");
            System.exit(1);
        }
        
        do{
           opcion = menu();
           switch(opcion){
               case 1:{
                   listarClientes(conexion);
                   break;
               }
               case 2:{
                   Cliente cliente = getCliente();
                   anadirCliente(conexion, cliente);
                   break;
               }
               case 3:{
                   Scanner scanner = new Scanner(System.in);
                   System.out.println("Introduzca el email/fragemento de email a buscar");
                   String email = scanner.nextLine();
                   buscarPorEmail(conexion,email);
                   break;
               }
               case 4:{
                   Scanner scanner = new Scanner(System.in);
                   int opcionModificar = 0;
                   String query = "SELECT email FROM cliente;";
                   ResultSet rs;
                   String emailString = "";
                   ArrayList<String> emails = new ArrayList<>();
                   
                   try{
                       rs = conexion.ejecutarQuery(query);
                       while (rs.next()) {
                           emailString = rs.getString("email");
                           emails.add(emailString);
                       }
                   }
                   catch (SQLException sqle) {
                       System.err.println("Se ha producido un error en la operación de eliminación.");
                   }
                   
                   do{
                       opcionModificar = menuModificar();
                       switch(opcionModificar){
                           case 1:{
                               System.out.println("Introduce el nuevo nombre del cliente");
                               String nombre = scanner.nextLine();
                               System.out.println("Introduce el email del cliente que desea modificar");
                               String email = scanner.nextLine();
                               if(containsIgnoreCase(emails,email)){
                                   String queryUpdate = "UPDATE cliente SET nombre = '" + nombre + "' WHERE email = '" + email + "'";
                                   try{
                                       conexion.ejecutar(queryUpdate);
                                   }
                                   catch(SQLException sqle){
                                       System.err.println("Ha surgido un error al actualizar el nombre del cliente");
                                   }
                               }
                               else{
                                   System.out.println("No se ha encontado el cliente que se quiere modificar");
                               }
                               break;
                           }
                           case 2:{
                               System.out.println("Introduce la nueva ciudad del cliente");
                               String ciudad = scanner.nextLine();
                               System.out.println("Introduce el email del cliente que desea modificar");
                               String email = scanner.nextLine();
                               if(containsIgnoreCase(emails,email)){
                                   String queryUpdate = "UPDATE cliente SET ciudad = '" + ciudad + "' WHERE email = '" + email + "'";
                                   try{
                                       conexion.ejecutar(queryUpdate);
                                   }
                                   catch(SQLException sqle){
                                       System.err.println("Ha surgido un error al actualizar el nombre del cliente");
                                   }
                               }
                               else{
                                   System.out.println("No se ha encontado el cliente que se quiere modificar");
                               }
                               break;
                           }
                           case 3:{
                               System.out.println("Introduce el nuevo telefono del cliente");
                               String telefono = scanner.nextLine();
                               System.out.println("Introduce el email del cliente que desea modificar");
                               String email = scanner.nextLine();
                               if(containsIgnoreCase(emails,email)){
                                   String queryUpdate = "UPDATE cliente SET email = '" + telefono + "' WHERE email = '" + email + "'";
                                   try{
                                       conexion.ejecutar(queryUpdate);
                                   }
                                   catch(SQLException sqle){
                                       System.err.println("Ha surgido un error al actualizar el nombre del cliente");
                                   }
                               }
                               else{
                                   System.out.println("No se ha encontado el cliente que se quiere modificar");
                               }
                               break;
                           }
                           default:
                               System.out.println("Has salido del menu de modificacion");
                       }
                   }while(opcionModificar != 4);
                   break;
               }
               case 5:{
                   Scanner scanner = new Scanner(System.in);
                   System.out.println("Introduce el email del cliente que deseas eliminar");
                   String email = scanner.nextLine();
                   eliminarCliente(conexion,email);
                   break;
               }
               default:
                   System.out.println("Has salido del programa");
           }
           
        }while(opcion != 6);
        try{
            conexion.cerrarConexion();
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error al cerrar la conexion");
        }
    }
    
    public static void listarClientes(Conexion conn) {
        String query = "SELECT * FROM cliente;";
        ResultSet rs;
        try{
            rs = conn.ejecutarQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String ciudad = rs.getString("ciudad");
                String telefono = rs.getString("telefono");
                
                System.out.println("id: " + id + "\tnombre: " + nombre + "\temail: " + email + "\tciudad: " + ciudad + "\ttelefono: " + telefono);
            }
        }
        catch(SQLException slqe){
            System.err.println("Se ha producido un error ejecutando la busqueda");
        }
    }
    
    public static void anadirCliente(Conexion conn, Cliente cliente) {
        String query = "SELECT email FROM cliente WHERE email = " + "'" + cliente.getEmail() + "';";
        ResultSet rs;
        
        try{
            rs = conn.ejecutarQuery(query);
            if(!rs.next()){
                String executeQuery = "INSERT INTO `cliente` (`nombre`, `email`, `ciudad`, `telefono`) VALUES "
                                      + "('" + cliente.getNombre() + "', '" + cliente.getEmail() + "', '" + cliente.getCiudad() + "', '" + cliente.getTelefono() + "');";
                try{
                    conn.ejecutar(executeQuery);
                }
                catch(SQLException sqlei){
                    System.err.println("Se ha producido un error añadiendo el cliente");
                }
            }
            else{
                System.out.println("No se ha añadido el cliente porque el email introducido ya existe en la base de datos");
            }
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error en la operacion");
        }
    }
    
    public static void buscarPorEmail(Conexion conn, String email) {
        String query = "SELECT * FROM cliente WHERE email LIKE '%" + email + "%'";
        ResultSet rs;

        try {
            rs = conn.ejecutarQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String emailC = rs.getString("email");
                String ciudad = rs.getString("ciudad");
                String telefono = rs.getString("telefono");
                
                System.out.println("id: " + id + "\tnombre: " + nombre + "\temail: " + emailC + "\tciudad: " + ciudad + "\ttelefono: " + telefono);
            }
        } catch (SQLException sqle) {
            System.err.println("Se ha producido un error en la busqueda");
        }
    }

    
    public static void eliminarCliente(Conexion conn, String email) {
        String query = "SELECT email FROM cliente;";
        ResultSet rs;
        String emailString = "";
        ArrayList<String> emails = new ArrayList<>();

        try {
            rs = conn.ejecutarQuery(query);
            while (rs.next()) {
                emailString = rs.getString("email");
                emails.add(emailString);
            }
            if (containsIgnoreCase(emails,email)) {
                String executeQuery = "DELETE FROM `cliente` WHERE `email` = '" + email + "';";
                try {
                    conn.ejecutar(executeQuery);
                    System.out.println("Cliente con email " + email + " ha sido eliminado.");
                } catch (SQLException sqlei) {
                    System.err.println("Se ha producido un error al eliminar el cliente.");
                }
            } else {
                System.out.println("No se ha encontrado un cliente con el email: " + email);
            }
        } catch (SQLException sqle) {
            System.err.println("Se ha producido un error en la operación de eliminación.");
        }
    }
    
    public static boolean containsIgnoreCase(ArrayList<String> emails, String str) {
        for (String email : emails) {
            if (email.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
    
    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        System.out.println("");
        System.out.println("");
        System.out.println("1. Listar todos los clientes");
        System.out.println("2. Añadir un cliente");
        System.out.println("3. Buscar cliente por email");
        System.out.println("4. Modificar datos de cliente");
        System.out.println("5. Borra un cliente");
        System.out.println("6. Salir");
        System.out.println("Elija una opcion");
        opcion = scanner.nextInt();
        System.out.println("");
        System.out.println("");
        
        return opcion;
    }
    
    public static int menuModificar() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        System.out.println("");
        System.out.println("");
        System.out.println("1. Modificar nombre");
        System.out.println("2. Modificar ciudad");
        System.out.println("3. Modificar telefono");
        System.out.println("4. Salir");
        System.out.println("Elija una opcion");
        opcion = scanner.nextInt();
        System.out.println("");
        System.out.println("");
        
        return opcion;
    }
    
    public static Cliente getCliente() {
        Scanner scanner = new Scanner(System.in);
        String nombre, email, ciudad, telefono;

        System.out.println("Introduzca el nombre del nuevo cliente");
        nombre = scanner.nextLine();
        System.out.println("Introduzca el email del nuevo cliente");
        email = scanner.nextLine();
        System.out.println("Introduzca la ciudad del nuevo cliente");
        ciudad = scanner.nextLine();
        System.out.println("Introduzca el telefono del nuevo cliente");
        telefono = scanner.nextLine();
        
        Cliente cliente = new Cliente(nombre,email,ciudad,telefono);
        return cliente;
    }
}
