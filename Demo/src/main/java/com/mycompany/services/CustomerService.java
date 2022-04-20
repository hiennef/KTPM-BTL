/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Employee;
import com.mycompany.pojo.Gender;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 *
 * @author Vi
 */
public class CustomerService {
    
    /*public List<Customer> getCustomer(){
        List<Customer> listCus = new ArrayList<>();
        //Customer e = new Customer();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            
            ResultSet rs = stm.executeQuery("SELECT * FROM customer");
            
            while(rs.next()){
                Customer e = new Customer(rs.getInt("id"), rs.getString("first_name"),
                        rs.getTimestamp("birthday"), rs.getString("phone_number"),
                                rs.getString("card_id"), rs.getInt("address_id"), rs.getInt("gender_id"));
                
                listCus.add(e);
            }
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        return listCus;
    }*/
    
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
        
    public List<Customer> getCustomer(String kw) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM customer WHERE id like concat('%', ?, '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<Customer> cus = new ArrayList<>();
           
           while (rs.next()) {
               
               Gender g = getGenderById(rs.getInt("gender_id"));
               
               int id = rs.getInt("id");
               String name = rs.getString("last_name");
               Timestamp birth = rs.getTimestamp("birthday");
               String phoneNumber = rs.getString("phone_number");
               String cardid = rs.getString("card_id");
               int availablePoint = rs.getInt("available_point");
               int addressId = rs.getInt("address_id");
               String genderId = g.getName();
               
               cus.add(new Customer(id, name, birth, phoneNumber, cardid, availablePoint, addressId, genderId));
            }
           
           return cus;
        }
    }
    
    /*public boolean addCustomer(Customer q) throws SQLException {
        String q1 = "INSERT INTO customer(id, first_name, last_name, birthday, phone_number,"
                + " card_id, address_id, gender_id ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

         try (Connection conn = jdbcUtils.getConn()) {
//             conn.setAutoCommit(false);
             PreparedStatement stm1 = conn.prepareStatement(q1);
             stm1.setInt(1, q.getId());
             stm1.setString(2, q.getFirstName());
             stm1.setString(3, q.getLastName());
             //stm1.setString(4, q.getBirthday());
             stm1.setString(5, q.getPhoneNumber());
             stm1.setString(9, q.getCardId());
             stm1.setInt(13, q.getGenderId());

             return stm1.executeUpdate()>0;
             
            }
    }*/
}  
