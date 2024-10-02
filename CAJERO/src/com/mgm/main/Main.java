/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import com.mgm.cajero.CuentaBancaria;
import com.mgm.cajero.SaldoInsuficienteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario14
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int option = 0;
        CuentaBancaria cuenta = new CuentaBancaria();
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("");
            System.out.println("");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("Escoja una opcion: ");
            option = scanner.nextInt();
            
            switch(option){
                case 1:
                    CuentaBancaria.checkMoney();
                    break;
                case 2:
                    CuentaBancaria.depositMoney();
                    break;
                case 3:
                    try {
                        CuentaBancaria.withdrawMoney();
                    } catch (SaldoInsuficienteException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Has salido del programa");
                    break;
            }
        }
        while(option != 4);
    }
    
}
