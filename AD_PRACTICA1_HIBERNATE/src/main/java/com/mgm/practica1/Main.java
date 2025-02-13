/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm.practica1;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
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
                    anadirProveedor();
                    break;
                }
                case 2:{
                    modificarProveedor();
                    break;
                }
                case 3:{
                    borrarProveedor();
                    break;
                }
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:{
                    listarDetallesProveedor();
                    break;
                }
                case 15:
                case 16:{
                    System.out.println("Has salido del programa!");
                    break;
                }
                default:{
                    System.out.println("Opcion invalida");
                }
            }
        }while(opcion != 16);
    }
    
    public static int menu(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        System.out.println("\n1. Agregar proveedor");
        System.out.println("2. Modificar proveedor");
        System.out.println("3. Borrar proveedor");
        System.out.println("4. Agregar actividad");
        System.out.println("5. Borrar actividad");
        System.out.println("6. Agregar un cliente");
        System.out.println("7. Modificar un cliente");
        System.out.println("8. Borrar cliente");
        System.out.println("9. Comprar actividad");
        System.out.println("10. Cancelar compra");
        System.out.println("11. Listar todos los clientes");
        System.out.println("12. Listar todas las actividades futuras");
        System.out.println("13. Listar detalles de un cliente");
        System.out.println("14. Listar detalles de  un proveedor");
        System.out.println("15. Listar detalle de una actividad");
        System.out.println("16. Salir");
        System.out.println("Digita una opcion");
        opcion = scanner.nextInt();
        
        return opcion;
    }
    
    public static void anadirProveedor(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce el cif del proveedor");
        String cif = scanner.nextLine();
        
        Proveedor pro = ProveedorDao.getProveedorByCif(cif);
        if(pro == null){
            System.out.println("Introduce el nombre del proveedor");
            String nombre = scanner.nextLine();
            System.out.println("Introduce el email del proveedor");
            String email = scanner.nextLine();
            
            Integer id = ProveedorDao.anadirProveedor(nombre,email,cif);
            if(id != null){
                System.out.println("Se ha anadido el proveedor! Su ID es: " + id);
            }
            else{
                System.out.println("NO se ha podido anadir el proveedor");
            }
        }
        else{
            System.out.println("Ya existe ese proveedor");
        }      
    }
    
    public static void modificarProveedor(){
        Scanner scanner = new Scanner(System.in);
        Proveedor pro;
        
        System.out.println("Introduce el ID del proveedor a modificar");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        pro = ProveedorDao.getProveedorById(id);
        if (pro != null) {
            
            System.out.println("Introduce el nombre del proyecto (deja en blanco para mantener el actual}");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                pro.setNombre(nombre);
            }
            
            System.out.println("Introduce el email del proyecto {deja en blanco para mantener el actual}");
            String email = scanner.nextLine();
            if (!email.isEmpty()){
                pro.setEmail(email);
            }
            
            System.out.println("Introduce el cif del proyecto {deja en blanco para mantener el actual}");
            String cif = scanner.nextLine();
            if (!cif.isEmpty()){
                pro.setCif(cif);
            }
            
            if (ProveedorDao.modificarProveedor(pro.getId(),pro.getNombre(),pro.getEmail(),pro.getCif())){
                System.out.println("Se ha modificado el proveedor!");
            }
            else{
                System.out.println("NO se ha podido modificar el proveedor");
            }
        }
        else{
            System.out.println("NO existe un proveedor con ese ID");
        }
    }
    
    public static void borrarProveedor(){
        Scanner scanner = new Scanner(System.in);
        Proveedor pro;
        
        System.out.println("Introduce el id del proveedor a borrar");
        int id = scanner.nextInt();
        
        pro = ProveedorDao.getProveedorById(id);
        if (pro != null){
            if (ProveedorDao.borrarProveedor(pro.getId())){
                System.out.println("Se ha borrado el proveedor!");
            }
            else{
                System.out.println("NO se ha podido borrar el proveedor");
            }
        }
        else{
            System.out.println("NO existe un proveedor con ese ID");
        }
    }
    
    public static void listarDetallesProveedor(){
        Scanner scanner = new Scanner(System.in);
        Proveedor pro;
        
        System.out.println("Introduce el id del proveedor");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        pro = ProveedorDao.getProveedorById(id);
        if (pro != null){
            System.out.println("ID: " + pro.getId());
            System.out.println("Nombre: " + pro.getNombre());
            System.out.println("Email: " + pro.getEmail());
            System.out.println("\nActividades creadas:");
            System.out.println("ID|\tNombre|\tFecha|\tUbicacion|\tPlazas disponibles");
            List <Actividad> actividades = pro.getActividadList();
            
            for(Actividad act : actividades){
                System.out.println(act.getId() + "\t" + act.getFecha() + "\t" + act.getUbicacion() + "\t" + act.getPlazasDisponibles());
            }
        }
        else{
            System.out.println("NO existe un proveedor con ese ID");
        }
    }
}
