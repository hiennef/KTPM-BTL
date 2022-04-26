/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author HIEN
 */
public class Store {
    private int idStore;
    private String nameStore;
    private String phoneNumberStore;
    private int addressIdStore;
  
    public String getFull_address() {
        return full_address;
    }

    /**
     * @param full_address the full_address to set
     */
    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }
    private int id;
    private String name;
    private String phoneNumber;
    private int addressId;
    private String full_address;
    
    public Store(){
        
    }
    
    public Store(int id,String name, String phoneNumber){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public Store(int id, String name, String phoneNumber, int addressId){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressId = addressId;
    }
    
    public Store(String name, String phoneNumber, int addressId){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressId = addressId;
    }
    
    public Store(int id, String name, String phoneNumber, String full_address){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.full_address = full_address;
    }
<<<<<<< HEAD
=======
    
    @Override
    public String toString(){
        return this.name;
    }
>>>>>>> 49aebcba8b2782887d83a0055ee8319c027aaa60
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return this.name;
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
     * @return the idStore
     */
    public int getIdStore() {
        return idStore;
    }

    /**
     * @param idStore the idStore to set
     */
    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    /**
     * @return the nameStore
     */
    public String getNameStore() {
        return nameStore;
    }

    /**
     * @param nameStore the nameStore to set
     */
    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    /**
     * @return the phoneNumberStore
     */
    public String getPhoneNumberStore() {
        return phoneNumberStore;
    }

    /**
     * @param phoneNumberStore the phoneNumberStore to set
     */
    public void setPhoneNumberStore(String phoneNumberStore) {
        this.phoneNumberStore = phoneNumberStore;
    }

    /**
     * @return the addressIdStore
     */
    public int getAddressIdStore() {
        return addressIdStore;
    }

    /**
     * @param addressIdStore the addressIdStore to set
     */
    public void setAddressIdStore(int addressIdStore) {
        this.addressIdStore = addressIdStore;
    }
    
    
}
