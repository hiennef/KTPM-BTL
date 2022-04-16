/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author HIEN
 */
public class ReceiptDetail {
    private int receiptId;
    private int productId;
    private double quantity;
    private float droppedPrice;

    public ReceiptDetail(){
        
    }
    
    public ReceiptDetail(int receiptId, int productId, double quantity, float dropppedPrice){
        this.receiptId = receiptId;
        this.productId = productId;
        this.quantity = quantity;
        this.droppedPrice = dropppedPrice;
    }
    
    public ReceiptDetail(int receiptId, int productId){
        this.receiptId = receiptId;
        this.productId = productId;
    }
    
    /**
     * @return the receiptId
     */
    public int getReceiptId() {
        return receiptId;
    }

    /**
     * @param receiptId the receiptId to set
     */
    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
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
     * @return the droppedPrice
     */
    public float getDroppedPrice() {
        return droppedPrice;
    }

    /**
     * @param droppedPrice the droppedPrice to set
     */
    public void setDroppedPrice(float droppedPrice) {
        this.droppedPrice = droppedPrice;
    }
    
}
