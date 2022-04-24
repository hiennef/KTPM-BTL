/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Address;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.DataTbCustomer;
import com.mycompany.pojo.Gender;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vi
 */
public class CustomerService {
    
    public Customer getCustomerById(int id){
        Customer e = new Customer();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM customer WHERE id LIKE ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                e.setId(rs.getInt("id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setBirthday(rs.getString("birthday")); 
                e.setPhoneNumber(rs.getString("phone_number"));
                e.setCardId(rs.getString("card_id"));
                e.setAddressId(rs.getInt("address_id"));
                e.setGenderId(rs.getInt("gender_id"));
            }
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        return e;
    }
    
    public Gender getGenderById(int id){
        Gender p = new Gender();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from gender WHERE id LIKE ?");
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
        
    public List<DataTbCustomer> getCustomer(String kw) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM customer WHERE id like concat('%', ?, '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<DataTbCustomer> cus = new ArrayList<>();
           
           while (rs.next()) {
               
               Gender g = getGenderById(rs.getInt("gender_id"));
               
               int id = rs.getInt("id");
               String name = rs.getString("last_name");
               String birth = rs.getString("birthday");
               String phoneNumber = rs.getString("phone_number");
               String cardid = rs.getString("card_id");
               int availablePoint = rs.getInt("available_point");
               int addressId = rs.getInt("address_id");
               String genderName = g.getName();
               
               cus.add(new DataTbCustomer(id, name, birth, phoneNumber, cardid, availablePoint, addressId, genderName));
            }
           
           return cus;
        }
    }
    
    public boolean addCustomer(Customer q) throws SQLException {
        String q1 = "INSERT INTO customer(first_name, last_name, birthday, phone_number,"
                + " card_id, address_id, gender_id ) VALUES( ?, ?, ?, ?, ?, ?, ?)";

         try (Connection conn = jdbcUtils.getConn()) {
//             conn.setAutoCommit(false);
             PreparedStatement stm1 = conn.prepareStatement(q1);
             
             stm1.setString(1, q.getFirstName());
             stm1.setString(2, q.getLastName());
             stm1.setString(3, q.getBirthday());
             stm1.setString(4, q.getPhoneNumber());
             stm1.setString(5, q.getCardId());
             stm1.setInt(6, q.getAddressId());
             stm1.setInt(7, q.getGenderId());

             return stm1.executeUpdate()>0;
             
            }
    }
    
    public boolean addAddressCustomer(Address q) throws SQLException{
        String q1 = "INSERT INTO address(id, more_info, ward_id ) VALUES(?, ?, ?)";
        try (Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm1 = conn.prepareStatement(q1);
            
             stm1.setInt(1, q.getId());
             stm1.setString(2, q.getMoreInfo());
             stm1.setInt (3, q.getWardId());
             return stm1.executeUpdate()>0;
           
        }
    }
    
}  
