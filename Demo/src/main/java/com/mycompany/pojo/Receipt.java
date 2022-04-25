/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.TimeZone;

/**
 *
 * @author HIEN
 */
public class Receipt {
    private int id;
    private Timestamp createdDate;
    private float totalPrice;
    private int rewardPoint;
    private int customerId;
    private int employeeId;

    public Receipt(){
        
    }
    
    public Receipt(int id, Timestamp createdDate, float totalPrice, 
            int rewardPoint, int customerId, int employeeId){
        this.id = id;
        this.createdDate = createdDate;
        this.totalPrice = totalPrice;
        this.rewardPoint = rewardPoint;
        this.customerId = customerId;
        this.employeeId = employeeId;
    }
    
    
    public Receipt(int id, Timestamp createdDate){
        this.id = id;
        this.createdDate = createdDate;
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
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the rewardPoint
     */
    public int getRewardPoint() {
        return rewardPoint;
    }

    /**
     * @param rewardPoint the rewardPoint to set
     */
    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
