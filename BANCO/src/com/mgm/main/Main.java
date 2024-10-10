/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mgm.main;

import com.mgm.banco.*;
import java.util.Scanner;
/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Banco bank = new Banco();
       Banco.loadData();
       int actionCode = 0;
       do{
           actionCode = menu();
           doFunctions(actionCode);
           
       }while(actionCode != 8);
    }
    
    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        
        System.out.println("");
        System.out.println("1. Añadir cuenta bancaria");
        System.out.println("2. Consultar saldo por DNI");
        System.out.println("3. Consultar saldo por numero de cuenta");
        System.out.println("4. Depositar dinero por DNI");
        System.out.println("5. Depositar dinero por numero de cuenta");
        System.out.println("6. Retirar dinero por DNI");
        System.out.println("7. Retirar dinero por numero de cuenta");
        System.out.println("8. Salir");
        System.out.print("Escoja una opcion: ");
        option = scanner.nextInt();
        
        return option;
    }
    
    public static void doFunctions(int code) {
        switch(code){
            case 1:
            {
                Banco.addAccount();
                break;
            }
            case 2:
            {
                Banco.checkBalance();
                break;
            }
            case 3:
            {
                Banco.checkBalance();
                break;
            }
            case 4:
            {
                Banco.depositMoney();
                break;
            }
            case 5:
            {
                Banco.depositMoney();
                break;
            }
            case 6:
            {
                try{
                    Banco.withdrawMoney();
                }
                catch(SaldoInsuficienteException sie){}
                break;
            }
            case 7:
            {
                try{
                    Banco.withdrawMoney();
                }
                catch(SaldoInsuficienteException sie){}
            }
            case 8:
            {
                System.out.println("");
                System.out.println("Has salido del programa");
                break;
            }
            default:
            {
                System.out.println("No has escogido bien, por favor escoge bien la proxima");
            }
        }
    }
}
