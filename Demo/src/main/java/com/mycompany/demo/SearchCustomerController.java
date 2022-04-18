/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Employee;
import com.mycompany.services.CustomerService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Vi
 */
public class SearchCustomerController extends TrangChudemo2Controller  {
    @FXML private TableView<Customer> tbCustomers;
    @FXML private TextField txtSearchCus;
    @FXML private TextField txtPoint;
    @FXML private Button btSearch;
        
    @Override
    public void initialize(URL url, ResourceBundle rb){
        loadTableView();
        loadTableCus();
        //this.txtSearchCus.textProperty().addListener((evt)->{
        //    //oadTableCus(this.txtSearchCus.getText());
        //});
    }
    
    @Override
    public void loadTableView(){
        TableColumn colSTT = new TableColumn("STT");
        colSTT.setCellValueFactory(new PropertyValueFactory("id"));
        colSTT.setPrefWidth(50);
        
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
        colGender.setCellValueFactory(new PropertyValueFactory("genderId"));
        colGender.setPrefWidth(100);
        
        TableColumn colAdderess = new TableColumn("Địa chỉ");
        colAdderess.setCellValueFactory(new PropertyValueFactory("addressId"));
        colAdderess.setPrefWidth(150);
        
        TableColumn colCard = new TableColumn("Số CMND");
        colCard.setCellValueFactory(new PropertyValueFactory("cardId"));
        colCard.setPrefWidth(100);
        
        TableColumn colPhone = new TableColumn("Số điện thoại");
        colPhone.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        colPhone.setPrefWidth(150);
        
        this.tbCustomers.getColumns().addAll(colId, colName, colBirth, colGender, colAdderess, colCard, colPhone);
    }
    
    private void loadTableCus(){
        CustomerService s = new CustomerService();
        this.tbCustomers.setItems(FXCollections.observableList(s.getCustomer()));
    }
    
}
