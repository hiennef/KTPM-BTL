
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author HIEN
 */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String phoneNumber;
    private String cardId;
    private int availablePoint;
    private int addressId;
    private int genderId;
    
    public Customer(){
        
    }
    
    
    public Customer(int id, int availablePoint){
        this.id = id;
        this.availablePoint = availablePoint;
    }
    
    public Customer(int id, String lastName, String birthday, 
            String phoneNumber, String cardId,int availablePoint,  int addressId, int genderId){
        this.id = id;
        this.lastName =lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.cardId = cardId;
        this.availablePoint = availablePoint;
        this.addressId = addressId;
        this.genderId = genderId;
    }
    
    
    public Customer(int id, String lastName, String birthday, 
            String phoneNumber, String cardId,int availablePoint, int genderId){
        this.id = id;
        this.lastName =lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.cardId = cardId;
        this.availablePoint = availablePoint;
        this.genderId = genderId;
    }
    
    public Customer(String firstName, String lastName, String birthday, 
            String phoneNumber, String cardId, int genderId){
        this.firstName = firstName;
        this.lastName =lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.cardId = cardId;
        this.genderId = genderId;
    }
    
    public Customer(String firstName, String lastName, String birthday, 
            String phoneNumber, String cardId, int addressId, int genderId){
        this.firstName = firstName;
        this.lastName =lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.cardId = cardId;
        this.addressId = addressId;
        this.genderId = genderId;
    } 
    
    public Customer(int id, String firstName, String lastName, 
            String birthday, String phoneNumber, String cardId, 
            int availablePoint, int addressId, int genderId){
        this.id = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.birthday  = birthday;
        this.phoneNumber = phoneNumber;
        this.cardId = cardId;
        this.availablePoint = availablePoint;
        this.addressId = addressId;
        this.genderId = genderId;
    }
    
    public Customer(int id, String lastName){
        this.id = id;
        this.lastName  = lastName;
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
     * @return the availablePoint
     */
    public int getAvailablePoint() {
        return availablePoint;
    }

    /**
     * @param availablePoint the availablePoint to set
     */
    public void setAvailablePoint(int availablePoint) {
        this.availablePoint = availablePoint;
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
