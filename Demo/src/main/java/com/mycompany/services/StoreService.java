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
import com.mycompany.pojo.Ward;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class StoreService {
   AllComboboxService t = new AllComboboxService();
   public List<Store> gettbStore(String kw) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
            String sql = "select store.id, name, phone_number, address_id, more_info\n"
                   + "from store inner join address on address.id = store.address_id\n";
            List<Store> kq = new ArrayList<>();   
            if (kw != null && !kw.isEmpty())
                sql += "WHERE name like concat('%', ? , '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
           
            if(kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               String phoneNumber = rs.getString("phone_number");
               int address_id = rs.getInt("address_id");
               String full_address = rs.getString("more_info");
               full_address += ", ";
               full_address += getWard(t.getAddressById(address_id).getWardId());
               
               Store s = new Store(id, name, phoneNumber, full_address);
               kq.add(s);
            }
            return kq;
       }
    }

    public String getWard(int kw) throws SQLException {
         try (Connection conn = jdbcUtils.getConn()) {
            String sql = "select * from ward WHERE id like concat('%', ? , '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, kw);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
               int id_ward = rs.getInt("id");
               String name_ward = rs.getString("name");
               int district_id = rs.getInt("district_id");
               name_ward += ", ";
               name_ward += getDistric(district_id);
               return name_ward;
            }
        }
         return null;
    }
    
    public String getDistric(int kw) throws SQLException {
         try (Connection conn = jdbcUtils.getConn()) {
            String sql = "select * from district WHERE id like concat('%', ? , '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, kw);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
               int id_distric = rs.getInt("id");
               String name_district = rs.getString("name");
               int district_id = rs.getInt("province_id");
               name_district += ", ";
               name_district += getProvince(district_id);
               return name_district;
            }
        }
         return null;
    }
    public String getProvince(int kw) throws SQLException {
         try (Connection conn = jdbcUtils.getConn()) {
            String sql = "select * from province WHERE id like concat('%', ? , '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, kw);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
               int id_distric = rs.getInt("id");
               String name_province = rs.getString("name");
               return name_province;
            }
        }
         return null;
    }
    
    public List<District> getQ() throws SQLException{
        List<District> results = new ArrayList<>();
        try( Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from district");
            
            while (rs.next()){
                int id_district= rs.getInt("id");
                String name_district=rs.getString("name");
                int district_id = rs.getInt("province_id");
                District q= new District(id_district, name_district, district_id);
               results.add(q);
            }
                
        }
        return results;
    }
    
    public List<Ward> getP() throws SQLException{
        List<Ward> results = new ArrayList<>();
        try( Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from ward");
            
            while (rs.next()){
                Ward p = new Ward(rs.getInt("id"), rs.getString("name"), rs.getInt("district_id"));
                results.add(p);
            }
                
        }
        return results;
    }
    
    public List<Province> getTP() throws SQLException{
        List<Province> results = new ArrayList<>();
        try( Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from province");
            
            while (rs.next()){
                Province tp = new Province(rs.getInt("id"), rs.getString("name"));
                results.add(tp);
            }
                
        }
        return results;
    }
    
    public List<Address> getAddress() throws SQLException{
        List<Address> results = new ArrayList<>();
        try( Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from address");
            
            while (rs.next()){
                Address ad= new Address(rs.getInt("id"), rs.getString("more_info"), rs.getInt("ward_id"));
                results.add(ad);
            }
                
        }
        return results;
    }
    
    public int getAddressID(int kw) throws SQLException {
         try (Connection conn = jdbcUtils.getConn()) {
            String sql = "select address_id from store WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, kw);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
               int id = rs.getInt("address_id");
               return id;
            }
        }
         return 0;
    }
    
    public String getMoreInfo(int kw) throws SQLException {
         int key = getAddressID(kw);
         try (Connection conn = jdbcUtils.getConn()) {
            String sql = "select more_info from address WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
               String na = rs.getString("more_info");
               return na;
            }
        }
         return null;
    }
    public void addAddress(Address st) throws SQLException {
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                
                PreparedStatement stm = conn.prepareStatement("INSERT INTO address"
                        + "(id, more_info, ward_id) VALUES(?,?,?)");
                stm.setInt(1, st.getId());
                stm.setString(2, st.getMoreInfo());
                stm.setInt(3, st.getWardId());
                
                stm.executeUpdate();
                conn.commit();
            } catch (SQLException ex){
                Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    public void addStore(Store st) throws SQLException {
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                
                PreparedStatement stm = conn.prepareStatement("INSERT INTO store"
                        + "(name, phone_number, address_id) VALUES(?,?,?)");
                
                stm.setString(1, st.getName());
                stm.setString(2, st.getPhoneNumber());
                stm.setInt(3, st.getAddressId());
                
                stm.executeUpdate();
                conn.commit();
            } catch (SQLException ex){
                Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
      public void delCN(int id_store) throws SQLException{
        try (Connection cnn = jdbcUtils.getConn()) {
            int w = getAddressID(id_store);
            String sql = "DELETE FROM store WHERE (id = ?);";
            PreparedStatement stm = cnn.prepareStatement(sql);
            stm.setInt(1, id_store);
            stm.execute();
            
            String sql1 = "DELETE FROM address WHERE (id = ?)";
            PreparedStatement stm1 = cnn.prepareStatement(sql1);
            stm1.setInt(1, w);
            stm1.execute();
        }
    }
      public void updChiNhanh(Store st, String name) throws SQLException {
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm2 = conn.prepareStatement("UPDATE store Set name = ?, phone_number = ? where id = ?");
                stm2.setString(1, st.getName());
                stm2.setString(2, st.getPhoneNumber());
                stm2.setInt(3, st.getId());
                stm2.executeUpdate();
                
                int k = getAddressID(st.getId());
                PreparedStatement stm = conn.prepareStatement("UPDATE address Set more_info = ? where id = ?");
                stm.setString(1, name);
                stm.setInt(2, k);
                stm.executeUpdate();
                conn.commit();
            }
    }  
       
      public Store getStoreById(int id){
        Store p = new Store();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from store WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPhoneNumber(rs.getString("phone_number"));
                p.setAddressId(rs.getInt("address_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
      public boolean deleteStore(int qId) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM store WHERE id=?");
           stm.setInt(1, qId);
           
           return stm.executeUpdate() > 0;
       }
   }
     
}
