/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Employee;
import com.mycompany.pojo.ExportDataTbEmployee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    
    //Hồng
    public List<ExportDataTbEmployee> getEmployee(String kw) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM employee WHERE id like concat('%', ?, '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<ExportDataTbEmployee> Employee = new ArrayList<>();
           
           while (rs.next()) {
               
               int id = rs.getInt("id");
               String firstName = rs.getString("first_name");
               String lastName = rs.getString("last_name");
               int storeId = rs.getInt("store_id");
               int userRoleId = rs.getInt("user_role_id");
               int workingHours =rs.getInt("working_hours");
               Employee.add(new ExportDataTbEmployee(id,firstName, lastName,
                       workingHours,userRoleId, storeId));
           }
           
           return Employee;
       }
   }
    public boolean deleteEmployee(int qId) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM employee WHERE id=?");
           stm.setInt(1, qId);
           
           return stm.executeUpdate() > 0;
       }
   }
    public boolean addEmployee(Employee q) throws SQLException {
        String q1 = "INSERT INTO employee(id,first_name, last_name, birthday, phone_number,user_name, password, "
                + " card_id, user_role_id, address_id, store_id, gender_id ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

         try (Connection conn = jdbcUtils.getConn()) {
             conn.setAutoCommit(false);
             PreparedStatement stm1 = conn.prepareStatement(q1);
             stm1.setInt(1, q.getId());
             stm1.setString(2, q.getFirstName());
             stm1.setString(3, q.getLastName());
             stm1.setDate(4, (java.sql.Date) q.getBirthday());
             stm1.setString(5, q.getPhoneNumber());
             stm1.setInt(6, q.getWorkingHours());
             stm1.setString(7, q.getCardId());
             stm1.setInt(8, q.getUserRoleId());
             stm1.setInt(10, q.getAddressId());
             stm1.setInt(11, q.getStoreId());
             stm1.setInt(12, q.getGenderId());

             conn.commit();
            }
        return false;
    }
    
    
    
}
