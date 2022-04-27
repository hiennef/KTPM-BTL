/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.CustomerDiscount;
import com.mycompany.pojo.Discount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 *
 * @author HIEN
 */
public class CustomerDiscountService {
    public boolean isBirthday(Customer customer){
        boolean kq = false;
        try{
            Timestamp today = Timestamp.valueOf(LocalDateTime.now());
            Timestamp cusBirthday = Timestamp.valueOf(customer.getBirthday()+" 00:00:00.0");
            if(today.getDate()==cusBirthday.getDate()&&today.getMonth()==cusBirthday.getMonth())
                kq = true;
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        return kq;
    }
    
    public void addCustomerDiscount(int customerId, int discountId){
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement("INSERT INTO customer_discount(customer_id, discount_id) VALUES(?, ?)");
            stm.setInt(1, customerId);
            stm.setInt(2, discountId);
            
            stm.executeUpdate();
            
            conn.commit();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Discount getDiscountByName(String name){
        Discount d = new Discount();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM discount WHERE name LIKE ?");
            stm.setString(1, name);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
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
        if(d.getStartTime().before(Timestamp.from(Instant.now()))&&
                    d.getEndTime().after(Timestamp.from(Instant.now())))
            return d;
        else
            return new Discount();
    }
}
