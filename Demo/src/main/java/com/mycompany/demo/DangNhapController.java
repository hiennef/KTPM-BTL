/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Employee;
import com.mycompany.services.EmployeeService;
import java.lang.ModuleLayer.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 *
 * @author HIEN
 */
public class DangNhapController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label lbuser;
    private static final EmployeeService es = new EmployeeService();
    public static Employee employee = new Employee();
    
    public void DangNhap(ActionEvent e){
        try{
            employee = es.getEmployeeByUser(username.getText(), password.getText());
            if (employee.getUserRoleId()==1 || employee.getUserRoleId()==2||
                    employee.getUserRoleId()==3 || employee.getUserRoleId()==4){
                stage =(Stage)((Node)e.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("TrangChu.fxml"));
                Parent root = loader.load();
                scene= new Scene(root);
                TrangChudemo2Controller controller = loader.getController();
                controller.setUser(employee);
                stage.setScene(scene);
                stage.show();
            }
            else {
                Utils.getBox("Không tìm thấy!", Alert.AlertType.ERROR).show();
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
