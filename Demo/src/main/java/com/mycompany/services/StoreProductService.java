/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.ReceiptDetail;
import com.mycompany.pojo.Store;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author HIEN
 */
public class StoreProductService {
    public double getProductQuantity(int storeId, int productId){
        double productQuantity = 0;
        try(Connection conn =jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM "
                    + "store_product WHERE store_id LIKE ? AND product_id LIKE ?");
            pstm.setInt(1, storeId);
            pstm.setInt(2, productId);
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                productQuantity = rs.getDouble("quantity");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return productQuantity;
    }
    
    public boolean checkProductQuantity(int storeId, int productId, double quantity){
        if(getProductQuantity(storeId, productId)>=quantity)
            return true;
        else
            return false;
    }
    
    public void updateProductQuantity(List<ReceiptDetail> list, int store_id){
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            for (ReceiptDetail r:list){
                PreparedStatement stm2 = conn.prepareStatement("UPDATE store_product SET quantity = quantity - ? WHERE store_id = ? and product_id = ?");
                stm2.setDouble(1, r.getQuantity());
                stm2.setInt(2, store_id);
                stm2.setInt(3, r.getProductId());

                stm2.executeUpdate();
                conn.commit();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
