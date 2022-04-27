/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Discount;
import com.mycompany.pojo.Producer;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.ProductSubtype;
import com.mycompany.pojo.ProductType;
import com.mycompany.pojo.ProductUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HIEN
 */
public class ProductService {
    public Producer getProducerById(int id){
        Producer p = new Producer();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from producer WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAddressId(rs.getInt("address_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    public Product getProductById(int id){
        Product p = new Product();
        try(Connection conn = jdbcUtils.getConn()){
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
    
    public float getProductPrice(Product p){
        float price = p.getSalePrice();
        if (isDiscount(p)){
            price = p.getSalePrice() - (getDiscountById(p.getDiscountId()).getReducePercentage()*p.getSalePrice());
        }
        return price;
    }
    
    public boolean isDiscount(Product p){
        try{
            Discount d = getDiscountById(p.getDiscountId());
            if(d.getStartTime().before(Timestamp.from(Instant.now()))&&
                    d.getEndTime().after(Timestamp.from(Instant.now()))){
                return true;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }
    
    public Discount getDiscountById(int id){
        Discount d = new Discount();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM discount WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setStartTime(rs.getTimestamp("start_time"));
                d.setEndTime(rs.getTimestamp("end_time"));
                d.setReducePercentage(rs.getFloat("reduce_percentage"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return d;
    }
    
    public ProductSubtype getProSubType_ById(int id){
        ProductSubtype p = new ProductSubtype();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from product_subtype WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setTypeId(rs.getInt("product_type_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    public ProductType getProType_ById(int id){
        ProductType p = new ProductType();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from product_type WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    public ProductUnit getProductUnitById(int id){
        ProductUnit p = new ProductUnit();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from product_unit WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    public List<Product> getProduct(String kw) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM product WHERE name like concat('%', ?, '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           List<Product> Product = new ArrayList<>();
          
           while (rs.next()) {
               Producer p = this.getProducerById(rs.getInt("producer_id"));
               ProductSubtype Sb = this.getProSubType_ById(rs.getInt("type_id"));
               ProductUnit Un = this.getProductUnitById(rs.getInt("unit_id"));
               
                int id = rs.getInt("id");
                String Name = rs.getString("name");
                float purchasePrice = rs.getFloat("purchase_price");
                float salePrice = rs.getFloat("sale_price");
                int producerId = rs.getInt("producer_id"); 
                int subtypeId = rs.getInt("type_id");
                int unitId = rs.getInt("unit_id");
                int discount = rs.getInt("discount_id");
                String producerName = p.getName();
                String typeName = Sb.getName();
                String unitName = Un.getName();
               
               Product.add(new Product( id,  Name, purchasePrice, salePrice, producerId, subtypeId,unitId, discount,
            producerName,typeName, unitName));
           }
           
           return Product;
       }
   }
    public List<ProductSubtype> getProSub_ByProTypeId(int pid) throws SQLException {
        
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from product_subtype WHERE product_type_id LIKE ?");
            pstm.setInt(1, pid);
            ResultSet rs = pstm.executeQuery();
            List<ProductSubtype> cates = new ArrayList<>();
            while(rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("name");
               int productTypeId = rs.getInt("product_type_id");
               cates.add(new ProductSubtype(id, name, productTypeId));
               
            }
        return cates;
        
        }
        
    }
    public List<Discount> getCBDiscountId() throws SQLException {
        
        try(Connection conn = jdbcUtils.getConn()){
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM discount");
                List<Discount> cates = new ArrayList<>();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Timestamp startTime = rs.getTimestamp("start_time");
                    Timestamp endTime = rs.getTimestamp("end_time");
                    float reducePercentage = rs.getFloat("reduce_percentage");
                   cates.add(new Discount(id, name,startTime, endTime, reducePercentage));
               
            }
        return cates;
        
        }
        
    }
    public List<Product> getcbProduct() throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM product");
            
            List<Product> cates = new ArrayList<>();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Float PurchasePrice = rs.getFloat("purchase_price");
                Float SalePrice = rs.getFloat("sale_price") ;
                int ProducerId = rs.getInt("producer_id");
                int SubtypeId = rs.getInt("type_id");
                int UnitId = rs.getInt("unit_id") ;
                int DiscountId = rs.getInt("discount_id");
                cates.add(new Product(id, name,PurchasePrice,SalePrice,ProducerId, SubtypeId,UnitId,DiscountId ));
            }
            return cates;
        }
    }
    public List<ProductType> getProductType() throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM product_type");
            
            List<ProductType> cates = new ArrayList<>();
            
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               cates.add(new ProductType(id, name));
            }
            return cates;
        }
    }
    public List<ProductUnit> getProductUnit() throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM product_unit");
            
            List<ProductUnit> cates = new ArrayList<>();
            
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               cates.add(new ProductUnit(id, name));
            }
            return cates;
        }
    }
    public List<Producer> getProducer() throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM producer");
            
            List<Producer> cates = new ArrayList<>();
            
            while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               int addressid = rs.getInt("address_id");
               cates.add(new Producer(id, name, addressid));
            }
            return cates;
        }
    }
    public boolean addProduct(Product q) throws SQLException {
        String q1 = "INSERT INTO product(name, purchase_price, sale_price, producer_id,type_id, unit_id) " +
               "VALUES(?, ?, ?, ?, ?, ?)";

         try (Connection conn = jdbcUtils.getConn()) {
//             conn.setAutoCommit(false);
            PreparedStatement stm1 = conn.prepareStatement(q1);
            stm1.setString(1, q.getName());
            stm1.setFloat(2, q.getPurchasePrice());
            stm1.setFloat(3, q.getSalePrice());
            stm1.setInt(4, q.getProducerId());
            stm1.setInt(5, q.getSubtypeId());
            stm1.setInt(6,q.getUnitId());
            
             return stm1.executeUpdate()>0;
             
            }
    }
    public boolean deleteProduct(int qId) throws SQLException {
       try (Connection conn = jdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM product WHERE id=?");
           stm.setInt(1, qId);
           
           return stm.executeUpdate() > 0;
       }
   }
    public void updateProduct(Product st) throws SQLException {
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm2 = conn.prepareStatement("UPDATE product Set name = ?, purchase_price = ?, sale_price= ?,"
                         + " producer_id =?, type_id =?, unit_id =?  WHERE id =?");
                stm2.setString(1, st.getName());
                stm2.setFloat(2, st.getPurchasePrice());
                stm2.setFloat(3, st.getSalePrice());
                stm2.setInt(4, st.getProducerId());
                stm2.setInt(5, st.getSubtypeId());
                stm2.setInt(6, st.getUnitId());
                stm2.setInt(7, st.getId());
                stm2.executeUpdate();
                conn.commit();
                
                
            }
    }  
    //update 26/4
    public boolean apdung(Product q) throws SQLException {
        String q1 = "UPDATE product Set discount_id=? WHERE id = ?";

         try (Connection conn = jdbcUtils.getConn()) {
//             conn.setAutoCommit(false);
            PreparedStatement stm1 = conn.prepareStatement(q1);
            stm1.setInt(1, q.getDiscountId());
            stm1.setInt(2, q.getId());
             return stm1.executeUpdate()>0;
             
            }
    }
}
