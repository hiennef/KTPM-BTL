/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.District;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.Receipt;
import com.mycompany.pojo.ReceiptDetail;
import com.mycompany.pojo.Store;
import com.mycompany.pojo.TableReceiptDetailData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

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
    
    public List<ReceiptDetail> getReceiptDetailsByReceiptId(int receipId){
        List<ReceiptDetail> receiptdetails = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM receipt_detail WHERE receipt_id like ?");
            pstm.setInt(1, receipId);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ReceiptDetail rd = new ReceiptDetail(rs.getInt("product_id"), rs.getInt("receipt_id"), rs.getDouble("quantity"));
                receiptdetails.add(rd);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return receiptdetails;
    }
    
    //Trang chủ
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
    
    //Thanh toán
    public TableReceiptDetailData addRowProductData(Product p, List<TableReceiptDetailData> lt){
        ProductService ps = new ProductService();
        TableReceiptDetailData data = new TableReceiptDetailData();
        boolean find = false;
        for(int i=0; i<lt.size();i++){
            if(lt.get(i).getProductId()== p.getId()){
                data = lt.get(i);
                find=true;
                lt.get(i).setQuantity(data.getQuantity()+1);
                lt.get(i).setProductTotalPrice(ps.getProductPrice(ps.getProductById(lt.get(i)
                        .getProductId()))*(float)data.getQuantity());
                data = lt.get(i);
            }
        }
        if(!find){
            if(ps.isDiscount(p)){
                data.setSoThuTu(String.valueOf(lt.size()+1));
                data.setProductId(p.getId());
                data.setProductName(p.getName());
                data.setQuantity(1);
                data.setProductPrice(p.getSalePrice());
                data.setProductDroppedPrice(p.getSalePrice()*ps.getDiscountById(p.getDiscountId()).getReducePercentage());
                data.setProductTotalPrice(ps.getProductPrice(ps.getProductById(data.getProductId()))*(float)data.getQuantity());
            }
            else{
                data.setSoThuTu(String.valueOf(lt.size()+1));
                data.setProductId(p.getId());
                data.setProductName(p.getName());
                data.setQuantity(1);
                data.setProductPrice(p.getSalePrice());
                data.setProductDroppedPrice(0);
                data.setProductTotalPrice(ps.getProductPrice(ps.getProductById(data.getProductId()))*(float)data.getQuantity());
            }
            lt.add(data);
        }
        return data;
    }
    
    public void loadProductDataView(TableReceiptDetailData d, List<TextField> t){
        ProductService ps = new ProductService();
        t.get(0).setText(String.valueOf(d.getProductId()));
        t.get(1).setText(d.getProductName());
        t.get(2).setText(String.valueOf(d.getQuantity()));
        t.get(3).setText(String.valueOf(d.getProductPrice()));
        t.get(4).setText(String.valueOf(ps.getProductById(d.getProductId())
                .getDiscountId()));
    }
    
    public TableReceiptDetailData updateRowProductData(TableReceiptDetailData p, List<TableReceiptDetailData> lt, double quantity){
        ProductService ps = new ProductService();
        lt.get(lt.indexOf(p)).setQuantity(quantity);
        lt.get(lt.indexOf(p)).setProductTotalPrice((float)quantity*ps.getProductPrice(ps.getProductById(p.getProductId())));
        return lt.get(lt.indexOf(p));
    }
    
    public boolean removeRowProductData(TableReceiptDetailData p, List<TableReceiptDetailData> lt){
        try{
            lt.remove(lt.indexOf(p));
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public List<Float> updatePrice(List<TableReceiptDetailData> lt, float tongTien, float daGiam, float thanhTien, float giamgia){
        tongTien = 0;
        daGiam = 0;
        thanhTien = 0;
        List<Float> list = new ArrayList<>();
        for (TableReceiptDetailData l : lt){
            tongTien += l.getProductPrice()*l.getQuantity();
            daGiam += l.getProductDroppedPrice()*l.getQuantity();
            thanhTien += l.getProductTotalPrice();
        }
        thanhTien = thanhTien - thanhTien*giamgia;
        list.add(tongTien);
        list.add(daGiam);
        list.add(thanhTien);
        return list;
    }
    
    public List<ReceiptDetail> addReceiptDetail(Receipt receipt, List<TableReceiptDetailData> lt) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            for (TableReceiptDetailData l : lt){
                PreparedStatement stm = conn.prepareStatement("INSERT INTO receipt_detail(product_id, receipt_id, quantity) VALUES (?, ?, ?)");
                
                stm.setInt(1, l.getProductId());
                stm.setInt(2, receipt.getId());
                stm.setDouble(3, l.getQuantity());
                
                stm.executeUpdate();
            }
            conn.commit();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return getReceiptDetailsByReceiptId(receipt.getId());
    }
    
    
    
}
