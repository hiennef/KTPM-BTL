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
public class Discount {
    
    private int id;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private float reducePercentage;
    
    public Discount(){
        
    }
    
    public Discount(int id, String name, Timestamp startTime, Timestamp endTime, float reducePercentage){
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reducePercentage = reducePercentage;
    }
    
   @Override
    public String toString(){
        return this.name;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the startTime
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Timestamp getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the reducePercentage
     */
    public float getReducePercentage() {
        return reducePercentage;
    }

    /**
     * @param reducePercentage the reducePercentage to set
     */
    public void setReducePercentage(float reducePercentage) {
        this.reducePercentage = reducePercentage;
    }

  
    
    
}
