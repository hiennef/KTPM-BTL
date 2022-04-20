/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.pojo.Employee;
import com.mycompany.services.EmployeeService;
import com.mycompany.services.StoreService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author Vi
 */
public class TaiKhoanController extends TrangChudemo2Controller{
    EmployeeService es = new EmployeeService();
    StoreService ss = new StoreService();
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtLastName;
    @FXML private DatePicker dpBirthday;
    @FXML private TextField txtAddress;
    @FXML private TextField txtCard;
    @FXML private TextField txtPhone;
    @FXML private TextField txtStore;
    @FXML private TextField txtGender;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtId.setText(String.valueOf(TrangChudemo2Controller.em.getId()));
        txtName.setText(TrangChudemo2Controller.em.getFirstName());
        txtLastName.setText(TrangChudemo2Controller.em.getLastName());
        //dpBirthday.setDayCellFactory(TrangChudemo2Controller.em.getBirthday());
        
        txtGender.setText(es.getGenderById(TrangChudemo2Controller.em.getGenderId()).toString());
        
        
        txtAddress.setText(String.valueOf(TrangChudemo2Controller.em.getAddressId()));
        txtCard.setText(TrangChudemo2Controller.em.getCardId());
        txtPhone.setText(TrangChudemo2Controller.em.getPhoneNumber());
        try {
            txtStore.setText(ss.getStoreById(TrangChudemo2Controller.em.getStoreId()).toString());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
