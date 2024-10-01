/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mgm.shop;

/**
 *
 * @author Usuario
 */
public class Product {
    private int idProduct;
    private String nameProduct;
    private float priceProduct;
    private int stockProduct;
    
    public Product() {
        
    }
    
    public Product(int idProduct,String nameProduct,float priceProduct,int stockProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.stockProduct = stockProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public float getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(float priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getStockProduct() {
        return stockProduct;
    }

    public void setStockProduct(int stockProduct) {
        this.stockProduct = stockProduct;
    }
    
    @Override
    public String toString() {
        return "id: " + idProduct + "\nnombre: " + nameProduct + "\nprecio: " + priceProduct + "\nstock: " + stockProduct + "\n";
    }
}
