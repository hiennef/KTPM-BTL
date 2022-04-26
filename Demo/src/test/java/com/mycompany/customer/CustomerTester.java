/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customer;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Customer;
<<<<<<< HEAD
import com.mycompany.services.CustomerService;
=======
import com.mycompany.pojo.Employee;
import com.mycompany.services.CustomerService;
import com.mycompany.services.EmployeeService;
>>>>>>> 49aebcba8b2782887d83a0055ee8319c027aaa60
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
<<<<<<< HEAD
=======
import org.junit.jupiter.api.Assertions;
>>>>>>> 49aebcba8b2782887d83a0055ee8319c027aaa60
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Vi
 */
public class CustomerTester {
    private static Connection conn;
            
    @BeforeAll
    public static void beforeAll() throws SQLException{
        conn = jdbcUtils.getConn();
    }
    
    @AfterAll
    public static void afterAll() throws SQLException{
        if (conn!=null){
            conn.close();
        }
    }
    
    
    @Test
    public void testCustomer() throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM customer");
        
        List<String> kq = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("last_name");
            kq.add(name);
            System.out.println(name);
        }
    }
    
    @Test
    public void testGetCustomer() throws SQLException{
        CustomerService cs = new CustomerService();
        Customer e = new Customer("Tran", "Y", "10/08/2001", "093254656", "243434", 3, 2);
        cs.addCustomer(e);
        System.out.println(e.getLastName());
    }
    
    
    
}
