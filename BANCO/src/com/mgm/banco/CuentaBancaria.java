/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.banco;

/**
 *
 * @author Usuario
 */
public class CuentaBancaria {
    private String accountNumber;
    private String titularName;
    private String titularDNI;
    private float accountBalance;
    private static int lastAccountNumber = 0;
    
    public CuentaBancaria() {
        this.accountNumber = generateAccountNumber();
    }
    
    public CuentaBancaria(String accountNumber,String titularName,String titularDNI,float accountBalance) {
        this.accountNumber = accountNumber;
        this.titularName = titularName;
        this.titularDNI = titularDNI;
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTitularName() {
        return titularName;
    }

    public void setTitularName(String titularName) {
        this.titularName = titularName;
    }

    public String getTitularDNI() {
        return titularDNI;
    }

    public void setTitularDNI(String titularDNI) {
        this.titularDNI = titularDNI;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        if(accountBalance <= 0){
            throw new IllegalArgumentException("No se permiten depositos nulos o  negativos");
        }
        else{
            this.accountBalance = accountBalance;
        }
    }
    
    public String generateAccountNumber() {
        lastAccountNumber = (lastAccountNumber + 1);
        return String.format("%04d",lastAccountNumber);
    }
    
    @Override
    public String toString() {
        return "Numero de cuenta: " + accountNumber + "\nNombre del titular: " + titularName + "\nDNI del titular: " + titularDNI + "\nSaldo: " + accountBalance + "\n";
    } 
}
