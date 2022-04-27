/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author HIEN
 */
public class ImportBillDetail {
    private int importBillId;
    private int productId;
    private double quantity;

    public ImportBillDetail(){
        
    }
    
    public ImportBillDetail(int importBillId, int productId, double quantity){
        this.importBillId = importBillId;
        this.productId = productId;
        this.quantity = quantity;
    }
    
    /**
     * @return the importBillId
     */
    public int getImportBillId() {
        return importBillId;
    }

    /**
     * @param importBillId the importBillId to set
     */
    public void setImportBillId(int importBillId) {
        this.importBillId = importBillId;
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
    
}
