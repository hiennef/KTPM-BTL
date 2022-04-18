/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.sql.Timestamp;

/**
 *
 * @author HIEN
 */
public class TableReceiptDetailData {
    private String soThuTu ;
    private String receiptId;
    private Timestamp createdDate;
    private String productName;
    private double quantity;
    private String producerName;
    private float productPrice;
    private String customerName;
    private String employeeName;
    
    public TableReceiptDetailData(){
        
    }
    
    public TableReceiptDetailData(String soThuTu, String receiptId, 
            Timestamp createdDate, String productName, double quantity, 
            String producerName, float productPrice, String customerName, 
            String employeeName){
        this.soThuTu = soThuTu;
        this.receiptId = receiptId;
        this.createdDate = createdDate;
        this.productName = productName;
        this.quantity = quantity;
        this.producerName = producerName;
        this.productPrice = productPrice;
        this.customerName = customerName;
        this.employeeName = employeeName;
    }
    
    public TableReceiptDetailData(Timestamp createdDate, String productName, 
            double quantity, String producerName, float productPrice,
            String customerName, String employeeName){
        this.createdDate = createdDate;
        this.productName = productName;
        this.quantity = quantity;
        this.producerName = producerName;
        this.productPrice = productPrice;
        this.customerName = customerName;
        this.employeeName = employeeName;
    }

    /**
     * @return the soThuTu
     */
    public String getSoThuTu() {
        return soThuTu;
    }

    /**
     * @param soThuTu the soThuTu to set
     */
    public void setSoThuTu(String soThuTu) {
        this.soThuTu = soThuTu;
    }

    /**
     * @return the receiptId
     */
    public String getReceiptId() {
        return receiptId;
    }

    /**
     * @param receiptId the receiptId to set
     */
    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    /**
     * @return the createdDate
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the producerName
     */
    public String getProducerName() {
        return producerName;
    }

    /**
     * @param producerName the producerName to set
     */
    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    /**
     * @return the productPrice
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
    
}
