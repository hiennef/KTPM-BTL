/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Discount;
import com.mycompany.pojo.Product;
import com.mycompany.services.ProductService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class ApdungMaController extends TrangChudemo2Controller {
    private static final ProductService s = new ProductService();
    @FXML
    private ComboBox<Product> cbProduct;
    @FXML
    private ComboBox<Discount> cbdiscount;
    @FXML
    private BorderPane borderpane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ProductService t = new ProductService();
        try {
            this.cbProduct.setItems(FXCollections.observableList(t.getcbProduct()));
            this.cbdiscount.setItems(FXCollections.observableList(t.getCBDiscountId()));
        } catch (SQLException ex) {
            Logger.getLogger(ApdungMaController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void Apdung (ActionEvent evt) throws SQLException, IOException {
      
        

//        Product q = new Product(this.cbdiscount.getSelectionModel().getSelectedItem().getId(), 
//                this.cbProduct.getSelectionModel().getSelectedItem().getId());
        
        try{
//        s.apdung(q);
        Connection conn = jdbcUtils.getConn();
        int v1 = this.cbdiscount.getSelectionModel().getSelectedItem().getId();
        int v2 = this.cbProduct.getSelectionModel().getSelectedItem().getId();
        String sql1 = "UPDATE product Set discount_id ='"+v1+"' WHERE id = '"+v2+"'";
        PreparedStatement stm1 = conn.prepareStatement(sql1);

        stm1.execute(); 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Áp dụng mã giảm vào sản phẩm mới thành công!");
        alert.show();
        Parent root = FXMLLoader.load(getClass().getResource("TrangChu.fxml"));
        stage =(Stage)((Node)evt.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        }catch(SQLException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText( ex.getMessage());
            alert.show();
        }
   }
}
