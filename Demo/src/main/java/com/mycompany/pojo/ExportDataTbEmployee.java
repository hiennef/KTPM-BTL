/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author Administrator
 */
public class ExportDataTbEmployee {
    private int id;
    private String firstName;
    private String lastName;
    private int storeId;
    private int userRoleId;
    private int workingHours;
    
    public ExportDataTbEmployee(){
        
    }
    
    public ExportDataTbEmployee(int id, String firstName, String lastName,int storeId, int userRoleId, int workingHours){
        this.id = id;
        this.firstName = firstName;
        this.lastName =lastName;
        this.storeId = storeId;
        this.userRoleId = userRoleId;
        this.workingHours = workingHours;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    /**
     * @return the userRoleId
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId the userRoleId to set
     */
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * @return the workingHours
     */
    public int getWorkingHours() {
        return workingHours;
    }

    /**
     * @param workingHours the workingHours to set
     */
    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }
}

