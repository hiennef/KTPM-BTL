/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.store;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Discount;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.ReceiptDetail;
import com.mycompany.pojo.StoreProduct;
import com.mycompany.services.CustomerDiscountService;
import com.mycompany.services.DiscountService;
import com.mycompany.services.ProductService;
import com.mycompany.services.StoreProductService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
public class ProductTester {
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
    public void testupdateProductQuantity() throws SQLException{
        StoreProductService sps = new StoreProductService();
        List<ReceiptDetail> rd = new ArrayList<>();
        StoreProduct sp = new StoreProduct(1,10,20);
        sps.updateProductQuantity(rd, sp.getStoreId());
        System.out.println(sp.getStoreId()+" "+ sp.getProductId() +" "+ sp.getQuantity());
    }
    
    @Test 
    public void TestDGbySDTInvlid() {
       ProductService ps = new ProductService();
       Product p;
       p = ps.getProductById(1);
       Assertions.assertEquals("Rau dền", p.getName());
   }
    
    @Test
    public void testGetLastImportBill(){
        StoreProductService sps = new StoreProductService();
        System.out.println("Importbill: "+ sps.getLastImportBill().getId());
    }
    
    @Test
    public void testTbDiscount() throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM discount");
        
        System.out.println("Danh sách discount: ");
        while (rs.next()) {
            String name = rs.getString("name");
            Timestamp start_time = rs.getTimestamp("start_time");
            Timestamp end_time = rs.getTimestamp("end_time");
            Float reducePercentage = rs.getFloat("reduce_percentage");
            
            System.out.println(name +"  "+ start_time+"  "+ end_time + "  " +reducePercentage);
        }
    }
    
    
    @Test
    public void testGetDiscountById(){
        CustomerDiscountService cds = new CustomerDiscountService();
        Discount d = cds.getDiscountById(2);
        System.out.println(d.getName());
        Assertions.assertEquals("birthday", d.getName());
        // Co tinh fail
    }
    
}
