/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Address;
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
    //update tối 24.4
   public List<District> getDistrictByProvinceId(int pid) throws SQLException {
        
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from district WHERE province_id LIKE ?");
            pstm.setInt(1, pid);
            ResultSet rs = pstm.executeQuery();
            List<District> cates = new ArrayList<>();
            while(rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("name");
               int provinceId = rs.getInt("province_id");
               cates.add(new District(id, name, provinceId));
               
            }
        return cates;
        
        }
        
    }
    public List<Ward> getWardByDistrictId(int pid) throws SQLException {
        
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from ward WHERE district_id LIKE ?");
            pstm.setInt(1, pid);
            ResultSet rs = pstm.executeQuery();
            List<Ward> cates = new ArrayList<>();
            while(rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("name");
               int districtId = rs.getInt("district_id");
               cates.add(new Ward(id, name, districtId));
               
            }
        return cates;
        
        }
        
    }
//    public List<Ward> getWard() throws SQLException {
//        try (Connection conn = jdbcUtils.getConn()) {
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery("SELECT * FROM ward");
//            
//            List<Ward> cates = new ArrayList<>();
//            
//            while (rs.next()) {
//               int id = rs.getInt("id");
//               String name = rs.getString("name");
//               int districtId = rs.getInt("district_id");
//               cates.add(new Ward(id, name, districtId));
//               return cates;
//            }
//        return cates;
//        }
//    }
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
    //hong update 21/4
    public UserRole getUserRoleById(int id){
        UserRole p = new UserRole();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from user_role WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    /*update ngày 22/4*/
    public Province getProvinceById(int id){
        Province p = new Province();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from province WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                return p;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    } 
    public District getDistrictById(int id){
        District p = new District();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from district WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setProvinceId(rs.getInt("province_id"));
                return p;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
      public Ward getWardById(int id){
        Ward p = new Ward();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from ward WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDistrictId(rs.getInt("district_id"));
                return p;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
      public Address getAddressById(int id){
        Address p = new Address();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from address WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setMoreInfo(rs.getString("more_info"));
                p.setWardId(rs.getInt("ward_id"));
                return p;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    public District getDistrictByPrId(int id){
        District p = new District();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from district WHERE province_id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setProvinceId(rs.getInt("province_id"));
                return p;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
}
