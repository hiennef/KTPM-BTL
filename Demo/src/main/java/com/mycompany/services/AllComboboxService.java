/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.District;
import com.mycompany.pojo.Province;
import com.mycompany.pojo.Store;
import com.mycompany.pojo.UserRole;
import com.mycompany.pojo.Ward;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hong
 */
public class AllComboboxService {
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
               
               cates.add(new Store(id, name, phoneNumber, addressId));
            }
           
            return cates;
        }
    }
     public List<Province> getProvince() throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM province");
            
            List<Province> cates = new ArrayList<>();
            
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               cates.add(new Province(id, name));
            }
           
            return cates;
        }
    }
    public List<District> getDistrict() throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM district");
            
            List<District> cates = new ArrayList<>();
            
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               int provinceId = rs.getInt("province_id");
               cates.add(new District(id, name, provinceId));
            }
           
            return cates;
        }
    }
    public List<Ward> getWard() throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ward");
            
            List<Ward> cates = new ArrayList<>();
            
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               int districtId = rs.getInt("district_id");
               cates.add(new Ward(id, name, districtId));
            }
           
            return cates;
        }
    }
    public List<UserRole> getUserRole() throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM user_role");
            
            List<UserRole> cates = new ArrayList<>();
            
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               cates.add(new UserRole(id, name));
            }
            return cates;
        }
    }
    
}
