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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private static final CustomerService c = new CustomerService();
    @FXML private TableView<Customer> tbCustomers;
    @FXML private TextField txtSearchCus;
    @FXML private TextField txtUpPoint;
    @FXML private Button btUpdate;
        
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
        colGender.setCellValueFactory(new PropertyValueFactory("genderId"));
        colGender.setPrefWidth(90);
        
        TableColumn colAdderess = new TableColumn("Địa chỉ");
        colAdderess.setCellValueFactory(new PropertyValueFactory("addressId"));
        colAdderess.setPrefWidth(100);
        
        TableColumn colCard = new TableColumn("Số CMND");
        colCard.setCellValueFactory(new PropertyValueFactory("cardId"));
        colCard.setPrefWidth(100);
        
        TableColumn colPhone = new TableColumn("Số điện thoại");
        colPhone.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        colPhone.setPrefWidth(100);
        
        TableColumn colPoint = new TableColumn("Điểm tích luỹ");
        colPoint.setCellValueFactory(new PropertyValueFactory("availablePoint"));
        colPoint.setPrefWidth(100);
        
        this.tbCustomers.getColumns().addAll(colId, colName, colBirth, colGender, colAdderess, colCard, colPhone, colPoint);
    }
    
    public void addPointCus(ActionEvent e) throws SQLException{
        TextField[] t = new TextField[] {txtSearchCus, txtUpPoint};
        Customer cus = new Customer(Integer.parseInt(txtSearchCus.getText()), Integer.parseInt(txtUpPoint.getText()));
        //c.addPointCus(cus);
        try{
            c.addPointCus(cus);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Cập nhật điểm tích luỹ thành công!");
            alert.show();
            }catch(SQLException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Cập nhật điểm không thành công: " + ex.getMessage());
                alert.show();
            } 
        //vcb 1020228128 nguyen van tung
        
    }
    
}
