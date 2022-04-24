/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.DataTbCustomer;
import com.mycompany.services.CustomerService;
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
 * @author Vi
 */
public class SearchCustomerController extends TrangChudemo2Controller  {
    private static final CustomerService c = new CustomerService();
    @FXML private TableView<DataTbCustomer> tbCustomers;
    @FXML private TextField txtSearchCus;
        
    @Override
    public void initialize(URL url, ResourceBundle rb){
        loadTableView();
        loadData(null);
        this.txtSearchCus.textProperty().addListener((evt) -> {
            loadData(this.txtSearchCus.getText());
            
        });
    }
    
    
    private void loadData(String kw) {
        try {
            this.tbCustomers.setItems(FXCollections.observableList(c.getCustomer(kw)));
            
        } catch (SQLException ex) {
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void loadTableView(){
        
        TableColumn colId = new TableColumn("Mã KH");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);
        
        TableColumn colName = new TableColumn("Tên");
        colName.setCellValueFactory(new PropertyValueFactory("lastName"));
        colName.setPrefWidth(100);
        
        TableColumn colBirth = new TableColumn("Ngày sinh");
        colBirth.setCellValueFactory(new PropertyValueFactory("birthday"));
        colBirth.setPrefWidth(100);
        
        TableColumn colGender = new TableColumn("Giới tính");
        colGender.setCellValueFactory(new PropertyValueFactory("genderName"));
        colGender.setPrefWidth(90);
        
        TableColumn colCard = new TableColumn("Số CMND");
        colCard.setCellValueFactory(new PropertyValueFactory("cardId"));
        colCard.setPrefWidth(150);
        
        TableColumn colPhone = new TableColumn("Số điện thoại");
        colPhone.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        colPhone.setPrefWidth(150);
        
        TableColumn colPoint = new TableColumn("Điểm tích luỹ");
        colPoint.setCellValueFactory(new PropertyValueFactory("availablePoint"));
        colPoint.setPrefWidth(100);
        
        this.tbCustomers.getColumns().addAll(colId, colName, colBirth, colGender, colCard, colPhone, colPoint);
    }
    
}
