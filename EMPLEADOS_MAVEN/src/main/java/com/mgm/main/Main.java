/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm.main;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        do{
           opcion = menu();
           switch(opcion){
               case 1:{
                   listarEmpleados();
                   break;
               }
               case 2:{
                   anadirEmpleado();
                   break;
               }
               case 3:{
                   actualizarEmpleado();
                   break;
               }
               case 4:{
                   borrarEmpleado();
                   break;
               }
               case 5:{
                   System.out.println("Has salido del programa");
                   break;
               }
               default:{
                   System.out.println("Opcion no valida");
               }
           }
        }
        while(opcion != 5);
    }
    
    public static void listarEmpleados() {
        ArrayList<Empleado> empleados = EmpleadoDAO.listarEmpleados();
        if(empleados.size() > 0){
            for(Empleado empleado : empleados){
                System.out.println(empleado.toString());
            }
        }
    }
    
    public static void anadirEmpleado() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce el nombre del empleado");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el puesto del empleado");
        String puesto = scanner.nextLine();
        System.out.println("Introduce el salario del empleado");
        float salario = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Introduce el anyo de ingreso en la empresa (del empleado)");
        int anyoIngreso = scanner.nextInt();
        
        Empleado newEmpleado = new Empleado(nombre,puesto,salario,anyoIngreso);
        EmpleadoDAO.anadirEmpleado(newEmpleado);
    }
    
    public static void actualizarEmpleado() {
        Scanner scanner = new Scanner(System.in);
        int filasAfectadas = 0;
        
        System.out.println("Introduce el id del empleado que desea modificar");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el nuevo nombre del empleado");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el nuevo puesto del empleado");
        String puesto = scanner.nextLine();
        System.out.println("Introduce el nuevo salario del empleado");
        float salario = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Introduce el nuevo anyo de ingreso en la empresa (del empleado)");
        int anyo = scanner.nextInt();
        
        Empleado newEmpleado = new Empleado(nombre,puesto,salario,anyo);
        filasAfectadas = EmpleadoDAO.actualizarEmpleado(newEmpleado, id);
        if(filasAfectadas > 0){
            System.out.println("Se ha actualizado el empleado correctamente");
        }
    }
    
    public static void borrarEmpleado() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Empleado> empleados;
        Empleado empleado = null;
        boolean encontrado = false;
        int filasAfectadas = 0;

        System.out.println("Introduce el id del empleado que desea modificar");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        empleados = EmpleadoDAO.listarEmpleados();
        for(Empleado aux : empleados){
            if(aux.getId() == id){
                empleado = aux;
                encontrado = true;
            }
        }
        if(encontrado){
            filasAfectadas = EmpleadoDAO.borrarEmpleado(empleado);
            if(filasAfectadas > 0){ System.out.println("Se ha eliminado el empleado correctamente"); }
        }
        else{
            System.out.println("No se ha encontrado el empleado que desea borrar");
        }
    }
    
    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Anadir un empleado");
        System.out.println("3. Actualizar un empleado");
        System.out.println("4. Borrar un empleado");
        System.out.println("5. Salir");
        System.out.println("Escoja una opcion:");
        int opcion = scanner.nextInt();
        
        return opcion;
    }
}
