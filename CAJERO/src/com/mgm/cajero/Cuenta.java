/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.cajero;

/**
 *
 * @author Usuario14
 */
public class Cuenta {
    private int accountNumber;
    private String titularName;
    private float money;
    
    public Cuenta() {
        
    }
    
    public Cuenta(int accountNumber,String titularName,float money) {
        this.accountNumber = accountNumber;
        this.titularName = titularName;
        this.money = money;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTitularName() {
        return titularName;
    }

    public void setTitularName(String titularName) {
        this.titularName = titularName;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
    
    public void addMoney(float money) {
        if(money <= 0){
            throw new IllegalArgumentException("No se puede depositar saldo nulo o negativo");
        }
        else{
            this.money += money;
        }
    }
    
    @Override
    public String toString() {
        return "Numero de cuenta: " + accountNumber + "\nNombre de titular: " + titularName + "\nSaldo: " + money;
    }
}
