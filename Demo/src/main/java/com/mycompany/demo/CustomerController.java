/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.pojo.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author Vi
 */
public class CustomerController  implements Initializable{
    @FXML private Button btAddCus;
    @FXML private RadioButton rd1;
    @FXML private RadioButton rd2;
    @FXML private RadioButton rd3;
    @FXML private TextField txtIdCus;
    @FXML private TextField txtNameCus;
    @FXML private DatePicker dpBirthdayCus;
    @FXML private TextField txtAddressCus;
    @FXML private TextField txtCardCus;
    @FXML private TextField txtPhoneCus;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
    public void addCus(ActionEvent e){
        Customer c = new Customer();
        c.setId(Integer.parseInt(txtIdCus.getText()));
        c.setLastName(txtNameCus.getText());
        c.setAddressId(Integer.parseInt(txtAddressCus.getText()));
        c.setCardId(txtCardCus.getText());
        c.setPhoneNumber(txtPhoneCus.getText());
        
    }
    
}
