/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Address;
import com.mycompany.pojo.District;
import com.mycompany.pojo.Employee;
import com.mycompany.pojo.Province;
import com.mycompany.pojo.Ward;
import com.mycompany.services.EmployeeService;
import com.mycompany.services.StoreService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author Vi
 */
public class TaiKhoanController extends TrangChudemo2Controller{
    EmployeeService es = new EmployeeService();
    StoreService ss = new StoreService();
    Employee employ = TrangChudemo2Controller.em;
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
        
        txtId.setText(String.valueOf(employ.getId()));
        txtName.setText(employ.getFirstName());
        txtLastName.setText(employ.getLastName());
        dpBirthday.setValue(LocalDate.parse(employ.getBirthday()));
        txtGender.setText(es.getGenderById(employ.getGenderId()).toString());
        
        
        txtAddress.setText(String.valueOf(getAddressById(employ.getAddressId()).getMoreInfo())+", Phường "
                +String.valueOf(getWardById(getAddressById(employ.getAddressId()).getWardId()).getName())+", Quận "
                +String.valueOf(getDistrictById(getWardById(getAddressById(employ.getAddressId()).getWardId()).getDistrictId()).getName()) );
        txtCard.setText(employ.getCardId());
        txtPhone.setText(employ.getPhoneNumber());
        txtStore.setText(ss.getStoreById(employ.getStoreId()).toString());
    }
    
    
    public Address getAddressById(int id){
        Address p = new Address();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from address WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setMoreInfo(rs.getString("more_info"));
                p.setWardId(rs.getInt("ward_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    
    public Ward getWardById(int id){
        Ward p = new Ward();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from ward WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDistrictId(rs.getInt("district_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return p;
    }
    
    public District getDistrictById(int id){
        District p = new District();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from district WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setProvinceId(rs.getInt("province_id"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return p;
    }
    
    public Province getProvinceById(int id){
        Province p = new Province();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * from province WHERE id LIKE ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return p;
    }
    
}
