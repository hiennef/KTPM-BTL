/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.Receipt;
import com.mycompany.pojo.ReceiptDetail;
import com.mycompany.pojo.TableReceiptDetailData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HIEN
 */
public class ReceiptDetailService {
    public List<Receipt> getReceiptsByProductId(int productId){
        List<Receipt> receipts = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM receipt_detail WHERE product_id like ?");
            pstm.setInt(1, productId);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                PreparedStatement stm = conn.prepareStatement("SELECT * FROM receipt WHERE id like ?");
                stm.setInt(1, rs.getInt("receipt_id"));
                ResultSet receipt = stm.executeQuery();
                while(receipt.next()){
                    Receipt r = new Receipt(receipt.getInt("id"), 
                       receipt.getTimestamp("created_date"), 
                       receipt.getFloat("total_price"), receipt.getInt("reward_point"),
                       receipt.getInt("customer_id"), receipt.getInt("employee_id"));
                    receipts.add(r);
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return receipts;
    }
    public List<Product> getProductsByReceiptId(int receiptId){
        List<Product> products = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM receipt_detail WHERE receipt_id like ?");
            pstm.setInt(1, receiptId);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                PreparedStatement stm = conn.prepareStatement("SELECT * FROM product WHERE id like ?");
                stm.setInt(1, rs.getInt("product_id"));
                ResultSet product = stm.executeQuery();
                while(product.next()){
                    Product p = new Product(product.getInt("id"),
                            product.getString("name"), 
                            product.getFloat("purchase_price"), 
                            product.getFloat("sale_price"), 
                            product.getInt("producer_id"), 
                            product.getInt("type_id"), 
                            product.getInt("unit_id"),
                            product.getInt("discount_id"));
                    products.add(p);
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return products;
    }
    
    public Receipt getReceiptById(int id){
        Receipt r = new Receipt();
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM receipt WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                r.setId(rs.getInt("id"));
                r.setCreatedDate(rs.getTimestamp("created_date"));
                r.setTotalPrice(rs.getFloat("total_price"));
                r.setRewardPoint(rs.getInt("reward_point"));
                r.setCustomerId(rs.getInt("customer_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return r;
    }
    
    public Product getProductById(int id){
        Product p = new Product();
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM product WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPurchasePrice(rs.getFloat("purchase_price"));
                p.setSalePrice(rs.getFloat("sale_price"));
                p.setProducerId(rs.getInt("producer_id"));
                p.setSubtypeId(rs.getInt("type_id"));
                p.setUnitId(rs.getInt("unit_id"));
                p.setDiscountId(rs.getInt("discount_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    public List<TableReceiptDetailData> getTbReceiptDetailData(){
        ProductService ps = new ProductService();
        ReceiptService rsv = new ReceiptService();
        EmployeeService es = new EmployeeService();
        List<TableReceiptDetailData> data = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM receipt_detail order by receipt_id");
            int i = 1 ;
            int rid = -1 ;
            while(rs.next()){
                Receipt r = getReceiptById(rs.getInt("receipt_id"));
                Product p = getProductById(rs.getInt("product_id"));
                if(i>=2&&r.getId()==rid){
                    TableReceiptDetailData item = new TableReceiptDetailData(r.getCreatedDate(), p.getName(), 
                        rs.getDouble("quantity"), 
                        ps.getProducerById(p.getProducerId()).getName(), 
                        p.getSalePrice(), rsv.getCustomerById(r.getCustomerId())
                                .getLastName(), 
                        es.getEmployeeById(r.getEmployeeId()).getLastName());
                    data.add(item);
                }
                else{
                    TableReceiptDetailData item = new TableReceiptDetailData(String.valueOf(i), 
                        String.valueOf(r.getId()), r.getCreatedDate(), p.getName(), 
                        rs.getDouble("quantity"), 
                        ps.getProducerById(p.getProducerId()).getName(), 
                        p.getSalePrice(), rsv.getCustomerById(r.getCustomerId())
                                .getLastName(), 
                        es.getEmployeeById(r.getEmployeeId()).getLastName());
                    i++;
                    data.add(item);
                }
                rid = r.getId();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
