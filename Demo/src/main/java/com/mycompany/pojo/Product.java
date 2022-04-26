/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author HIEN
 */
public class Product {
    private int id;
    private String name;
    private float purchasePrice;
    private float salePrice;
    private int producerId;
    private int subtypeId;
    private int unitId;
    private int discountId;
    private String producerName;
    private String typeName;
    private String unitName;

    public Product(){
        
    }
    
    public Product(int id, String name, float purchasePrice, float salePrice, 
            int producerId, int subtypeId, int unitId, int discountId, String producerName, String typeName,String unitName ){
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.producerId = producerId;
        this.subtypeId = subtypeId;
        this.unitId = unitId;
        this.discountId = discountId;
        this.producerName = producerName;
        this.typeName = typeName;
        this.unitName = unitName;
    }
    public Product(int id, String name, float purchasePrice, float salePrice, 
            int producerId, int subtypeId, int unitId, int discountId ){
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.producerId = producerId;
        this.subtypeId = subtypeId;
        this.unitId = unitId;
        this.discountId = discountId;
        
    }
    public Product(String name, float purchasePrice, float salePrice,
            int subtypeId, int unitId){
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.subtypeId = subtypeId;
        this.unitId = unitId;
    }
    public Product(String name, float purchasePrice, float salePrice, int producerId, 
            int subtypeId, int unitId){
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.producerId = producerId;
        this.subtypeId = subtypeId;
        this.unitId = unitId;
        
        
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
     * @return the purchasePrice
     */
    public float getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * @param purchasePrice the purchasePrice to set
     */
    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * @return the salePrice
     */
    public float getSalePrice() {
        return salePrice;
    }

    /**
     * @param salePrice the salePrice to set
     */
    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * @return the producerId
     */
    public int getProducerId() {
        return producerId;
    }

    /**
     * @param producerId the producerId to set
     */
    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    /**
     * @return the subtypeId
     */
    public int getSubtypeId() {
        return subtypeId;
    }

    /**
     * @param subtypeId the subtypeId to set
     */
    public void setSubtypeId(int subtypeId) {
        this.subtypeId = subtypeId;
    }

    /**
     * @return the unitId
     */
    public int getUnitId() {
        return unitId;
    }

    /**
     * @param unitId the unitId to set
     */
    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    /**
     * @return the discountId
     */
    public int getDiscountId() {
        return discountId;
    }

    /**
     * @param discountId the discountId to set
     */
    public void setDiscountId(int discountId) {
        this.discountId = discountId;
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
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return the unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName the unitName to set
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    
}
