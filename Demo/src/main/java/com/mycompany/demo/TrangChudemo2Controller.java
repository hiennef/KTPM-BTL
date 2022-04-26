/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.demo;


//import static com.mycompany.demo.DangNhapController.employee;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.mycompany.pojo.Employee;
import com.mycompany.pojo.Receipt;
import com.mycompany.pojo.TableReceiptDetailData;
import com.mycompany.services.EmployeeService;
import com.mycompany.services.ReceiptDetailService;
import com.mycompany.services.ReceiptService;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class TrangChudemo2Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private MenuItem quanLyNV;
    @FXML private MenuItem quanLyCN;

    @FXML
    private BorderPane borderpane, borderpaneThemNV;
    @FXML 
    private Label lbusername;
    @FXML 
    private TableView<TableReceiptDetailData> tbReceipt;
    @FXML 
    private TextField txtDoanhthu;
    
    
      
    private static final ReceiptService rs = new ReceiptService();
    private static final ReceiptDetailService rds = new ReceiptDetailService();
    public static Employee em = new Employee();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableView();
        loadTableData();
        loadDoanhthu();
<<<<<<< HEAD
        setUser(em);

=======

        
>>>>>>> 49aebcba8b2782887d83a0055ee8319c027aaa60
    }    
    
    @FXML
    private void trangchu(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("TrangChu.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public Employee loadUser(Employee employee){
        this.em = employee;
        return this.em;
    }
    
    @FXML
    private void taobill(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("thanhtoan.fxml"));
        borderpane.setCenter(root);
    }
    
   
    @FXML
    private void taikhoan(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("taikhoan.fxml"));
        borderpane.setCenter(root);
    }
    @FXML
    private void QLChiNhanh(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("QLChiNhanh.fxml"));
        borderpane.setCenter(root);
    }
    @FXML
    private void theKH(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("theKH.fxml"));
        borderpane.setCenter(root);
    }
    @FXML
    private void TracuuKH(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("TracuuKH.fxml"));
        borderpane.setCenter(root);
    }
    @FXML
    private void QLNhanvien(ActionEvent event)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("QLNhanvien.fxml"));
        borderpane.setCenter(root);
    }
  
    @FXML
    private void QLHanghoa(ActionEvent event) throws IOException
    {
<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("QLHangHoa.fxml"));
=======
        Parent root = FXMLLoader.load(getClass().getResource("QLHanghoa1.fxml"));
>>>>>>> 49aebcba8b2782887d83a0055ee8319c027aaa60
        borderpane.setCenter(root);
    }
    
    @FXML
    private void User(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
        borderpane.setCenter(root);
    }
    
    public void DangXuat(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("dangNhap.fxml"));
                stage =(Stage)((Node)e.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    //update 24/4
    public void setUser(Employee employee){
        this.lbusername.setText(employee.getLastName());
<<<<<<< HEAD
=======
        
>>>>>>> 49aebcba8b2782887d83a0055ee8319c027aaa60
        if (employee.getUserRoleId() == 4 || employee.getUserRoleId() == 3){
            this.quanLyNV.setVisible(true);
        }
        else{
            this.quanLyNV.setVisible(false);
        }
        
        if (employee.getUserRoleId() == 3){
            this.quanLyCN.setVisible(true);
        }
        else{
            this.quanLyCN.setVisible(false);
        }
    }
   
    public void loadTableView(){
        
        TableColumn colSTT = new TableColumn("STT");
        colSTT.setCellValueFactory(new PropertyValueFactory("soThuTu"));
        colSTT.setPrefWidth(50);
        
        TableColumn colReceiptId = new TableColumn("Mã đơn hàng");
        colReceiptId.setCellValueFactory(new PropertyValueFactory("receiptId"));
        colReceiptId.setPrefWidth(50);
        
        TableColumn colCreatedDate = new TableColumn("Ngày chứng từ");
        colCreatedDate.setCellValueFactory(new PropertyValueFactory("createdDate"));
        colCreatedDate.setPrefWidth(130);
        
        TableColumn colProductName = new TableColumn("Tên sản phẩm");
        colProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        colProductName.setPrefWidth(150);
        
        TableColumn colQuantity = new TableColumn("Số lượng");
        colQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        colQuantity.setPrefWidth(60);
        
        TableColumn colProducerName = new TableColumn("Xuất xứ");
        colProducerName.setCellValueFactory(new PropertyValueFactory("producerName"));
        colProducerName.setPrefWidth(70);
        
        TableColumn colProductPrice = new TableColumn("Đơn giá");
        colProductPrice.setCellValueFactory(new PropertyValueFactory("productPrice"));
        colProductPrice.setPrefWidth(70);
        
        TableColumn colCustomerName = new TableColumn("Tên khách hàng");
        colCustomerName.setCellValueFactory(new PropertyValueFactory("customerName"));
        colCustomerName.setPrefWidth(50);
        
        TableColumn colEmployeeName = new TableColumn("Tên nhân viên");
        colEmployeeName.setCellValueFactory(new PropertyValueFactory("employeeName"));
        colEmployeeName.setPrefWidth(50);
        
        this.tbReceipt.getColumns().addAll(colSTT, colReceiptId,colCreatedDate, 
                colProductName,colQuantity, colProducerName, colProductPrice,
                colCustomerName, colEmployeeName);
    }
    
    public void loadTableData(){
        try{
            this.tbReceipt.setItems(FXCollections.observableList(rds.getTbReceiptDetailData()));
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void loadDoanhthu(){
        var now = Date.from(Instant.now());
        System.out.println(now);
        var year = now.getYear();
        var month = now.getMonth();
        var day = now.getDate();
        this.txtDoanhthu.setText(String.valueOf(rs.tinhDoanhthu(new Date(year,month,day))));
    }
    
}
