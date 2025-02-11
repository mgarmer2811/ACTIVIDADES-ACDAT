/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario14
 */
public class Main {

    public static void main(String[] args) {
        Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.SEVERE);
        int opcion;
        
        do{
            opcion = menu();
            switch(opcion){
                case 1:{
                    addEmpleado();
                    break;
                }
                case 2:{
                    updateEmpleado();
                    break;
                }
                case 3:{
                    fireEmpleado();
                    break;
                }
                case 4:{
                    getActiveEmpleados();
                    break;
                }
                case 5:{
                    getFiredEmpleados();
                    break;
                }
                case 6:{
                    addProyecto();
                    break;
                }
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:{
                    System.out.println("Has salido del programa!");
                    break;
                }
                default:
                    System.out.println("Opcion no valida. Por favor introduzca una opcion valida");
            }
        }while(opcion != 15);
    }
    
    public static int menu(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        System.out.println("\n1. Anadir empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Despedir empleado");
        System.out.println("4. Listar empleados activos");
        System.out.println("5. Listar empleados despedidos");
        System.out.println("6. Anadir un proyecto con empleados");
        System.out.println("7. Modificar un proyecto");
        System.out.println("8. Anadir empleado a proyecto");
        System.out.println("9. Anadir varios empleados a un proyecto");
        System.out.println("10. Eliminar un empleado de un proyecto");
        System.out.println("11. Listar todos los proyectos futuros");
        System.out.println("12. Listar todos los proyectos pasados");
        System.out.println("13. Listar todos los proyectos activos");
        System.out.println("14. Listar detalles de un proyecto");
        System.out.println("15. Salir");
        System.out.println("Elija una opcion: ");
        opcion = scanner.nextInt();
        
        return opcion;
    }
    
    public static void addEmpleado(){
        Scanner scanner = new Scanner(System.in);
        Empleado em;
        
        System.out.println("Introduce el dni del empleado");
        String dni = scanner.nextLine();
        em = EmpleadoDao.getEmpleadoByDni(dni);
        
        if(em == null){
            System.out.println("Introduce el nombre del empleado");
            String nombre = scanner.nextLine();
            System.out.println("Introduce el departamento del empleado");
            String departamento = scanner.nextLine();
            System.out.println("Introduce el sueldo del empleado");
            float sueldo = scanner.nextFloat();
            scanner.nextLine();
            System.out.println("Introduce la fecha de contratacion del empleado {aaaa-mm-dd}");
            String fechaContratacion = scanner.nextLine();
            
            em = new Empleado();
            em.setNombre(nombre);
            em.setDni(dni);
            em.setDepartamento(departamento);
            em.setSueldo(sueldo);
            em.setFechaContratacion(fechaContratacion);
            em.setFechaFinalizacion(null);
            
            Integer id = EmpleadoDao.addEmpleado(em);
            if (id != null) {
                System.out.println("Se ha agregado el empleado! Su ID es: " + id);
            }
        }
        else{
            System.out.println("El empleado ya existe");
            System.out.println("Introduce el nombre del empleado (deja en blanco para mantener el actual");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                em.setNombre(nombre);
            }

            System.out.println("Introduce el departamento del empleado (deja en blanco para mantener el actual");
            String departamento = scanner.nextLine();
            if (!departamento.isEmpty()) {
                em.setDepartamento(departamento);
            }

            System.out.println("Introduce el sueldo del empleado (deja en blanco para mantener el actual)");
            String sueldoInput = scanner.nextLine();
            if (!sueldoInput.isEmpty()) {
                try {
                    float sueldo = Float.parseFloat(sueldoInput);
                    em.setSueldo(sueldo);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Se mantiene el sueldo actual.");
                }
            }

            System.out.println("Introduce la fecha de contratación del empleado {aaaa-mm-dd} (deja en blanco para mantener el actual)");
            String fechaContratacion = scanner.nextLine();
            if (!fechaContratacion.isEmpty()) {
                em.setFechaContratacion(fechaContratacion);
            }
            
            em.setFechaFinalizacion(null);
            if(EmpleadoDao.updateEmpleado(em)){
                System.out.println("Se ha actualizado el empleado!");
            }
        }
    }
    
    public static void updateEmpleado(){
        Scanner scanner = new Scanner(System.in);
        Empleado em;
        
        System.out.println("Introduce el ID del empleado");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        em = EmpleadoDao.getEmpleadoById(id);
        if(em != null){
            System.out.println("Introduce el nombre del empleado (deja en blanco para mantener el actual");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                em.setNombre(nombre);
            }

            System.out.println("Introduce el departamento del empleado (deja en blanco para mantener el actual");
            String departamento = scanner.nextLine();
            if (!departamento.isEmpty()) {
                em.setDepartamento(departamento);
            }

            System.out.println("Introduce el sueldo del empleado (deja en blanco para mantener el actual)");
            String sueldoInput = scanner.nextLine();
            if (!sueldoInput.isEmpty()) {
                try {
                    float sueldo = Float.parseFloat(sueldoInput);
                    em.setSueldo(sueldo);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Se mantiene el sueldo actual.");
                }
            }
            
            if(EmpleadoDao.updateEmpleado(em)){
                System.out.println("Se ha actualizado el empleado!");
            }
        }
        else{
            System.out.println("No se ha encontrado un empleado con ese ID");
        }
    }
    
    public static void fireEmpleado(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce el ID del empleado a eliminar");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce la fecha de finalizacion del contrato {aaaa-mm-dd}");
        String fechaFinalizacion = scanner.nextLine();
        
        if(EmpleadoDao.fireEmpleado(id,fechaFinalizacion)){
            System.out.println("Se ha despedido al empleado!");
        }
        else{
            System.out.println("No se ha encontrado un empleado con ese ID");
        }
    }
    
    public static void getActiveEmpleados(){
        List<Empleado> empleados = EmpleadoDao.getActiveEmpleados();
        
        if(empleados != null){
            for(Empleado empleado : empleados){
                System.out.println(empleado.toString());
            }
        }
        else{
            System.out.println("No hay empleados activos en la base de datos");
        }
    }
    
    public static void getFiredEmpleados(){
        List<Empleado> empleados = EmpleadoDao.getFiredEmpleados();
        
        if(empleados != null){
            for(Empleado empleado : empleados){
                System.out.println(empleado.toString());
                System.out.print("fechaContratacion=" + empleado.getFechaFinalizacion());
            }
        }
    }
    
    public static void addProyecto(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduzca el nombre del proyecto");
        String nombre = scanner.nextLine();
        System.out.println("Introduzca la fecha de inicio del proyecto {aaaa-mm-dd}");
        String fechaInicio = scanner.nextLine();
        System.out.println("Introduzca la fecha de finalizacion del proyecto {aaaa-mm-dd}");
        String fechaFinalizacion = scanner.nextLine();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date inicio = formatter.parse(fechaInicio);
            Date finalizacion = formatter.parse(fechaFinalizacion);
            
            if(finalizacion.before(inicio)){
                String aux = fechaInicio;
                fechaInicio = fechaFinalizacion;
                fechaFinalizacion = aux;
            }
        } catch (ParseException e) {
            System.err.println("Ha ocurrido un error al trabajar con las fechas: " + e.getMessage());
        }
        
        Proyecto pro = new Proyecto(nombre,fechaInicio,fechaFinalizacion);
        Integer id = ProyectoDao.addProyecto(pro);
        if(id != null){
            System.out.println("Se ha agregado el proyecto! Su ID es: " + id);
        }
    }
}
