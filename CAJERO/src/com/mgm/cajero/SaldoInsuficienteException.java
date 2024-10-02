/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.cajero;

/**
 *
 * @author Usuario14
 */
public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException() {
        super("No puedes retirar mas dinero del que tienes mamon");
    }
}
