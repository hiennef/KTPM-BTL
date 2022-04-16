/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.demo;


import static com.mycompany.demo.DangNhapController.employee;
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
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class TrangChudemo2Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private BorderPane borderpane, borderpaneThemNV;
    @FXML 
    private Label lbusername;
    @FXML 
    private TableView<Receipt> tbReceipt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void ThemNV(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("ThemNV.fxml"));
        borderpaneThemNV.setCenter(root);
    }
    @FXML
    private void QLHanghoa(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("QLHanghoa.fxml"));
        borderpane.setCenter(root);
    }
    
    public void DangXuat(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("dangNhap.fxml"));
                stage =(Stage)((Node)e.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    
    public void setUser(Employee employee){
        this.lbusername.setText(employee.getLastName());
    }
}
