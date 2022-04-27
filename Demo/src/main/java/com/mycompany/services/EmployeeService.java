/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Address;
import com.mycompany.pojo.Employee;
import com.mycompany.pojo.ExportDataTbEmployee;
import com.mycompany.pojo.Gender;
import com.mycompany.pojo.Store;
import com.mycompany.pojo.UserRole;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                e.setBirthday(rs.getString("birthday"));
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
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    public List<ExportDataTbEmployee> getEmployee(String kw) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM employee WHERE last_name like concat('%', ?, '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           StoreService st = new StoreService();
           
           List<ExportDataTbEmployee> Employee = new ArrayList<>();
          
           while (rs.next()) {
               Store p = getStoreById(rs.getInt("store_id"));
               UserRole u = getUserRoleById(rs.getInt("user_role_id"));
              
               int id = rs.getInt("id");
               String firstName = rs.getString("first_name");
               String lastName = rs.getString("last_name");
               String birthday = rs.getString("birthday");
               String phoneNumber = rs.getString("phone_number");
               String username = rs.getString("user_name");
               String Password = rs.getString("password");
               int workingHours =rs.getInt("working_hours");
               String CardId = rs.getString("card_id");
               String userRole = u.getName();
               int AddressId = rs.getInt("address_id") ;
               String storeName = p.getName();
               int GenderId = rs.getInt("gender_id");
               int userRoleId = rs.getInt("user_role_id");
               int StoreId = rs.getInt("store_id");
              
              
               
               Employee.add(new ExportDataTbEmployee( id,  firstName, lastName, birthday, 
            phoneNumber,username, Password, workingHours, CardId,userRole, AddressId, storeName,GenderId, userRoleId, StoreId));
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
    public boolean deleteAddressEmployee(int qId) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm1 = conn.prepareStatement("DELETE FROM address WHERE id=?");
           stm1.setInt(1, qId);
           return stm1.executeUpdate() > 0;
       }
   }
    public boolean addAddressEmployee(Address q) throws SQLException{
        String q1 = "INSERT INTO address(id, more_info, ward_id ) VALUES(?, ?, ?)";
        try (Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm1 = conn.prepareStatement(q1);
     
            stm1.setInt(1, q.getId());
            stm1.setString(2, q.getMoreInfo());
            stm1.setInt (3, q.getWardId());

            return stm1.executeUpdate()>0;
           
        }
    }
    public boolean updateAddress (Address q) throws SQLException{
        String q1 = "UPDATE address Set more_info = ?, ward_id =? WHERE id = ?";
        try (Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm1 = conn.prepareStatement(q1);
            
            stm1.setString(1, q.getMoreInfo());
            stm1.setInt(2, q.getWardId());
            stm1.setInt (3, q.getId());
            
             return stm1.executeUpdate()>0;
           
        }
    }
    public static String SplitStringToDate (String q){
        String date[] = q.split("/");
        String year = date[2];
        String day = date[1];
        String month =date[0];
        if (date[0].length()>=2){
            month = date[0];
        }else{
            month = "0"+ date[0];
        }
        if (date[1].length()>=2){
            day = date[1];
        }else{
            day = "0"+ date[1];
        }
        return year + "-" + month +"-" + day;
    }
   public boolean addEmployee(Employee q) throws SQLException {
        String q1 = "INSERT INTO employee(first_name, last_name, birthday, phone_number,user_name, password, working_hours, "
                + " card_id, user_role_id, address_id, store_id, gender_id ) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

         try (Connection conn = jdbcUtils.getConn()) {
//             conn.setAutoCommit(false);
             PreparedStatement stm1 = conn.prepareStatement(q1);
          
             stm1.setString(1, q.getFirstName());
             stm1.setString(2, q.getLastName());
             stm1.setString(3, SplitStringToDate(q.getBirthday()));
             stm1.setString(4, q.getPhoneNumber());
             stm1.setString(5, q.getUsername());
             stm1.setString(6,q.getPassword());
             stm1.setInt(7, q.getWorkingHours());
             stm1.setString(8, q.getCardId());
             stm1.setInt(9, q.getUserRoleId());
             stm1.setInt(10, q.getAddressId());
             stm1.setInt(11, q.getStoreId());
             stm1.setInt(12, q.getGenderId());

             return stm1.executeUpdate()>0;
             
            }
    }
    public boolean updateEmployee(ExportDataTbEmployee q) throws SQLException {
    String q1 = "UPDATE employee Set first_name =?, last_name = ?, birthday= ?, phone_number =?,user_name =?, password=?,"
    + " working_hours =?, card_id=?, user_role_id=?, address_id=?, store_id=?, gender_id=? WHERE id = ?";

     try (Connection conn = jdbcUtils.getConn()) {
//       conn.setAutoCommit(false);
         PreparedStatement stm1 = conn.prepareStatement(q1);
         
         stm1.setString(1, q.getFirstName());
         stm1.setString(2, q.getLastName());
         stm1.setString(3,String.valueOf(q.getBirthday()) );
         stm1.setString(4, q.getPhoneNumber());
         stm1.setString(5, q.getUsername());
         stm1.setString(6,q.getPassword());
         stm1.setInt(7, q.getWorkingHours());
         stm1.setString(8, q.getCardId());
         stm1.setInt(9, q.getUserRoleId());
         stm1.setInt(10, q.getAddressId());
         stm1.setInt(11, q.getStoreId());
         stm1.setInt(12, q.getGenderId());
         stm1.setInt(13, q.getId());

         return stm1.executeUpdate()>0;

        }
    }
    public Address getWardById(int id){
        Address p = new Address();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from address WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setMoreInfo(rs.getString("more_info"));
                p.setWardId(rs.getInt("ward_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
}
