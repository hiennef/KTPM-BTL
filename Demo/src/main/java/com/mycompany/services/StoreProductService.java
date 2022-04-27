/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.Utils;
import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.ImportBill;
import com.mycompany.pojo.ImportBillDetail;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.ReceiptDetail;
import com.mycompany.pojo.Store;
import com.mycompany.pojo.TableProductData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javax.xml.transform.Result;

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
    
    public TableProductData addTableProductData(int productId, double quantity, List<TableProductData> lt){
        ProductService ps = new ProductService();
        Product p = ps.getProductById(productId);
        TableProductData data = new TableProductData(p, quantity);
        lt.add(data);
        return data;
    }
    
    public boolean deleteTableProductData(TableProductData t, List<TableProductData> lt){
        try{
            lt.remove(t);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public float tinhTongTien(float tongtien, List<TableProductData> lt){
        tongtien = 0;
        for (TableProductData data :lt){
            tongtien+=data.getProductTotalP();
        }
        System.out.println(tongtien);
        return tongtien;
    }
    
    public ImportBill addImportBill(Timestamp createdDate, int storeId, List<TableProductData> lt){
        ImportBill ib = new ImportBill(createdDate, storeId);
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement("INSERT INTO import_bill (created_date, store_id) VALUES (?, ?);");
            stm.setTimestamp(1, createdDate);
            stm.setInt(2, storeId);
            
            stm.executeUpdate();
            conn.commit();
            
            addImportBillDetail(getLastImportBill().getId(), lt);
            updateProductQuantityImport(storeId, lt);
            Utils.getBox("Ghi phiếu thành công", Alert.AlertType.INFORMATION).show();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            Utils.getBox(ex.getMessage(), Alert.AlertType.ERROR).show();
        }
        return ib;
    }
    
    public List<ImportBillDetail> addImportBillDetail(int importBillId, List<TableProductData> lt) throws SQLException{
        List<ImportBillDetail> libd = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            for(TableProductData data:lt){
                PreparedStatement stm = conn.prepareStatement("INSERT INTO import_detail(import_bill_id, product_id, quantity) VALUES (?, ?, ?);");
                stm.setInt(1, importBillId);
                stm.setInt(2, data.getProductId());
                stm.setDouble(3, data.getQuantity());
                
                stm.executeUpdate();
                
                ImportBillDetail ibd = new ImportBillDetail(importBillId, data.getProductId(), data.getQuantity());
                libd.add(ibd);
            }
            conn.commit();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return libd;
    }
    
    public void updateProductQuantityImport(int storeId, List<TableProductData> lt){
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            for (TableProductData r:lt){
                PreparedStatement stm1 = conn.prepareStatement("SELECT * FROM store_product WHERE store_id = ? and product_id = ?;");
                stm1.setInt(1, storeId);
                stm1.setInt(2, r.getProductId());
                
                ResultSet rs = stm1.executeQuery();
                if(rs.next()){
                    PreparedStatement stm2 = conn.prepareStatement("UPDATE store_product SET quantity = quantity + ? WHERE store_id = ? and product_id = ?;");
                    stm2.setDouble(1, r.getQuantity());
                    stm2.setInt(2, storeId);
                    stm2.setInt(3, r.getProductId());

                    stm2.executeUpdate();
                }
                else{
                    PreparedStatement stm2 = conn.prepareStatement("INSERT INTO store_product(store_id, product_id, quantity) VALUES(?,?,?);");
                    stm2.setInt(1, storeId);
                    stm2.setInt(2, r.getProductId());
                    stm2.setDouble(3, r.getQuantity());

                    stm2.executeUpdate();
                }
                
            }
            conn.commit();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public ImportBill getLastImportBill(){
        ImportBill ib = new ImportBill();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM import_bill");
            
            while(rs.next()){
                if(rs.isLast()){
                    ib.setId(rs.getInt("id"));
                    ib.setCreatedDate(rs.getTimestamp("created_date"));
                    ib.setStoreId(rs.getInt("store_id"));
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return ib;
    }
}
