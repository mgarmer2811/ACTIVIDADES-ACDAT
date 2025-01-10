/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mgm;

/**
 *
 * @author Usuario14
 */
public class Main {

    public static void main(String[] args) {
        int option = -1;
        
        do{
            option = Controller.menu();
            switch(option){
                case 1:{
                    break;
                }
                
                default:{
                    System.out.println("Has salido del programa");
                }
            }
        }
        while(option != 11);
    }
}
