/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.store;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Address;
import com.mycompany.services.AddressService;
import com.mycompany.services.AllComboboxService;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Vi
 */
public class AddressTester {
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
    public void testGetAddress() throws SQLException{
        AddressService as = new AddressService();
        Address a = as.getAddressById(1);
        System.out.println(a.getId());
        Assertions.assertSame(a.getId(), 1);
    }
    
    @Test
    public void testGetAddressById() throws SQLException{
        AllComboboxService acs = new AllComboboxService();
        System.out.println(acs.getAddressById(1).getMoreInfo() + " " + acs.getAddressById(1).getWardId());
    }
    
}
