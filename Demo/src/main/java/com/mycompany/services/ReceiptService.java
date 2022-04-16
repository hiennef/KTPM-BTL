/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Receipt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HIEN
 */
public class ReceiptService {
    public List<Receipt> getReceipts(){
        List<Receipt> receipts = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM receipt");
            
           while(rs.next()){
               Receipt r = new Receipt(rs.getInt("id"), 
                       rs.getTimestamp("created_date"), 
                       rs.getFloat("total_price"), rs.getInt("reward_point"),
                       rs.getInt("customer_id"), rs.getInt("employee_id"));
               receipts.add(r);
           }
        }
        catch (Exception ex ){
            System.out.println(ex.getMessage());
        }
        return receipts;
    }
}