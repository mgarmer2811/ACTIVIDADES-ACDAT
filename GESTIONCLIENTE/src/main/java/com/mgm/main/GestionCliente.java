package com.mgm.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionCliente {
    public static void main( String[] args ) {
       int opcion = 0;
       Conexion conexion = null;

       try{
        conexion = new Conexion();
        System.out.println("Se ha establecido la conexion correctamente");
       }
       catch(SQLException slqe){
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
                    anadirCliente(conexion);
                    break;
                }
                case 3:{
                    buscarPorEmail(conexion);
                    break;
                }
                case 4:{
                    modificarCliente(conexion);
                    break;
                }
                case 5:{
                    eliminarCliente(conexion);
                    break;
                }
                case 6:{
                    rankingClientes(conexion);
                    break;
                }
                case 7:{
                    anadirPedido(conexion);
                    break;
                }
                case 8:{
                    modificarPedido(conexion);
                    break;
                }
                case 9:{
                    eliminarPedido(conexion);
                    break;
                }
                case 10:{
                    iniciarSesion(conexion);
                    break;
                }
                case 11:{
                    cambiarContrasena(conexion);
                    break;
                }
                default:{
                    System.out.println("Has salido del programa");
                }
            }
       }
       while(opcion != 12);
       try{
           conexion.cerrarConexion();
       }
       catch(SQLException sqle){
           System.err.println("Se ha producido un error al cerrar la conexion con la base de datos");
       }
    }

    public static void listarClientes(Conexion conn) {
        String query = "SELECT * FROM cliente;";
        ResultSet rs;
        try{
            rs = conn.ejecutarQuery(query);
            
            rs.last();
            int numClientes = rs.getRow(); // Numero de columna final (cantidad clientes)
            rs.beforeFirst();
            
            System.out.println("Hay " + numClientes + " clientes:");
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String ciudad = rs.getString("ciudad");
                String telefono = rs.getString("telefono");
                
                Cliente cliente = new Cliente(id,nombre,email,ciudad,telefono);
                System.out.println(cliente.toString());
            }
        }
        catch(SQLException slqe){
            System.err.println("Se ha producido un error ejecutando la busqueda");
        }
    }

    public static void anadirCliente(Conexion conn) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el email del nuevo cliente");
        String email = scanner.nextLine();
        if(!checkEmail(conn,email)){
            System.out.println("Introduzca el nombre del nuevo cliente");
            String nombre = scanner.nextLine();
            System.out.println("Introduzca la ciudad del nuevo cliente");
            String ciudad = scanner.nextLine();
            System.out.println("Introduzca el telefono del nuevo cliente");
            String telefono = scanner.nextLine();
            String executeQuery = "INSERT INTO `cliente` (`nombre`, `email`, `ciudad`, `telefono`) VALUES "
                                      + "('" + nombre + "', '" + email + "', '" + ciudad + "', '" + telefono + "');";
            try{
                conn.ejecutar(executeQuery);
                System.out.println("Se ha añadido correctamente el cliente");
            }
            catch(SQLException sqlei){
                System.err.println("Se ha producido un error añadiendo el cliente");
            }
        }
        else{
            System.out.println("Lo sentimos, ya existe un cliente registrado con ese correo");
        }
    }

    public static void buscarPorEmail(Conexion conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el email/fragmento de email");
        String email = scanner.nextLine();
        String query = "SELECT * FROM cliente WHERE email LIKE '%" + email + "%'";
        ResultSet rs;
        boolean existing = true;

        try {
            rs = conn.ejecutarQuery(query);
            while(rs.next()){
                existing = true;
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String emailC = rs.getString("email");
                String ciudad = rs.getString("ciudad");
                String telefono = rs.getString("telefono");
                
                Cliente cliente = new Cliente(id,nombre,emailC,ciudad,telefono);
                System.out.println(cliente.toString());
            }
        } catch (SQLException sqle) {
            System.err.println("Se ha producido un error en la busqueda");
        }
        if(!existing){
            System.out.println("No se ha encontrado ninguna coincidencia");
        }
    }

    public static void modificarCliente(Conexion conn){
        Scanner scanner = new Scanner(System.in);
        String nombre = "",email = "",ciudad = "",telefono = "",aux = "";
        int filasAfectadas = 0;

        System.out.println("Introduzca el email del cliente que desea modificar");
        email = scanner.nextLine();

        if(checkEmail(conn, email)){
            String getCliente = "SELECT * FROM cliente WHERE email = '" + email + "'";
            try{
                ResultSet rs = conn.ejecutarQuery(getCliente);
                if(rs.next()){
                    nombre = rs.getString("nombre");
                    ciudad = rs.getString("ciudad");
                    telefono = rs.getString("telefono");
                }
                System.out.println("Introduzca el nuevo nombre del cliente {ENTER, no modificar}");
                aux = scanner.nextLine();
                if(!aux.isBlank()){
                    nombre = aux;
                }
                System.out.println("Introduzca la nueva ciudad del cliente {ENTER, no modificar}");
                aux = scanner.nextLine();
                if(!aux.isBlank()){
                    ciudad = aux;
                }
                System.out.println("Introduzca el nuevo telefono del cliente {ENTER, no modificar}");
                aux = scanner.nextLine();
                if(!aux.isBlank()){
                    telefono = aux;
                }

                try{
                    String updateNombre = "UPDATE cliente SET nombre = '" + nombre + "' WHERE email = '" + email + "'";
                    String updateCiudad = "UPDATE cliente SET ciudad = '" + ciudad + "' WHERE email = '" + email + "'";
                    String updateTelefono = "UPDATE cliente SET telefono = '" + telefono + "' WHERE email = '" + email + "'";

                    int filasNombre = conn.ejecutar(updateNombre);
                    int filasCiudad = conn.ejecutar(updateCiudad);
                    int filasTelefono = conn.ejecutar(updateTelefono);

                    filasAfectadas = filasNombre + filasCiudad + filasTelefono;
                }
                catch(SQLException slqei){
                    System.err.println("Se ha producido un error modificacndo los datos del cliente");
                }
                if(filasAfectadas > 0){
                    System.out.println("Se han modificado los datos del cliente");
                }
                else{
                    System.out.println("No se ha modificado ningun dato del cliente");
                }
            }
            catch(SQLException sqle){
                System.err.println("Se ha producido un error recuperando datos del cliente");
            }
        }
        else{
            System.out.println("No se ha encontrado ninguna coincidencia, no se ha modificado nada");
        }
    }

    public static void eliminarCliente(Conexion conn) {
        Scanner scanner = new Scanner(System.in);
        int filasCliente = 0,filasPedido = 0;

        System.out.println("Introduzca el email del cliente a borrar");
        String email = scanner.nextLine();
        if(checkEmail(conn, email)){
            String borrarCliente = "DELETE FROM `cliente` WHERE `email` = '" + email + "';";
            String eliminarPedidos = "DELETE FROM `pedido` WHERE `email` = '" + email + "'";

            try{
                filasCliente = conn.ejecutar(borrarCliente);
                filasPedido = conn.ejecutar(eliminarPedidos);
            }
            catch(SQLException sqle){
                System.err.println("Se ha producido un error al eliminar al cliente");
            }

            if((filasCliente > 0) && (filasPedido > 0)){
                System.out.println("Se ha borrado el cliente correctamente");
            }
            else{
                System.out.println("No se ha borrado del todo el cliente");
            }
        }
        else{
            System.out.println("No se ha encontrado ninguna coincidencia, no se ha borrado nada");
        }
    }

    public static void rankingClientes(Conexion conn) {
        String query = "SELECT c.nombre,c.email,COUNT(p.id) 'numero_pedidos',SUM(p.precio_total) 'gasto_total' FROM cliente c JOIN pedido p ON c.id = p.id_cliente GROUP BY c.id ORDER BY gasto_total DESC";
        ResultSet rs;

        try{
            rs = conn.ejecutarQuery(query);
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String numPedidos = rs.getString("numero_pedidos");
                String totalGastado = rs.getString("gasto_total");
                
                System.out.println("Cliente: " + nombre + "\tEmail del cliente: " + email + "\tNumero de pedidos: " + numPedidos + "\tTotal Gastado: " + totalGastado);
            }
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error recuperando la información");
        }
    }

    public static void anadirPedido(Conexion conn) {
        Scanner scanner = new Scanner(System.in);
        String email = "",fecha = "",precio = "",nombre = "",ciudad = "",telefono = "";
        int id = 0, filasAfectadas = 0;
        System.out.println("Introduce el email del cliente que ha realizado la compra");
        email = scanner.nextLine();

        if(!checkEmail(conn, email)){
            System.out.println("Introduce el nombre del cliente");
            nombre = scanner.nextLine();
            System.out.println("Introduce la ciudad del cliente");
            ciudad = scanner.nextLine();
            System.out.println("Introduce el telefono del cliente");
            telefono = scanner.nextLine();

            try{
                String query = "INSERT INTO `cliente` (`nombre`, `email`, `ciudad`, `telefono`) VALUES "
                                      + "('" + nombre + "', '" + email + "', '" + ciudad + "', '" + telefono + "');";
                conn.ejecutar(query);
            }
            catch(SQLException sqlei){
                System.err.println("Se ha producido un error añadiendo al cliente");
            }
        }

        System.out.println("Introduce la fecha en que se hizo el pedido {AAAA-MM-DD}");
        fecha = scanner.nextLine();
        System.out.println("Introduce el gasto al que asciende el pedido {12.5, se utiliza el punto}");
        precio = scanner.nextLine();

        try{
            String queryCliente = "SELECT id FROM cliente WHERE email = '" + email + "'";
            ResultSet rs = conn.ejecutarQuery(queryCliente);
            if(rs.next()){
                id = rs.getInt("id");
            }
            try{
                String anadirPedido = "INSERT INTO `pedido` (`id_cliente`, `fecha`, `precio_total`) VALUES (" + id + ",'" + fecha + "','" + precio + "')";
                filasAfectadas = conn.ejecutar(anadirPedido);
            }
            catch(SQLException sqlei){
                System.err.println("Se ha producido un error añadiendo el pedido");
            }
        }
        catch(SQLException slqc){
            System.err.println("Se ha producido un error obteniendo el id del cliente");
        }
        if(filasAfectadas > 0){
            System.out.println("Se ha añadido correctamente el pedido");
        }
    }

    public static void modificarPedido(Conexion conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id del pedido que desea modificar");
        int id = scanner.nextInt();
        scanner.nextLine();
        int filasAfectadas = 0, filasFecha = 0, filasGasto = 0;
        String fecha = "", gasto = "", aux = "";

        if(checkId(conn, id) != -1){
            String query = "SELECT fecha,precio_total FROM pedido WHERE id = " + id + ";";
            try{
                ResultSet rs = conn.ejecutarQuery(query);
                if(rs.next()){
                    fecha = rs.getString("fecha");
                    gasto = rs.getString("precio_total");
                }
                System.out.println("Introduzca la nueva fecha del pedido {ENTER, no modificar}");
                aux = scanner.nextLine();
                if(!aux.isBlank()){
                    fecha = aux;
                }
                System.out.println("Introduzca el nuevo gasto del pedido {ENTER, no modificar}");
                aux = scanner.nextLine();
                if(!aux.isBlank()){
                    gasto = aux;
                }

                String updateFecha = "UPDATE pedido SET fecha = '" + fecha + "' WHERE id = " + id + ";";
                String updateGasto = "UPDATE pedido SET precio_total = '" + gasto + "' WHERE id = " + id + ";";

                try{
                    filasFecha = conn.ejecutar(updateFecha);
                    filasGasto = conn.ejecutar(updateGasto);
                    filasAfectadas = filasFecha + filasGasto;
                }
                catch(SQLException sqlei){
                    System.err.println("Se ha producido un error en la modificacion del pedido");
                }
            }
            catch(SQLException sqle){
                System.err.println("Se ha producido un error en la recuperacion del pedido");
            }
            if(filasAfectadas > 0){
                System.out.println("Se ha  modificado el pedido correctamente");
            }
        }
        else{
            System.out.println("No se ha encontrado ninguna coincidencia, no se ha modificado nada");
        }
    }

    public static void eliminarPedido(Conexion conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id del pedido que desea borrar");
        int id = scanner.nextInt();
        int filasAfectadas = 0;

        if(checkId(conn, id) != -1){
            String eliminarPedido = "DELETE FROM `pedido` WHERE id = " + id + ";";
            try{
                filasAfectadas = conn.ejecutar(eliminarPedido);
            }
            catch(SQLException sqle){
                System.err.println("Se ha producido un error borrando el pedido");
            }
            if(filasAfectadas > 0){
                System.out.println("Se ha borrado el pedido correctamente");
            }
        }
        else{
            System.out.println("No se ha encontrado ninguna coincidencia, no se ha borrado nada");
        }
    }
    
    public static void iniciarSesion(Conexion conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca email");
        String email = scanner.nextLine();
        System.out.println("Introduzca contraseña");
        String contrasena = scanner.nextLine();
        boolean existing = false;
        
        if(checkEmail(conn,email)){
            String sql = "SELECT * FROM cliente WHERE email = '" + email + "' AND contrasena = '" + contrasena + "';";
            try{
                ResultSet rs = conn.ejecutarQuery(sql);
                if(rs.next()){
                    existing = true;
                    String nombre = rs.getString("nombre");
                    System.out.println("Hola " + nombre);
                }
            }
            catch(SQLException sqle){
                System.err.println("Se ha producido un error ejecutando la query");
            }
        }
        if(!existing){
            System.out.println("Usuario o contraseña erroneo");
        }
    }
    
    public static void cambiarContrasena(Conexion conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el email");
        String email = scanner.nextLine();
        System.out.println("Introduzca la nueva contraseña");
        String nuevaContrasena = scanner.nextLine();
        
        if(checkEmail(conn,email)){
            String sql = "UPDATE cliente SET contrasena = '" + nuevaContrasena + "' WHERE email = '" + email + "';";
            try{
                int filasAfectadas = conn.ejecutar(sql);
                if(filasAfectadas > 0){
                    System.out.println("Se ha modificado la contraseña correctamente");
                }
            }
            catch(SQLException slqe){
                System.err.println("Se ha producido un error actualizando la contraseña");
            }
        }
        else{
            System.out.println("Usuario no encontrado");
        }
    }

    public static boolean checkEmail(Conexion conn, String email) {
        String sql = "SELECT email FROM cliente WHERE email = '" +  email + "'";
        ResultSet rs;
        boolean existing = false;

        try{
            rs = conn.ejecutarQuery(sql);
            if(rs.next()){
                existing = true;
            }
        }
        catch(SQLException  sqle){
            System.err.println("Se ha producido un error buscando el email");
        }

        return existing;
    }

    public static int checkId(Conexion conn, int id) {
        int idPedido = -1;
        ResultSet rs;
        String query = "SELECT id FROM pedido WHERE id = " + id + ";";
        
        try{
            rs = conn.ejecutarQuery(query);
            if(rs.next()){
                idPedido = rs.getInt("id");
            }
        }
        catch(SQLException sqle){
            System.err.println("Se ha producido un error buscando el id");
        }

        return idPedido;
    }

    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        System.out.println("");
        System.out.println("");
        System.out.println("1. Listar todos los clientes");
        System.out.println("2. Añadir un cliente");
        System.out.println("3. Buscar cliente por email/fragmento de email");
        System.out.println("4. Modificar datos de cliente");
        System.out.println("5. Borra un cliente");
        System.out.println("6. Ranking de clientes. Se muestra cliente, numero de pedidos de ese cliente y gasto total");
        System.out.println("7. Añadir un pedido");
        System.out.println("8. Actualizar datos de un pedido");
        System.out.println("9. Borrar datos de un pedido");
        System.out.println("10. Iniciar Sesion");
        System.out.println("11. Borrar datos de un pedido");
        System.out.println("12. Salir");
        System.out.println("Elija una opcion");
        opcion = scanner.nextInt();
        System.out.println("");
        System.out.println("");
        
        return opcion;
    }
}
