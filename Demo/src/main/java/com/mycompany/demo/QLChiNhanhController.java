/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.pojo.Store;
import com.mycompany.services.StoreService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Administrator
 */
public class QLChiNhanhController extends TrangChudemo2Controller {
    private static final StoreService s = new StoreService();
    @FXML
    private TableView<Store> tbStore;
    @FXML
    private TextField txtKeyword ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadColumns();
        this.loadData(null);
        this.txtKeyword.textProperty().addListener((evt) -> {
            this.loadData(this.txtKeyword.getText());
        });
    }
   
    private void loadData(String kw) {
        try {
            this.tbStore.setItems(FXCollections.observableList(s.gettbStore(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QLChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   private void loadColumns() {
        
        TableColumn col1 = new TableColumn("Mã chi nhánh");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col1.setPrefWidth(150);
        
        TableColumn col2 = new TableColumn("Tên chi nhánh");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setPrefWidth(250);
        
        TableColumn col3 = new TableColumn("Địa chỉ");
        col3.setCellValueFactory(new PropertyValueFactory<>("addressId"));
        col3.setPrefWidth(200);
        
        TableColumn col4 = new TableColumn("Hotline");
        col4.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col4.setPrefWidth(130);
        
            
        this.tbStore.getColumns().addAll( col1, col2, col3, col4);
     
    }
   
}
