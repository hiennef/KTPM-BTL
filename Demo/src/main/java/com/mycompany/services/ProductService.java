/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Discount;
import com.mycompany.pojo.Producer;
import com.mycompany.pojo.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;

/**
 *
 * @author HIEN
 */
public class ProductService {
    public Producer getProducerById(int id){
        Producer p = new Producer();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from producer WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAddressId(rs.getInt("address_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    public Product getProductById(int id){
        Product p = new Product();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM product WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPurchasePrice(rs.getFloat("purchase_price"));
                p.setSalePrice(rs.getFloat("sale_price"));
                p.setProducerId(rs.getInt("producer_id"));
                p.setSubtypeId(rs.getInt("type_id"));
                p.setUnitId(rs.getInt("unit_id"));
                p.setDiscountId(rs.getInt("discount_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    public float getProductPrice(Product p){
        float price = p.getSalePrice();
        if (isDiscount(p)){
            price = p.getSalePrice() - (getDiscountById(p.getDiscountId()).getReducePercentage()*p.getSalePrice());
        }
        return price;
    }
    
    public boolean isDiscount(Product p){
        try{
            Discount d = getDiscountById(p.getDiscountId());
            if(d.getStartTime().before(Timestamp.from(Instant.now()))&&
                    d.getEndTime().after(Timestamp.from(Instant.now()))){
                return true;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }
    
    public Discount getDiscountById(int id){
        Discount d = new Discount();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM discount WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setStartTime(rs.getTimestamp("start_time"));
                d.setEndTime(rs.getTimestamp("end_time"));
                d.setReducePercentage(rs.getFloat("reduce_percentage"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return d;
    }
}
