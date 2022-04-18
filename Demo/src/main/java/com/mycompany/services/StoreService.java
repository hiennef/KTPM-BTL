/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Store;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class StoreService {
    public List<Store> gettbStore(String kw) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM store WHERE id like concat('%', ?, '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<Store> Store = new ArrayList<>();
           
           while (rs.next()) {
               
               int id = rs.getInt("id");
               String name = rs.getString("name");
             
               String phoneNumber = rs.getString("phone_number");
               int addressId = rs.getInt("address_id");
               
               Store.add(new Store(id,name, phoneNumber,addressId));
           }
           
           return Store;
       }
   }
    public List<Store> getStore() throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM store");
            
            List<Store> cates = new ArrayList<>();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                int addressId = rs.getInt("address_id");
                cates.add(new Store(id,name, phoneNumber,addressId));
            }
           
            return cates;
        }
    }
}
