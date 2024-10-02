/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.cajero;

import java.util.Scanner;

/**
 *
 * @author Usuario14
 */
public class CuentaBancaria {
    private static Cuenta account = new Cuenta();
    
    public Cuenta getAccount() {
        return this.account;
    }
    
    public static void checkMoney() {
        System.out.println("");
        System.out.println("");
        System.out.println("El saldo de la cuentas asciende a: " + account.getMoney());
    }
    
    public static void depositMoney() {
        Scanner scanner = new Scanner(System.in);
        float moreMoney;
        System.out.println("");
        System.out.println("");
        System.out.println("Introduce el salario que quieres depositar");
        moreMoney = scanner.nextFloat();
        
        try{
            account.addMoney(moreMoney);
        }
        catch(IllegalArgumentException ex){}
    }
    
    public static void withdrawMoney() throws SaldoInsuficienteException {
        Scanner scanner = new Scanner(System.in);
        float moneyToWithdraw;
        System.out.println("");
        System.out.println("");
        System.out.println("Introduce el dinero que quieres retirar");
        moneyToWithdraw = scanner.nextFloat();
        
        if(moneyToWithdraw > account.getMoney()){
            throw new SaldoInsuficienteException();
        }
        else{
            float originalMoney = account.getMoney();
            float actualMoney = originalMoney - moneyToWithdraw;
            account.setMoney(actualMoney);
        }
    }
}
