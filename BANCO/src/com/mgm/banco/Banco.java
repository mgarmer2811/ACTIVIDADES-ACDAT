/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.banco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Banco {
    private static ArrayList<CuentaBancaria> bankAccounts = new ArrayList<>();
    
    public static void loadData() {
        FileReader fr = null;
        BufferedReader br = null;
        String fileName = "accountsData.txt";
        String[] lineData;
        try{
            File file = new File(fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String dataLine;
            CuentaBancaria tempAccount;
            
            while((dataLine = br.readLine()) != null){
                lineData = dataLine.split(",");
                
                tempAccount = new CuentaBancaria(lineData[0],lineData[1],lineData[2],Float.parseFloat(lineData[3]));
                bankAccounts.add(tempAccount);
            }
            br.close();
        }
        catch(IOException ioe){
            if(!(new File(fileName).exists())){
                System.err.println("Se ha producido un error porque el fichero no existe");
                System.out.println("Procedemos a crear el fichero");
                try{
                    File newFile = new File(fileName);
                    FileWriter fw = new FileWriter(newFile);
                }
                catch(IOException ioeC){
                    System.err.println("Se ha producido un error creando el fichero");
                }
            }
            else{
                System.err.println("Se ha producido un error en la lectura del fichero");
            }
        }
    }
    
    public static void addAccount() {
        Scanner scanner = new Scanner(System.in);
        FileWriter fw = null;
        BufferedWriter bw = null;
        
        System.out.println("");
        System.out.println("Introduzca el nombre del titular");
        String newTitularName = scanner.nextLine();
        System.out.println("Introduzca el DNI del titular (8 digitos y la letra)");
        String newTitularDNI= scanner.nextLine();
        System.out.println("Introduzca el saldo inicial de la nueva cuenta");
        float newBalance = scanner.nextFloat();
        
        CuentaBancaria newAccount = new CuentaBancaria();
        String stringAccount = newAccount.getAccountNumber() + "," + newTitularName + "," + newTitularDNI + "," + newBalance + "\n";
        
        try{
            File file = new File("accountsData.txt");
            fw = new FileWriter(file,true);
            bw = new BufferedWriter(fw);
            
            bw.write(stringAccount);
            bw.flush();
            bw.close();
            
            newAccount.setTitularName(newTitularName);
            newAccount.setTitularDNI(newTitularDNI);
            newAccount.setAccountBalance(newBalance);
            
            bankAccounts.add(newAccount);
        }
        catch(IOException ioe){
            System.err.println("Se ha producido un error escribiendo en el fichero");
        }
    }
    
    public static void checkBalance() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        int index;
        
        System.out.println("");
        System.out.println("Introduzca el DNI del titular de la cuenta o el numero de cuenta");
        userInput = scanner.nextLine();
        
        if((index = checkExistance(userInput)) != 0){
            System.out.println("");
            System.out.println("El saldo actual de la cuenta asciende a " + bankAccounts.get(index).getAccountBalance());
        }
        else{
            System.out.println("");
            System.out.println("No se ha encontrado la cuenta");
        }
    }
    
    public static void depositMoney() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        
        System.out.println("");
        System.out.println("Introduzca el DNI del titular de la cuenta o el numero de cuenta");
        userInput = scanner.nextLine();
        float moneyAdded;
        float actualMoney;
        int index;

        if((index = (checkExistance(userInput))) != 0){
            System.out.println("");
            System.out.println("Introduzca el saldo a ingresar");
            moneyAdded = scanner.nextFloat();
            actualMoney = bankAccounts.get(index).getAccountBalance();
            FileWriter fw = null;
            BufferedWriter bw = null;
            
            try{
                float newActualMoney = actualMoney + moneyAdded;
                bankAccounts.get(index).setAccountBalance(newActualMoney);
                
                File file = new File("accountsData.txt");
                fw = new FileWriter(file,false);
                bw = new BufferedWriter(fw);
                
                for(CuentaBancaria account : bankAccounts){
                    String data = account.getAccountNumber() + "," + account.getTitularDNI() + "," + account.getTitularName() + "," + Float.toString(account.getAccountBalance()) + "\n";
                    bw.write(data);
                    
                    bw.flush();
                    bw.close();
                }
                
                System.out.println("");
                System.out.println("Se ha añadido correctamente el saldo, Saldo actual de la cuenta: " + newActualMoney);
            }
            catch(IllegalArgumentException iae){
                System.err.println(iae.getMessage());
            }
            catch(IOException ioe){
                System.err.println("Se ha producido un error escribiendo en el fichero");
            }
        }
        else{
            System.out.println("");
            System.out.println("No se ha encontrado la cuenta");
        }
    }
    
    public static void withdrawMoney() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        
        System.out.println("");
        System.out.println("Introduzca el DNI del titular de la cuenta o el numero de cuenta");
        userInput = scanner.nextLine();
        float moneyWithdrawn;
        float actualMoney;
        int index;

        if((index = (checkExistance(userInput))) != 0){
            System.out.println("");
            System.out.println("Introduzca el saldo a retirar");
            moneyWithdrawn = scanner.nextFloat();
            actualMoney = bankAccounts.get(index).getAccountBalance();
            FileWriter fw = null;
            BufferedWriter bw = null;
            
            try{
                if(actualMoney > moneyWithdrawn){
                    float newActualMoney = actualMoney - moneyWithdrawn;
                    bankAccounts.get(index).setAccountBalance(newActualMoney);
                    File file = new File("accountsData.txt");
                    fw = new FileWriter(file,false);
                    bw = new BufferedWriter(fw);

                    for (CuentaBancaria account : bankAccounts) {
                        String data = account.getAccountNumber() + "," + account.getTitularDNI() + "," + account.getTitularName() + "," + Float.toString(account.getAccountBalance()) + "\n";
                        bw.write(data);

                        bw.flush();
                        bw.close();
                    }

                    System.out.println("");
                    System.out.println("Se ha retirado correctamente el saldo, Saldo actual de la cuenta: " + newActualMoney);
                }
                
            }
            catch(IllegalArgumentException iae){
                System.err.println(iae.getMessage());
            }
            catch(IOException ioe){
                System.err.println("Se ha producido un error escribiendo en el fichero");
            }
        }
        else{
            System.out.println("");
            System.out.println("No se ha encontrado la cuenta");
        }
    }
    
    public static int checkExistance(String userInput) {
        boolean found = false;
        int iterator = 0;
        int index = 999;
        
        while((!found) && (iterator < bankAccounts.size())){
            if((bankAccounts.get(iterator).getTitularDNI().equals(userInput)) || (bankAccounts.get(iterator).getAccountNumber().equals(userInput))){
                found = true;
                index = iterator;
            }
            iterator++;
        }
        
        if(found){
            return index;
        }
        else{
            return 0;
        }
    }
}
