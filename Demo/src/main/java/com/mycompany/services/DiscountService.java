/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Discount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class DiscountService {
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
        String d = year + "-" + month +"-" + day +" 00:00:00";
        
        return d;
    }
    public List<Discount> getTbDiscount(String kw) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM discount WHERE id like concat('%', ?, '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
 
           List<Discount> q = new ArrayList<>();
          
           while (rs.next()) {
               int id = rs.getInt("id");
               String Name = rs.getString("name");
               Timestamp start_time = rs.getTimestamp("start_time");
               Timestamp end_time = rs.getTimestamp("end_time");
               Float reducePercentage = rs.getFloat("reduce_percentage");
               q.add(new Discount( id, Name, start_time, end_time, reducePercentage));
            
           }
           
           return q;
       }
   }
    public boolean addDiscount(Discount q) throws SQLException {
        String q1 = "INSERT INTO discount(name, start_time , end_time, reduce_percentage ) VALUES(?, ?, ?, ?)";

         try (Connection conn = jdbcUtils.getConn()) {
//             conn.setAutoCommit(false);
             PreparedStatement stm1 = conn.prepareStatement(q1);
             stm1.setString(1, q.getName());
             stm1.setTimestamp(2,q.getStartTime() );
             stm1.setTimestamp(3, q.getEndTime());
             stm1.setFloat(4, q.getReducePercentage());
             return stm1.executeUpdate()>0;
             
            }
    }
}
