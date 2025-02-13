/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm.practica1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                case 4:{
                    anadirActividad();
                    break;
                }
                case 5:{
                    borrarActividad();
                    break;
                }
                case 6:{
                    anadirCliente();
                    break;
                }
                case 7:{
                    modificarCliente();
                    break;
                }
                case 8:{
                    borrarCliente();
                    break;
                }
                case 9:{
                    comprarActividad();
                    break;
                }
                case 10:{
                    cancelarCompra();
                    break;
                }
                case 11:{
                    listarClientes();
                    break;
                }
                case 12:{
                    listarActividadesFuturas();
                    break;
                }
                case 13:{
                    listarDetallesCliente();
                    break;
                }
                case 14:{
                    listarDetallesProveedor();
                    break;
                }
                case 15:{
                    listarDetallesActividad();
                    break;
                }
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
            
            System.out.println("Introduce el nombre del proveedor (deja en blanco para mantener el actual}");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                pro.setNombre(nombre);
            }
            
            System.out.println("Introduce el email del proveedor {deja en blanco para mantener el actual}");
            String email = scanner.nextLine();
            if (!email.isEmpty()){
                pro.setEmail(email);
            }
            
            System.out.println("Introduce el cif del proveedor {deja en blanco para mantener el actual}");
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
            System.out.println("ID|Nombre|Fecha|Ubicacion|Plazas disponibles\n");
            List <Actividad> actividades = pro.getActividadList();
            
            for(Actividad act : actividades){
                System.out.println(act.getId() + "|" + act.getNombre() + "|" + act.getFecha() + "|" + act.getUbicacion() + "|" + act.getPlazasDisponibles());
            }
        }
        else{
            System.out.println("NO existe un proveedor con ese ID");
        }
    }
    
    public static void anadirActividad(){
        try {
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Introduce el nombre de la actividad");
            String nombre = scanner.nextLine();
            System.out.println("Introduce la fecha de la actividad {yyyy-mm-dd}");
            String fecha = scanner.nextLine();
            System.out.println("Introduce la ubicacion de la actividad");
            String ubicacion = scanner.nextLine();
            System.out.println("Introduce las plazas disponibles de la actividad");
            String plazas = scanner.nextLine();
            System.out.println("Introduce el CIF del proveedor de la actividad");
            String cif = scanner.nextLine();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaFormateada = formatter.parse(fecha);
            int plazasNum = Integer.parseInt(plazas);
            
            Integer id = ActividadDao.anadirActividad(nombre, fechaFormateada, ubicacion, plazasNum, cif);
            if (id != null) {
                System.out.println("Se ha anadido la actividad! Su ID es: " + id);
            }
            else {
                System.out.println("NO se ha podido anadir la actividad");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void borrarActividad(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce el id de la actividad a borrar");
        int id = scanner.nextInt();
        
        Actividad act = ActividadDao.getActividadById(id);
        if (act != null) {
            if (ActividadDao.borrarActividad(act.getId())){
                System.out.println("Se ha borrado la actividad!");
            }
            else{
                System.out.println("NO se ha podido borrar la actividad");
            }
        }
        else {
            System.out.println("NO existe ninguna actividad con ese ID");
        }
    }
    
    public static void anadirCliente(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce el email del cliente");
        String email = scanner.nextLine();
        
        Cliente cli = ClienteDao.getClienteByEmail(email);
        if (cli == null){
            System.out.println("Introduzca el nombre del cliente");
            String nombre = scanner.nextLine();
            
            Integer id = ClienteDao.anadirCliente(nombre,email);
            if (id != null) {
                System.out.println("Se ha anadido el cliente! Su ID es: " + id);
            }
            else {
                System.out.println("NO se ha podido anadir al cliente");
            }
        }
        else {
            System.out.println("NO se ha podido anadir el cliente, ya existe ese cliente");
        }
    }
    
    public static void modificarCliente(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce el id del cliente a modificar");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Cliente cli = ClienteDao.getClienteById(id);
        if (cli != null) {
            System.out.println("Introduce el nombre del cliente (deja en blanco para mantener el actual)");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                cli.setNombre(nombre);
            }

            System.out.println("Introduce el email del cliente (deja en blanco para mantener el actual)");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                cli.setEmail(email);
            }
            
            if (ClienteDao.modificarCliente(cli.getId(), cli.getNombre(), cli.getEmail())) {
                System.out.println("Se ha modificado el cliente!");
            }
            else {
                System.out.println("NO se ha podido modificar el cliente");
            }
        }
        else {
            System.out.println("NO existe ningun cliente con ese ID");
        }
    }
    
    public static void borrarCliente(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce el id del cliente a borrar");
        int id = scanner.nextInt();
        
        Cliente cli = ClienteDao.getClienteById(id);
        if (cli != null){
            if (ClienteDao.borrarCliente(cli.getId())) {
                System.out.println("Se ha borrado el cliente!");
            }
            else {
                System.out.println("NO se ha podido borrar el cliente");
            }
        }
        else {
            System.out.println("NO existe un cliente con ese ID");
        }
    }
    
    public static void comprarActividad(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduzca el id de la actividad a comprar");
        int idAct = scanner.nextInt();
        System.out.println("Introduzca el id del cliente");
        int idCli = scanner.nextInt();
        
        Cliente cli = ClienteDao.getClienteById(idCli);
        Actividad act = ActividadDao.getActividadById(idAct);
        
        if ((cli != null) && (act != null)) {
            if (ActividadDao.comprarActividad(cli.getId(),act.getId())) {
                System.out.println("Se ha comprado la actividad!");
            }
            else {
                System.out.println("NO se ha podido comprar la actividad. La actividad ya se ha realizado/No quedan plazas");
            }
        }
        else {
            System.out.println("No existe cliente y/o actividad con ese ID");
        }
    }
    
    public static void cancelarCompra(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduzca el id de la actividad a cancelar");
        int idAct = scanner.nextInt();
        System.out.println("Introduzca el id del cliente que cancela");
        int idCli = scanner.nextInt();
        
        Cliente cli = ClienteDao.getClienteById(idCli);
        Actividad act = ActividadDao.getActividadById(idAct);
        
        if ((cli != null) && (act != null)) {
            if (ActividadDao.cancelarCompra(cli.getId(),act.getId())) {
                System.out.println("Se ha cancelado la compra de la actividad!");
            }
            else {
                System.out.println("NO se ha podido cancelar la compra de la actividad. La actividad ya se ha realizado");
            }
        }
        else {
            System.out.println("No existe cliente y/o actividad con ese ID");
        }
    }
    
    public static void listarClientes(){
        List<Cliente> clientes = ClienteDao.getClientes();
        
        if (!clientes.isEmpty()) {
            System.out.println("ID|NOMBRE|EMAIL");
            for (Cliente cli : clientes) {
                System.out.println(cli.toString());
            }
        }
        else {
            System.out.println("NO hay clientes");
        }
    }
    
    public static void listarActividadesFuturas(){
        List<Actividad> actividades = ActividadDao.getFutureActividades();
        
        if (!actividades.isEmpty()) {
            System.out.println("ID|NOMBRE|FECHA|PLAZAS|UBICACION|NOMBRE PROVEEDOR");
            for (Actividad act : actividades) {
                System.out.println(act.toString());
            }
        }
        else {
            System.out.println("NO hay actividades");
        }
    }
    
    public static void listarDetallesCliente() {
        Scanner scanner = new Scanner(System.in);
        Cliente cli;
        
        System.out.println("Introduce el id del cliente");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        cli = ClienteDao.getClienteById(id);
        if (cli != null) {
            System.out.println("ID: " + cli.getId());
            System.out.println("Nombre: " + cli.getNombre());
            System.out.println("Email: " + cli.getEmail());
            System.out.println("\nCompras realizadas:");
            System.out.println("ID ACTIVIDAD|NOMBRE|UBICACION|NOMBRE PROVEEDOR|FECHA ACTIVIDAD|FECHA COMPRA");
            List<Compra> compras = cli.getCompraList();
            
            for (Compra compra : compras) {
                Actividad act = ActividadDao.getActividadById(compra.getActividad().getId());
                if (act != null) {
                    System.out.println(act.getId() + "|" + act.getNombre() + "|" + act.getUbicacion() + "|" + compra.getCliente().getId() + "|" + act.getFecha() + "|" + compra.getFechaCompra());
                }
            }
        }
        else {
            System.out.println("NO existe un cliente con ese ID");
        }
    }
    
    public static void listarDetallesActividad() {
        Scanner scanner = new Scanner(System.in);
        Actividad act;
        
        System.out.println("Introduce el id de la actividad");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        act = ActividadDao.getActividadById(id);
        if (act != null) {
            System.out.println("ID: " + act.getId());
            System.out.println("Nombre: " + act.getNombre());
            System.out.println("Fecha: " + act.getFecha());
            System.out.println("Ubicacion: " + act.getUbicacion());
            System.out.println("Plazas Disponibles: " + act.getPlazasDisponibles());
            System.out.println("ID Proveedor: " + act.getProveedor().getId());
            System.out.println("Nombre Proveedor: " + act.getProveedor().getNombre());
            System.out.println("\nClientes:");
            System.out.println("ID|NOMBRE|EMAIL|FECHA COMPRA");
            List<Compra> compras = act.getCompraList();
            for (Compra compra : compras) {
                System.out.println(compra.getCliente().getId() + "|" + compra.getCliente().getNombre() + "|" + compra.getCliente().getEmail() + "|" + compra.getFechaCompra());
            }
        }
        else {
            System.out.println("NO existe una actividad con ese ID");
        }
    }
}
