/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.receipt;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.services.ReceiptDetailService;
import com.mycompany.services.ReceiptService;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author HIEN
 */
public class ReceiptDetailTester {
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
    
    @ParameterizedTest
    @CsvSource({"1,1","2,1","4,3"})
    public void testaddReceiptDetail(int id, int length){
        ReceiptDetailService rds = new ReceiptDetailService();
        Assertions.assertSame(rds.getReceiptDetailsByReceiptId(id).size(), length );
    }
}
