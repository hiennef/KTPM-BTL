/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.pojo.Address;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.District;
import com.mycompany.pojo.Province;
import com.mycompany.pojo.Ward;
import com.mycompany.services.AllComboboxService;
import com.mycompany.services.CustomerService;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
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
import javafx.scene.control.TextField;

/**
 *
 * @author Vi
 */
public class CustomerController  implements Initializable{
    private static final CustomerService s = new CustomerService();
    @FXML private RadioButton rd1;
    @FXML private RadioButton rd2;
    @FXML private RadioButton rd3;
    @FXML private ComboBox<Ward> cbWard;
    @FXML private ComboBox<Province> cbProvince;
    @FXML private ComboBox<District> cbDistrict;
    @FXML private TextField txtIdCus;
    @FXML private TextField txtNameCus;
    @FXML private TextField txtLastNameCus;
    @FXML private DatePicker dpBirthdayCus;
    @FXML private TextField txtAddressCus;
    @FXML private TextField txtCardCus;
    @FXML private TextField txtPhoneCus;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        AllComboboxService s = new AllComboboxService();
        //this.btAddCus.setVisible(false);
        try {
            this.cbProvince.setItems(FXCollections.observableList(s.getProvince()));
            this.cbDistrict.setItems(FXCollections.observableList(s.getDistrict()));
            this.cbWard.setItems(FXCollections.observableList(s.getWard()));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(QLNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
    public void addCustomer(ActionEvent evt) throws SQLException {
        TextField[] t = new TextField[] {txtIdCus, txtNameCus, txtLastNameCus, txtPhoneCus, txtCardCus, txtAddressCus};
        RadioButton[] r = new RadioButton[] { rd1, rd2, rd3 };
        DatePicker[] d = new DatePicker[]{dpBirthdayCus};
        // luu dia chi
         Random idrandom = new Random(); 
        Address ad = new Address(idrandom.nextInt(100), txtAddressCus.getText(),
                cbWard.getSelectionModel().getSelectedItem().getId()) ;
        s.addAddressCustomer(ad);
        Ward w = new Ward(cbWard.getSelectionModel().getSelectedItem().getId(), cbWard.getSelectionModel().getSelectedItem().getName(), 
        cbDistrict.getSelectionModel().getSelectedItem().getId());
        District Dt = new District(cbDistrict.getSelectionModel().getSelectedItem().getId(), cbDistrict.getSelectionModel().getSelectedItem().getName(),
        cbProvince.getSelectionModel().getSelectedItem().getId());
        Province Pr = new Province(cbProvince.getSelectionModel().getSelectedItem().getId(), cbProvince.getSelectionModel().getSelectedItem().getName());
        
        Customer q = new Customer(
            txtNameCus.getText(),
            txtLastNameCus.getText(),
            ((TextField)dpBirthdayCus.getEditor()).getText(),
            txtPhoneCus.getText(),
            txtCardCus.getText(),
            ad.getId(),
            rd1.isSelected()?1:rd2.isSelected()?2:3
            );
        try{
            s.addCustomer(q);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thêm khách hàng thành công!");
            alert.show();
            }catch(SQLException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Thêm khách hàng không thành công: " + ex.getMessage());
                alert.show();
            }
   }
    
}
