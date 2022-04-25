/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.store;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Employee;
import com.mycompany.pojo.Store;
import com.mycompany.services.EmployeeService;
import com.mycompany.services.StoreService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Star
 */
public class StoreTester {
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
    public void testGetStores() throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM store");
        
        System.out.println("Danh sách các chi nhánh: ");
        while (rs.next()) {
            String name = rs.getString("name");
            String phone = rs.getString("phone_number");
            
            System.out.println(name +" "+ phone);
        }
    }
    
    @Test
    public void testGetstoreById() throws SQLException{
        StoreService es = new StoreService();
        Store s = es.getStoreById(2);
        System.out.println(s.getName());
        Assertions.assertSame(s.getId(), 2);
    }
    
}
