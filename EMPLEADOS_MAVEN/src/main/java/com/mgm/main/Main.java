/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm.main;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) {
        
    }
    
    public static void listarEmpleados() {
        
    }
    
    public static void anadirEmpleado() {
        
    }
    
    public static void actualizarEmpleado() {
        
    }
    
    public static void borrarEmpleado() {
        
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
