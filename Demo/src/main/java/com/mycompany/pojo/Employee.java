/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author HIEN
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String phoneNumber;
    private String username;
    private String password;
    private int workingHours;
    private String cardId;
    private int userRoleId;
    private int addressId;
    private int storeId;
    private int genderId;
    
    public Employee(){
        
    }
    
    public Employee(int id, String firstName, String lastName, String birthday, 
            String phoneNumber, String username, String password, 
            int workingHours, String cardId, int userRoleId, int addressId
            , int storeId, int genderId){
        this.id = id;
        this.firstName = firstName;
        this.lastName =lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.workingHours = workingHours;
        this.cardId = cardId;
        this.userRoleId = userRoleId;
        this.addressId = addressId;
        this.storeId = storeId;
        this.genderId = genderId;
    }
    
    public Employee(String firstName, String lastName, String birthday, 
            String phoneNumber, String username, String password, String cardId,
            int userRoleId, int storeId, int genderId){
        this.firstName = firstName;
        this.lastName =lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.cardId = cardId;
        this.userRoleId = userRoleId;
        this.storeId = storeId;
        this.genderId = genderId;
    }
    public Employee( String firstName, String lastName, String birthday, 
            String phoneNumber, String username, String password, 
            int workingHours, String cardId, int userRoleId, int addressId
            , int storeId, int genderId){
        
        this.firstName = firstName;
        this.lastName =lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.workingHours = workingHours;
        this.cardId = cardId;
        this.userRoleId = userRoleId;
        this.addressId = addressId;
        this.storeId = storeId;
        this.genderId = genderId;
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
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * @return the cardId
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * @param cardId the cardId to set
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
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
     * @return the addressId
     */
    public int getAddressId() {
        return addressId;
    }

    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
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
     * @return the genderId
     */
    public int getGenderId() {
        return genderId;
    }

    /**
     * @param genderId the genderId to set
     */
    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }
    
}
