/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Employee;
import com.mycompany.services.AllComboboxService;
import com.mycompany.services.EmployeeService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Vi
 */
public class EmployeeTester {
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
    public void testEmployee() throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM employee");
        
        List<String> kq = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("last_name");
            kq.add(name);
            System.out.println(name);
        }
    }
    
    @Test
    public void testGetEmployee(){
        EmployeeService es = new EmployeeService();
        Employee e = es.getEmployeeByUser("thuhien", "hihi");
        System.out.println(e.getLastName());
        Assertions.assertSame(e.getId(), 1);
    }
    
    
    @Test
    public void testGetUserRole() throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user_role");
        
        System.out.println("Danh sách user role: ");
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            
            System.out.println(id +" là "+ name);
        }
    }
    
}
