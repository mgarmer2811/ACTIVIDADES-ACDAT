/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                case 7:{
                    addProyectoWithEmpleados();
                    break;
                }
                case 8:{
                    updateProyecto();
                    break;
                }
                case 9:{
                    addEmpleadoToProyecto();
                    break;
                }
                case 10:{
                    addEmpleadosToProyecto();
                    break;
                }
                case 11:{
                    deleteFromProyecto();
                    break;
                }
                case 12:{
                    getFutureProyects();
                    break;
                }
                case 13:
                case 14:
                case 15:
                case 16:{
                    System.out.println("Has salido del programa!");
                    break;
                }
                default:
                    System.out.println("Opcion no valida. Por favor introduzca una opcion valida");
            }
        }while(opcion != 16);
    }
    
    public static int menu(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        System.out.println("\n1. Anadir empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Despedir empleado");
        System.out.println("4. Listar empleados activos");
        System.out.println("5. Listar empleados despedidos");
        System.out.println("6. Anadir proyecto");
        System.out.println("7. Anadir un proyecto con empleados");
        System.out.println("8. Modificar un proyecto");
        System.out.println("9. Anadir empleado a proyecto");
        System.out.println("10. Anadir varios empleados a un proyecto");
        System.out.println("11. Eliminar un empleado de un proyecto");
        System.out.println("12. Listar todos los proyectos futuros");
        System.out.println("13. Listar todos los proyectos pasados");
        System.out.println("14. Listar todos los proyectos activos");
        System.out.println("15. Listar detalles de un proyecto");
        System.out.println("16. Salir");
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
            else{
                System.out.println("NO se ha podido actualizar el empleado!");
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
    
    public static void addProyectoWithEmpleados(){
        Scanner scanner = new Scanner(System.in);
        Proyecto pro;
        List<Empleado> empleados = new ArrayList<>();
        
        System.out.println("Introduzca el nombre del proyecto");
        String nombre = scanner.nextLine();
        nombre = nombre.strip().toLowerCase();
        pro = ProyectoDao.getProyectoByNombre(nombre);
        
        if(pro == null){
            System.out.println("Introduzca la fecha de inicio del proyecto {aaaa-mm-dd}");
            String fechaInicio = scanner.nextLine();
            System.out.println("Introduzca la fecha de finalizacion del proyecto {aaaa-mm-dd}");
            String fechaFinalizacion = scanner.nextLine();
            System.out.println("Introduzca los ID de los empleados a los que desea asociar a este proyecto");
            String stringIDs = scanner.nextLine();

            stringIDs = stringIDs.strip();
            String[] IDs = stringIDs.split(" ");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date inicio = formatter.parse(fechaInicio);
                Date finalizacion = formatter.parse(fechaFinalizacion);

                if (finalizacion.before(inicio)) {
                    String aux = fechaInicio;
                    fechaInicio = fechaFinalizacion;
                    fechaFinalizacion = aux;
                }
            } catch (ParseException e) {
                System.err.println("Error al trabajar con las fechas: " + e.getMessage());
            }
            
            for (String idStr : IDs) {
                try {
                    int idEmpleado = Integer.parseInt(idStr);
                    Empleado emp = EmpleadoDao.getEmpleadoById(idEmpleado);
                    if (emp != null) {
                        empleados.add(emp);
                    } else {
                        System.out.println("Empleado con ID " + idEmpleado + " no encontrado.");
                    }
                } catch (Exception e) {
                    System.err.println("ID inválido: " + idStr);
                }
            }

            if (empleados.isEmpty()) {
                System.out.println("No se ha añadido ningún empleado al proyecto. Operación cancelada.");
                return;
            }
            
            pro = new Proyecto(nombre, fechaInicio, fechaFinalizacion);
            Integer newId = ProyectoDao.addProyectoWithEmpleados(pro, empleados);

            if (newId != null) {
                System.out.println("Proyecto creado con éxito! Su ID es: " + newId);
            } else {
                System.err.println("Error al crear el proyecto.");
            }
        }
        else{
            System.out.println("Ya existe un proyecto con ese nombre. Pruebe a modificarlo");
        }
    }
    
    public static void updateProyecto(){
        Scanner scanner = new Scanner(System.in);
        Proyecto pro;
        int id;
        
        System.out.println("Introduzca el id del proyecto a modificar");
        id = scanner.nextInt();
        scanner.nextLine();
        
        pro = ProyectoDao.getProyectoById(id);
        if(pro != null){
            System.out.println("Introduce el nombre del proyecto (deja en blanco para mantener el actual");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                pro.setNombre(nombre);
            }

            System.out.println("Introduce la fecha de inicio del proyecto (deja en blanco para mantener la actual");
            String fechaInicio = scanner.nextLine();
            if (!fechaInicio.isEmpty()) {
                pro.setFechaInicio(fechaInicio);
            }
            
            System.out.println("Introduce la fecha de finalizacion del proyecto (deja en blanco para mantener la actual");
            String fechaFinalizacion = scanner.nextLine();
            if (!fechaFinalizacion.isEmpty()) {
                pro.setFechaFin(fechaFinalizacion);
            }
            
            if(ProyectoDao.updateProyecto(pro)){
                System.out.println("Se ha actualizado el proyecto!");
            }
            else{
                System.out.println("NO se ha podido actualizar el proyecto");
            }
        }
        else{
            System.out.println("No se ha encontrado un proyecto con ese ID");
        }
    }
    
    public static void addEmpleadoToProyecto(){
        Scanner scanner = new Scanner(System.in);
        int idPro, idEm;
        Proyecto pro;
        Empleado em;
        
        System.out.println("Introduce el ID del proyecto");
        idPro = scanner.nextInt();
        System.out.println("Introduce el ID del empleado");
        idEm = scanner.nextInt();
        scanner.nextLine();
        
        if(((pro = ProyectoDao.getProyectoById(idPro)) != null) && ((em = EmpleadoDao.getEmpleadoById(idEm)) != null)){
            if(ProyectoDao.addEmpleadoToProyecto(pro,em)){
                System.out.println("Se ha anadido el empleado al proyecto!");
            }
            else{
                System.out.println("NO se ha podido anadir el empleado al proyecto");
            }
        }
        else{
            System.out.println("NO existe el proyecto/empleado con ese ID");
        }
    }
    
    public static void addEmpleadosToProyecto(){
        Scanner scanner = new Scanner(System.in);
        int idPro;
        Proyecto pro;
        List<Empleado> empleados = new ArrayList<>();
        String ids;
        
        System.out.println("Introduce el ID del proyecto");
        idPro = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce los ID de los empleados separados por espacio");
        ids = scanner.nextLine();
        String[] emIds = ids.strip().split(" ");
        
        if ((pro = ProyectoDao.getProyectoById(idPro)) != null) {
            for (String id : emIds) {
                Empleado emp = EmpleadoDao.getEmpleadoById(Integer.parseInt(id));
                if (emp != null) {
                    empleados.add(emp);
                } else {
                    System.out.println("No se encontró un empleado con ID: " + id);
                }
            }
            if(ProyectoDao.addEmpleadosToProyecto(pro, empleados)){
                System.out.println("Se han anadido los empleados al proyecto!");
            }
            else{
                System.out.println("NO se han podido anadir los empleados al proyecto");
            }
        } else {
            System.out.println("NO se ha encontrado un proyecto con ese ID");
        }
    }
    
    public static void deleteFromProyecto(){
        Scanner scanner = new Scanner(System.in);
        int idPro, idEm;
        Proyecto pro;
        Empleado em;
        
        System.out.println("Introduce el ID del proyecto");
        idPro = scanner.nextInt();
        System.out.println("Introduce el ID del empleado");
        idEm = scanner.nextInt();
        scanner.nextLine();
        
        if(((pro = ProyectoDao.getProyectoById(idPro)) != null) && ((em = EmpleadoDao.getEmpleadoById(idEm)) != null)){
            if(ProyectoDao.removeFromProyecto(pro,em)){
                System.out.println("Se ha quitado del proyecto al empleado");
            }
            else{
                System.out.println("NO se ha podido quitar del proyecto al empleado");
            }
        }
        else{
            System.out.println("NO existe el proyecto/empleado con ese ID");
        }
    }
    
    public static void getFutureProyects(){
        List<Proyecto> proyectos = ProyectoDao.getFutureProyectos();
        
        if (proyectos != null) {
            for (Proyecto proyecto : proyectos) {
                System.out.println(proyecto.toString());
            }
        }
        else{
            System.out.println("NO hay proyectos futuros");
        }
    }
}
