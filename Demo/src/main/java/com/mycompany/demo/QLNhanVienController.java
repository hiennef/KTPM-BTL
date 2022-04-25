/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Address;
import com.mycompany.pojo.District;
import com.mycompany.pojo.Employee;
import com.mycompany.pojo.ExportDataTbEmployee;
import com.mycompany.pojo.Province;
import com.mycompany.pojo.Store;
import com.mycompany.pojo.UserRole;
import com.mycompany.pojo.Ward;
import com.mycompany.services.AllComboboxService;
import com.mycompany.services.EmployeeService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;






/**
 *
 * @author Administrator
 */
public class QLNhanVienController extends TrangChudemo2Controller {

    private static final EmployeeService s = new EmployeeService();
    @FXML
    private TableView<ExportDataTbEmployee> tableEmployee;
    @FXML
    private ComboBox<Store> cbStore;
    @FXML
    private ComboBox<Province> cbProvince;
    @FXML
    private ComboBox<District> cbDistrict;
    @FXML
    private ComboBox<Ward> cbWard;
    @FXML
    private ComboBox<UserRole> cbUserRole;
    @FXML
    private TextField txtKeyword ;
    @FXML
    private TextField txtID ;
    @FXML
    private TextField txtFirst_Name ;
    @FXML
    private TextField txtLast_Name ;
    @FXML
    private TextField txtPhone_Number ;
    
    @FXML
    private TextField txtRole ;
    @FXML
    private TextField txt_Card ;
    @FXML
    private TextField txt_Address;
    @FXML
    private TextField txt_UserName ;
    @FXML
    private TextField txt_Password ;
    @FXML
    private TextField txt_WorkingHours ;
    @FXML
    private RadioButton rdNam;
    @FXML
    private RadioButton rdNu;
    @FXML
    private RadioButton rdKhac;
    @FXML
    private DatePicker Birthday;
    @FXML
    private Button btSua;
    Address ad = new Address();
    Ward w = new Ward();
    District dt = new District();
    Province pv = new Province();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AllComboboxService t = new AllComboboxService();
        this.btSua.setVisible(false);
        try {
            this.cbStore.setItems(FXCollections.observableList(t.getStore()));
            this.cbProvince.setItems(FXCollections.observableList(t.getProvince()));
            this.cbUserRole.setItems(FXCollections.observableList(t.getUserRole()));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(QLNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadColumns();
        this.loadData(null);
        this.txtKeyword.textProperty().addListener((evt) -> {
            this.loadData(this.txtKeyword.getText());
        });
        this.tableEmployee.setRowFactory(et ->{
            TableRow row = new TableRow();
            row.setOnMouseClicked(r ->{
                ExportDataTbEmployee q = this.tableEmployee.getSelectionModel().getSelectedItem();
                this.txtID.setText(String.valueOf(q.getId()));
                this.txtFirst_Name.setText(q.getFirstName());
                this.txtLast_Name.setText(q.getLastName());
                this.Birthday.setValue(LocalDate.parse(q.getBirthday()));
                this.txt_Card.setText(q.getCardId());
                this.txtPhone_Number.setText(q.getPhoneNumber());
                this.txt_UserName.setText(q.getUsername());
                this.txt_Password.setText(q.getPassword());
                this.txt_WorkingHours.setText(String.valueOf(q.getWorkingHours()));
                this.txt_Address.setText(s.getAddressById(q.getAddressId()).getMoreInfo());
      
                this.cbUserRole.getSelectionModel().select(t.getUserRoleById(q.getUserRoleId()));

                 ad = t.getAddressById(q.getAddressId());
                 w = t.getWardById(ad.getWardId());
                 dt = t.getDistrictById(w.getDistrictId());
                 pv = t.getProvinceById(dt.getProvinceId());
                 
                this.cbWard.getSelectionModel().select(w);
                this.cbDistrict.getSelectionModel().select(dt);
                this.cbProvince.getSelectionModel().select(pv);
                this.cbStore.getSelectionModel().select(s.getStoreById(q.getStoreId()));
                if(q.getGenderId()==1){
                    this.rdNam.setSelected(true);
                    this.rdNu.setSelected(false);
                    this.rdKhac.setSelected(false);}
                else{
                    if(q.getGenderId()==2){
                        this.rdNu.setSelected(true);
                        this.rdNam.setSelected(false);
                        this.rdKhac.setSelected(false);}
                    else{
                        this.rdKhac.setSelected(true);
                        this.rdNam.setSelected(false);
                        this.rdNu.setSelected(false);}
                }
                this.btSua.setVisible(true);
            });
            return row;
        });
        
    }
    //phân loại cbbox theo thành phố, quận, phường HONG UPDATE 24/4
   public void SelectProvince (ActionEvent evt) throws SQLException {
       AllComboboxService t = new AllComboboxService();
       int provinceid = this.cbProvince.getSelectionModel().getSelectedItem().getId();
       this.cbDistrict.setItems(FXCollections.observableList(t.getDistrictByProvinceId(provinceid)));
   }
   public void SelectDistrict (ActionEvent evt) throws SQLException {
       AllComboboxService t = new AllComboboxService();
       int Districtid = this.cbDistrict.getSelectionModel().getSelectedItem().getId();
       this.cbWard.setItems(FXCollections.observableList(t.getWardByDistrictId(Districtid)));
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
        col2.setPrefWidth(100);
        
        TableColumn col3 = new TableColumn("Tên");
        col3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col3.setPrefWidth(50);
        
        TableColumn col4 = new TableColumn("Chi nhánh");
        col4.setCellValueFactory(new PropertyValueFactory<>("storeName"));
        col4.setPrefWidth(220);
        
        TableColumn col5 = new TableColumn("Chức vụ");
        col5.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        col5.setPrefWidth(120);
        
        TableColumn col6 = new TableColumn("Số ngày công");
        col6.setCellValueFactory(new PropertyValueFactory<>("workingHours"));
        col6.setPrefWidth(90);
        
        TableColumn col7 = new TableColumn("Thao tác");
        col7.setCellFactory((p) -> {
            Button btn = new Button("Xóa");
            
            btn.setOnAction((evt) -> {
                Alert confirm =  new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setContentText("Bạn có chắc chắn muốn xóa?");
                confirm.showAndWait().ifPresent(res->{
                    if(res== ButtonType.OK){
                        TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                        ExportDataTbEmployee q = (ExportDataTbEmployee) c.getTableRow().getItem();
                      
                        try {
                            s.deleteEmployee(q.getId());
                            this.tableEmployee.getItems().clear();
                            this.loadData(null);
                            this.reset();
                           
                        } catch (SQLException ex) {
                            Logger.getLogger(QLNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
            
            
        this.tableEmployee.getColumns().addAll( col1, col2, col3, col4, col5, col6, col7);
     
    }
   public void addEmployee(ActionEvent evt) throws SQLException {
        TextField[] t = new TextField[] { txtID, txtFirst_Name, txtLast_Name, txtPhone_Number, txt_Card, txt_Address
                , txt_UserName, txt_Password,txt_WorkingHours  };
        RadioButton[] r = new RadioButton[] { rdNam, rdNu, rdKhac };
        DatePicker[] d = new DatePicker[]{Birthday};
        // luu dia chi
        Random idrandom = new Random(); 
        Address ad = new Address(idrandom.nextInt(100000), txt_Address.getText(),
                cbWard.getSelectionModel().getSelectedItem().getId()) ;
        s.addAddressEmployee(ad);
        Ward w = new Ward(cbWard.getSelectionModel().getSelectedItem().getId(), cbWard.getSelectionModel().getSelectedItem().getName(), 
        cbDistrict.getSelectionModel().getSelectedItem().getId());
        District Dt = new District(cbDistrict.getSelectionModel().getSelectedItem().getId(), cbDistrict.getSelectionModel().getSelectedItem().getName(),
        cbProvince.getSelectionModel().getSelectedItem().getId());
        Province Pr = new Province(cbProvince.getSelectionModel().getSelectedItem().getId(), cbProvince.getSelectionModel().getSelectedItem().getName());
        
        Employee q = new Employee(
        Integer.parseInt(txtID.getText()),
        txtFirst_Name.getText() ,
        txtLast_Name.getText(),
        ((TextField)Birthday.getEditor()).getText(),
        txtPhone_Number.getText(),
        txt_UserName.getText(), txt_Password.getText(),
        Integer.parseInt(txt_WorkingHours.getText()),
        txt_Card.getText(),cbUserRole.getSelectionModel().getSelectedItem().getId(),ad.getId(),
        cbStore.getSelectionModel().getSelectedItem().getId(), 
        rdNam.isSelected()?1:rdNu.isSelected()?2:3
        );
            try{
            s.addEmployee(q);
            this.reset();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thêm nhân viên thành công!");
            alert.show();
            this.loadData(null);
            }catch(SQLException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Thêm nhân viên không thành công: " + ex.getMessage());
                alert.show();
            }
   }
   //UPDATE 22/4
   public void updateEmployeeHandler (ActionEvent event ) throws SQLException{
       ExportDataTbEmployee q = this.tableEmployee.getSelectionModel().getSelectedItem();
       if(q!=null){
            try{
                Connection conn = jdbcUtils.getConn();
                //Lấy dữ liệu cập nhật địa chỉ nhân viên
                     
                int a1 = q.getAddressId();
                String a2=  this.txt_Address.getText();
                int a3 = this.cbWard.getSelectionModel().getSelectedItem().getId();
                Ward w = new Ward(cbWard.getSelectionModel().getSelectedItem().getId(), cbWard.getSelectionModel().getSelectedItem().getName(), 
                cbDistrict.getSelectionModel().getSelectedItem().getId());
                District Dt = new District(cbDistrict.getSelectionModel().getSelectedItem().getId(), cbDistrict.getSelectionModel().getSelectedItem().getName(),
                cbProvince.getSelectionModel().getSelectedItem().getId());
                Province Pr = new Province(cbProvince.getSelectionModel().getSelectedItem().getId(), cbProvince.getSelectionModel().getSelectedItem().getName());
                
                String sql2 = "UPDATE address Set more_info = '"+a2+"', ward_id ='"+a3+"' WHERE id = '"+a1+"'";
                PreparedStatement stm2 = conn.prepareStatement(sql2);
                stm2.execute();
                //Lay dữ liệu cập nhật nhân viên
                String v1 = this.txtID.getText();
                String v2 = this.txtFirst_Name.getText();
                String v3 = this.txtLast_Name.getText();
                String v4 = s.SplitStringToDate(((TextField)this.Birthday.getEditor()).getText());
                String v5 = this.txtPhone_Number.getText();
                String v6 = this.txt_UserName.getText();
                String v7 = this.txt_Password.getText();
                int v8 = Integer.parseInt(this.txt_WorkingHours.getText());
                String v9 = this.txt_Card.getText();
                int v10 = this.cbUserRole.getSelectionModel().getSelectedItem().getId();
                int v11 = q.getAddressId();
                int v12 = this.cbStore.getSelectionModel().getSelectedItem().getId();
                int v13 = this.rdNam.isSelected()?1:rdNu.isSelected()?2:3;
                String sql1 = "UPDATE employee Set first_name ='"+v2+"', last_name = '"+v3+"', birthday= '"+v4+"', phone_number ='"+v5+"',"
                        + "user_name ='"+v6+"', password='"+v7+"', working_hours ='"+v8+"', card_id='"+v9+"', user_role_id='"+v10+"',"
                        + " address_id='"+v11+"', store_id='"+v12+"', gender_id='"+v13+"' WHERE id ='"+v1+"'";
                PreparedStatement stm1 = conn.prepareStatement(sql1);
                
                stm1.execute();    
                this.tableEmployee.getItems().clear();
                this.reset();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Sửa thông tin thành công!");
                alert.show();
                this.loadData(null);
            }catch(SQLException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Sửa thông tin không thành công: " + ex.getMessage());
                alert.show();
            }
       }
   }
     
    public void reset(){
        this.txtID.setText("");
        this.txtFirst_Name.setText("");
        this.txtLast_Name.setText("");
        this.Birthday.setValue(null);
        this.txtPhone_Number.setText("");
        this.txt_UserName.setText("");
        this.txt_Password.setText("");
        this.txt_WorkingHours.setText("");
        this.txt_Card.setText("");
        this.txt_Address.setText("");
        this.cbUserRole.getSelectionModel().select(null);
        this.cbStore.getSelectionModel().select(null);
        this.cbProvince.getSelectionModel().select(null);
        this.cbDistrict.getSelectionModel().select(null);
        this.cbWard.getSelectionModel().select(null);
        this.rdNam.setSelected(false);
        this.rdNu.setSelected(false);
        this.rdKhac.setSelected(false);   
    }  
       
       
     

}