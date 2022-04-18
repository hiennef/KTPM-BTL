/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.receipt;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.Receipt;
import com.mycompany.services.ReceiptDetailService;
import com.mycompany.services.ReceiptService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
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

//    @Test
//    public void testDatabase() {
//        ReceiptService rs = new ReceiptService();
//        List<Receipt> re = rs.getReceipts();
//        for(int i = 0; i<re.size();i++){
//            System.out.println(re.get(i).getCreatedDate());
//        }
//    }
    
    @Test
    public void testRDDatabase(){
        ReceiptDetailService rds = new ReceiptDetailService();
        List<Product> re = rds.getProductsByReceiptId(6);
        List<Receipt> rc = rds.getReceiptsByProductId(8);
        System.out.println("---------------------------");
        System.out.println("Test getReceiptsByProductId");
        for(int i = 0; i<rc.size();i++){
            System.out.println(rc.get(i).getTotalPrice());
        }
        System.out.println("---------------------------");
        System.out.println("Test getProductsByReceiptId");
        for(int i = 0; i<re.size();i++){
            System.out.println(re.get(i).getName());
        }
        System.out.println("---------------------------");
    }
    

}
