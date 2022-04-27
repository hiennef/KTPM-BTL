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
public class ImportBill {
    private int id;
    private Timestamp createdDate;
    private int storeId;
    
    public ImportBill(){
        
    }
    
    public ImportBill(Timestamp createdDate, int storeId){
        this.createdDate = createdDate;
        this.storeId = storeId;
    }
    
    public ImportBill(int id, Timestamp createdDate, int storeId){
        this.createdDate = createdDate;
        this.storeId = storeId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the storeId
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * @param storeId the storeId to set
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
    
}
