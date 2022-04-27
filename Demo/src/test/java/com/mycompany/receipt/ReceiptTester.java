/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.receipt;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.Receipt;
import com.mycompany.pojo.ReceiptDetail;
import com.mycompany.pojo.Store;
import com.mycompany.pojo.TableReceiptDetailData;
import com.mycompany.services.CustomerDiscountService;
import com.mycompany.services.CustomerService;
import com.mycompany.services.EmployeeService;
import com.mycompany.services.ProductService;
import com.mycompany.services.ReceiptDetailService;
import com.mycompany.services.ReceiptService;
import com.mycompany.services.StoreProductService;
import com.mycompany.services.StoreService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author HIEN
 */
public class ReceiptTester {

    private static Connection conn;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        conn = jdbcUtils.getConn();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    public void testRDDatabase(){
        var now = Date.from(Instant.now());
        System.out.println(now);
        var year = now.getYear();
        var month = now.getMonth();
        var day = now.getDate();
        ReceiptDetailService rds = new ReceiptDetailService();
        ReceiptService rs = new ReceiptService();
        List<Receipt> rc = rs.getReceiptsByDate(new Date(year,month,7));
        Float t = rs.tinhDoanhthu(new Date(year,month,7));
        System.out.println("---------------------------");
        System.out.println("Test");
        for(int i = 0; i<rc.size();i++){
            System.out.println(rc.get(i).getTotalPrice());
        }
        System.out.println("---------------------------");
        System.out.println(t);
        System.out.println("---------------------------");
        System.out.println(new Date(year,month,day));
    }
    
    @Test
    public void testDiscount(){
        ProductService ps = new ProductService();
        System.out.println(ps.getProductPrice(ps.getProductById(8)));
    }
    
    @Test
    public void testAddReceipt() throws SQLException{
        ReceiptService rs = new ReceiptService();
        ProductService ps = new ProductService();
        ReceiptDetailService rds = new ReceiptDetailService();
        StoreProductService sps = new StoreProductService();
        List<TableReceiptDetailData> lt = new ArrayList<>();
        Receipt r1 = rs.getLastReceipt();
        rds.addRowProductData(ps.getProductById(1), lt);
        rds.addRowProductData(ps.getProductById(2), lt);
        rds.addRowProductData(ps.getProductById(3), lt);
        rds.addRowProductData(ps.getProductById(8), lt);
        rds.addRowProductData(ps.getProductById(8), lt);
        rds.addRowProductData(ps.getProductById(5), lt);
        rs.addReceipt(Timestamp.valueOf(LocalDateTime.now()),300000 , 2, 1, 1, lt);
        Receipt r2 = rs.getLastReceipt();
        System.out.println("Test add receipt");
        Assertions.assertNotEquals(r1.getId(), r2.getId(), "sai");
    }
   
//    @Test
//    public void testCusDis(){
//        CustomerDiscountService cds = new CustomerDiscountService();
//        EmployeeService es = new EmployeeService();
//        CustomerService cs = new CustomerService();
//        Customer customer = cs.getCustomerById(7);
//        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
//        Timestamp cusBirthday = Timestamp.valueOf(customer.getBirthday()+" 00:00:00.0");
//        System.out.println(today.getDate()+"/"+today.getMonth());
//        System.out.println(cusBirthday.getDate()+"/"+cusBirthday.getMonth());
//        System.out.println(cds.isBirthday(customer));
//    }
}
