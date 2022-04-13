/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HIEN
 */
public class EmployeeService {
    public Employee getEmployeeById(int id){
        Employee e = new Employee();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM employee WHERE id LIKE ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                e.setId(rs.getInt("id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setBirthday(rs.getDate("birthday"));
                e.setPhoneNumber(rs.getString("phone_number"));
                e.setUsername(rs.getString("user_name"));
                e.setPassword(rs.getString("password"));
                e.setWorkingHours(rs.getInt("working_hours"));
                e.setCardId(rs.getString("card_id"));
                e.setUserRoleId(rs.getInt("user_role_id"));
                e.setAddressId(rs.getInt("address_id"));
                e.setStoreId(rs.getInt("store_id"));
                e.setGenderId(rs.getInt("gender_id"));
            }
            
            System.out.println(e.getId());
            System.out.println(e.getUsername());
            
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        return e;
    }
    
    public Employee getEmployeeByUser(String username, String password){
        Employee e = new Employee();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(
                    "SELECT * FROM employee WHERE user_name LIKE ? AND password LIKE ?");
            stm.setString(1,username);
            stm.setString(2,password);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                e = getEmployeeById(rs.getInt("id"));
            }
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        return e;
    }
}
