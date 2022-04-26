/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Receipt;
import com.mycompany.pojo.ReceiptDetail;
import com.mycompany.pojo.TableReceiptDetailData;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HIEN
 */
public class ReceiptService {
    public List<Receipt> getReceipts(){
        List<Receipt> receipts = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM receipt");
            
            while(rs.next()){
               Receipt r = new Receipt(rs.getInt("id"), 
                       rs.getTimestamp("created_date"), 
                       rs.getFloat("total_price"), rs.getInt("reward_point"),
                       rs.getInt("customer_id"), rs.getInt("employee_id"));
               receipts.add(r);
           }
        }
        catch (Exception ex ){
            System.out.println(ex.getMessage());
        }
        return receipts;
    }
    
    public Customer getCustomerById(int id){
        Customer c = new Customer();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from customer WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt("id"));
                c.setLastName(rs.getString("last_name"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
    public List<Receipt> getReceiptsByDate(Date d){
        List<Receipt> receipts = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM receipt WHERE created_date like concat('%',?,'%')");
            pstm.setDate(1, d);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Receipt r = new Receipt(rs.getInt("id"), 
                        rs.getTimestamp("created_date"), 
                        rs.getFloat("total_price"), rs.getInt("reward_point"), 
                        rs.getInt("customer_id"), rs.getInt("employee_id"));
                receipts.add(r);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return receipts;
    }
    
    public Float tinhDoanhthu(Date d){
        float doanhthu = 0;
        try{
            List<Receipt> receipts = getReceiptsByDate(d);
            for (int i =0; i<receipts.size();i++){
                doanhthu += receipts.get(i).getTotalPrice();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return doanhthu;
    }
    
    public void addReceipt(Timestamp createdDate, float totalPrice, int customerId, int employeeId, int storeId, List<TableReceiptDetailData> lt) throws SQLException{
        ReceiptDetailService rds = new ReceiptDetailService();
        StoreProductService sps = new StoreProductService();
        EmployeeService es = new EmployeeService();
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(
                    "INSERT INTO receipt(created_date, total_price, reward_point, customer_id, employee_id) VALUES(?, ?, ?, ?, ?)");
            stm.setTimestamp(1, createdDate);
            stm.setFloat(2, totalPrice);
            stm.setInt(3, addPoint(totalPrice, customerId));
            stm.setInt(4, customerId);
            stm.setInt(5, employeeId);
            
            stm.executeUpdate();
            
            conn.commit();
            
            List<ReceiptDetail> rd = rds.addReceiptDetail(getLastReceipt(), lt);
            sps.updateProductQuantity(rd, storeId);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Receipt getLastReceipt(){
        Receipt r = new Receipt();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            
            ResultSet rs = stm.executeQuery("SELECT * FROM receipt");
            while(rs.next()){
                if(rs.isLast()){
                    r.setId(rs.getInt("id"));
                    r.setCreatedDate(rs.getTimestamp("created_date"));
                    r.setTotalPrice(rs.getFloat("total_price"));
                    r.setRewardPoint(rs.getInt("reward_point"));
                    r.setCustomerId(rs.getInt("customer_id"));
                    r.setEmployeeId(rs.getInt("employee_id"));

                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return r;
    }
    
    public int addPoint(float totalPrice, int customerId){
        int point = 0;
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            point =(int) Math.floor(totalPrice/100000)*3;
            PreparedStatement stm = conn.prepareStatement("UPDATE customer SET available_point = available_point + ? WHERE id = ?");
            stm.setInt(1, point);
            stm.setInt(2, customerId);
            
            stm.executeUpdate();
            
            conn.commit();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return point ;
    }
}
