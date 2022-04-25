/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 *
 * @author Vi
 */
public class CustomerService {
    
    public List<Customer> getCustomer(){
        List<Customer> listCus = new ArrayList<>();
        //Customer e = new Customer();
        try(Connection conn = jdbcUtils.getConn()){
            String sql = "SELECT * FROM customer";
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
    }
    
    public Customer getCustomerById(int id){
        Customer c = new Customer();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM customer WHERE id LIKE ?");
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt("id"));
                c.setFirstName(rs.getString("first_name"));
                c.setLastName(rs.getString("last_name"));
                c.setBirthday(rs.getTimestamp("birthday"));
                c.setPhoneNumber(rs.getString("phone_number"));
                c.setCardId(rs.getString("card_id"));
                c.setAvailablePoint(rs.getInt("available_point"));
                c.setAddressId(rs.getInt("address_id"));
                c.setGenderId(rs.getInt("address_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
}  
