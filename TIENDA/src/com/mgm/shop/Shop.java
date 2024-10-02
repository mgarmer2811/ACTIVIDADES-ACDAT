/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.shop;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Shop {
    private static ArrayList<Product> products = new ArrayList<Product>();
    
    public static void loadProducts() {
        Product producto = null;
        int id;
        String name;
        float price;
        int stock;
        
        FileInputStream fin = null;
        DataInputStream din = null;
        try{
            File file = new File("inventory.dat");
            fin = new FileInputStream(file);
            din = new DataInputStream(fin);
            
            while(true){
                try{
                    id = din.readInt();
                    name = din.readUTF();
                    price = din.readFloat();
                    stock = din.readInt();
                    
                    producto = new Product(id,name,price,stock);
                    products.add(producto);
                }
                catch(EOFException eof){
                    break;
                }
            }
        }
        catch(Exception e){}
        finally{
            try{
                if(din != null)
                    din.close();
            }
            catch(Exception e){}
        }
    }
    
    public static void addProduct() {
        Product producto;
        int id;
        String name;
        float price;
        int stock;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("");
        System.out.println("");
        System.out.println("Introduce el codigo de producto");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el nombre del producto");
        name = scanner.nextLine();
        System.out.println("Introduce el precio del producto");
        scanner.nextLine();
        price = scanner.nextFloat();
        System.out.println("Introduce el stock del producto");
        stock = scanner.nextInt();
        scanner.nextLine();
        
        producto = new Product(id,name,price,stock);
        FileOutputStream fout = null;
        DataOutputStream dout = null;
        try{
            File file = new File("inventory.dat");
            fout = new FileOutputStream(file,true);
            dout = new DataOutputStream(fout);
            
            dout.writeInt(id);
            dout.writeUTF(name);
            dout.writeFloat(price);
            dout.writeInt(stock);
        }
        catch(Exception e){
            System.out.println("No se ha podido añadir el producto");
        }
        finally{
            try{
                if(dout != null)
                    dout.close();
            }
            catch(Exception e){}
        }
    }
    
    public static void updateProduct(int id) {
        loadProducts();
        int iterator = 0;
        int index = 0;
        boolean found = false;
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        
        while((iterator < products.size()) && (!found)){
            if(products.get(iterator).getIdProduct() == id){
                found = true;
                index = iterator;
            }
            else{
                iterator++;
            }
        }
        
        if(found){
            System.out.println("");
            System.out.println("");
            System.out.println("1. Modificar nombre del producto");
            System.out.println("2. Modificar precio del producto");
            System.out.println("3. Modificar stock del producto");
            System.out.println("4. Modificar precio Y stock del producto");
            System.out.print("Elija una opcion: ");
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch(option){
                case 1:
                {
                    System.out.println("");
                    System.out.println("Introduzca el nuevo nombre del producto");
                    String newName = scanner.nextLine();
                    
                    products.get(index).setNameProduct(newName);
                    FileOutputStream fout = null;
                    DataOutputStream dout = null;
                    
                    try{
                        File file = new File("inventory.dat");
                        fout = new FileOutputStream(file,false);
                        dout = new DataOutputStream(fout);
                        
                        for(Product producto : products){
                            dout.writeInt(producto.getIdProduct());
                            dout.writeUTF(producto.getNameProduct());
                            dout.writeFloat(producto.getPriceProduct());
                            dout.writeInt(producto.getStockProduct());
                        }
                    }
                    catch(Exception ex){}
                    finally{
                        try{
                            if(dout != null)
                                dout.close();
                        }
                        catch(Exception ex){}
                    }
                }
                break;
                case 2:
                {
                    System.out.println("");
                    System.out.println("Introduzca el nuevo precio del producto");
                    Float newPrice = scanner.nextFloat();
                    
                    products.get(index).setPriceProduct(newPrice);
                    FileOutputStream fout = null;
                    DataOutputStream dout = null;
                    
                    try{
                        File file = new File("inventory.dat");
                        fout = new FileOutputStream(file,false);
                        dout = new DataOutputStream(fout);
                        
                        for(Product producto : products){
                            dout.writeInt(producto.getIdProduct());
                            dout.writeUTF(producto.getNameProduct());
                            dout.writeFloat(producto.getPriceProduct());
                            dout.writeInt(producto.getStockProduct());
                        }
                    }
                    catch(Exception ex){}
                    finally{
                        try{
                            if(dout != null)
                                dout.close();
                        }
                        catch(Exception ex){}
                    }
                }
                break;
                case 3:
                {
                    System.out.println("");
                    System.out.println("Introduzca el nuevo stock del producto");
                    int newStock = scanner.nextInt();
                    scanner.nextLine();
                    
                    products.get(index).setStockProduct(newStock);
                    FileOutputStream fout = null;
                    DataOutputStream dout = null;
                    
                    try{
                        File file = new File("inventory.dat");
                        fout = new FileOutputStream(file,false);
                        dout = new DataOutputStream(fout);
                        
                        for(Product producto : products){
                            dout.writeInt(producto.getIdProduct());
                            dout.writeUTF(producto.getNameProduct());
                            dout.writeFloat(producto.getPriceProduct());
                            dout.writeInt(producto.getStockProduct());
                        }
                    }
                    catch(Exception ex){}
                    finally{
                        try{
                            if(dout != null)
                                dout.close();
                        }
                        catch(Exception ex){}
                    }
                }
                break;
                case 4:
                {
                    System.out.println("");
                    System.out.println("Introduzca el nuevo precio del producto");
                    float newPrice = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Introduzca el nuevo stock del producto");
                    int newStock = scanner.nextInt();
                    
                    products.get(index).setPriceProduct(newPrice);
                    products.get(index).setStockProduct(newStock);
                    FileOutputStream fout = null;
                    DataOutputStream dout = null;
                    
                    try{
                        File file = new File("inventory.dat");
                        fout = new FileOutputStream(file,false);
                        dout = new DataOutputStream(fout);
                        
                        for(Product producto : products){
                            dout.writeInt(producto.getIdProduct());
                            dout.writeUTF(producto.getNameProduct());
                            dout.writeFloat(producto.getPriceProduct());
                            dout.writeInt(producto.getStockProduct());
                        }
                    }
                    catch(Exception ex){}
                    finally{
                        try{
                            if(dout != null)
                                dout.close();
                        }
                        catch(Exception ex){}
                    }
                }
            }
        }
        else{
            System.out.println("No se ha encontrado el producto que usted desea modificar");
        }
    }
    
    public static void deleteProduct(int id) {
        loadProducts();
        int iterator = 0;
        boolean found = false;
        
        while((iterator < products.size()) && (!found)){
            if(products.get(iterator).getIdProduct() == id){
                found = true;
            }
            else{
                iterator++;
            }
        }
        
        if(found){
            FileOutputStream fout = null;
            DataOutputStream dout = null;
            
            try{
                File file = new File("inventory.dat");
                fout = new FileOutputStream(file,false);
                dout = new DataOutputStream(dout);
                
                for(Product producto : products){
                    if(producto.getIdProduct() != id){
                        dout.writeInt(producto.getIdProduct());
                        dout.writeUTF(producto.getNameProduct());
                        dout.writeFloat(producto.getPriceProduct());
                        dout.writeInt(producto.getStockProduct());
                    }
                }
            }
            catch(Exception e){
                System.out.println("No se puedo eliminar el producto");
            }
            finally{
                try{
                    if(dout != null){
                        dout.close();
                    }
                }
                catch(Exception ex){}
            }
        }
        else{
            System.out.println("El id introducido no pertenece a ningun producto");
        }
    }
    
    public static void checkProduct(int id) {
        loadProducts();
        boolean found = false;
        int iterator = 0;
        int index = 0;
        while((!found) && (iterator < products.size())){
            if(products.get(iterator).getIdProduct() == id){
                found = true;
                index = iterator;
            }
            else{
                iterator++;
            }
        }
        if(found){
            System.out.println(products.get(index).toString());
        }
        else{
            System.out.println("No se ha found ningun producto con ese codigo");
        }
    }
    
    public static void listProducts() {
        loadProducts();
        for(Product producto : products){
            System.out.println(producto.toString());
        }
    }
}
