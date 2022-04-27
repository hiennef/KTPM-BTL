/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author HIEN
 */
public class TableProductData {
    private int productId;
    private String productName;
    private double quantity;
    private float productPurchaseP;
    private float productTotalP;

    public TableProductData(){
        
    }
    
    public TableProductData(Product p, double quantity){
        this.productId = p.getId();
        this.productName = p.getName();
        this.quantity = quantity;
        this.productPurchaseP = p.getPurchasePrice();
        this.productTotalP = p.getPurchasePrice()*(float)quantity;
    }
    
    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the productPurchaseP
     */
    public float getProductPurchaseP() {
        return productPurchaseP;
    }

    /**
     * @param productPurchaseP the productPurchaseP to set
     */
    public void setProductPurchaseP(float productPurchaseP) {
        this.productPurchaseP = productPurchaseP;
    }

    /**
     * @return the productTotalP
     */
    public float getProductTotalP() {
        return productTotalP;
    }

    /**
     * @param productTotalP the productTotalP to set
     */
    public void setProductTotalP(float productTotalP) {
        this.productTotalP = productTotalP;
    }
    
}
