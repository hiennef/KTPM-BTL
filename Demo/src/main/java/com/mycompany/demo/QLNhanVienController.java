/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Employee;
import com.mycompany.pojo.ExportDataTbEmployee;
import com.mycompany.pojo.Store;
import com.mycompany.services.EmployeeService;
import com.mycompany.services.StoreService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;





/**
 *
 * @author Administrator
 */
public class QLNhanVienController extends TrangChudemo2Controller {

    private static final EmployeeService s = new EmployeeService();
    @FXML
    private TableView<ExportDataTbEmployee> tableEmployee;
//    @FXML
//    private ComboBox<Store> cbStore;
    @FXML
    private TextField txtKeyword ;
    @FXML
//    private TextField txtID ;
//    @FXML
//    private TextField txtFirst_Name ;
//    @FXML
//    private TextField txtLast_Name ;
//    @FXML
//    private TextField txtPhone_Number ;
//    @FXML
//    private TextField txtAddress ;
//    @FXML
//    private TextField txtRole ;
//    @FXML
//    private TextField txt_Card ;
//    @FXML
//    private RadioButton rdNam;
//    @FXML
//    private RadioButton rdNu;
//    @FXML
//    private RadioButton rdKhac;
    
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
            this.tableEmployee.setItems(FXCollections.observableList(s.getEmployee(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QLNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   private void loadColumns() {
        
        TableColumn col1 = new TableColumn("Mã nhân viên");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col1.setPrefWidth(90);
        
        TableColumn col2 = new TableColumn("Họ");
        col2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col2.setPrefWidth(120);
        
        TableColumn col3 = new TableColumn("Tên");
        col3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col3.setPrefWidth(120);
        
        TableColumn col4 = new TableColumn("Chi nhánh");
        col4.setCellValueFactory(new PropertyValueFactory<>("storeId"));
        col4.setPrefWidth(150);
        
        TableColumn col5 = new TableColumn("Chức vụ");
        col5.setCellValueFactory(new PropertyValueFactory<>("userRoleId"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Số ngày công");
        col6.setCellValueFactory(new PropertyValueFactory<>("workingHours"));
        col6.setPrefWidth(90);
        TableColumn col7 = new TableColumn("Thao tác");
        col7.setCellFactory((p) -> {
            Button btn = new Button("Xóa");
            
            btn.setOnAction((evt) -> {
                TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                ExportDataTbEmployee q = (ExportDataTbEmployee) c.getTableRow().getItem();
                
                
                try {
                    if (s.deleteEmployee(q.getId()) == true) {
                        Utils.getBox("Đã xóa nhân viên thành công!", Alert.AlertType.INFORMATION).show();
                        this.loadData(null);
                    } else {
                        Utils.getBox("Xóa không thành công!", Alert.AlertType.ERROR).show();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(QLNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
            
            
        this.tableEmployee.getColumns().addAll( col1, col2, col3, col4, col5, col6, col7);
     
    }
   
    
    
}