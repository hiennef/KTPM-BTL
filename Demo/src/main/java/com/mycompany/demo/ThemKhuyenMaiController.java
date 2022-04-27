/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.pojo.Discount;
import com.mycompany.pojo.Product;
import com.mycompany.services.DiscountService;
import com.mycompany.services.EmployeeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class ThemKhuyenMaiController extends TrangChudemo2Controller{
     private static final DiscountService s = new DiscountService();
     
    @FXML
    private TableView<Discount> tableDiscount;
    @FXML
    private TextField txtDiscountId ;
    @FXML
    private TextField txt_NameDiscount ;
    @FXML
    private TextField txtpercent ;
    @FXML
    private TextField txtKeyword ;
    @FXML
    private DatePicker start_time ;
    @FXML
    private DatePicker end_time ;
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
            this.tableDiscount.setItems(FXCollections.observableList(s.getTbDiscount(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QLHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   private void loadColumns() {
        
        TableColumn col1 = new TableColumn("Mã");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col1.setPrefWidth(50);
        
        TableColumn col2 = new TableColumn("Tên Khuyến Mãi");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setPrefWidth(100);
        
        TableColumn col3 = new TableColumn("Ngày bắt đầu");
        col3.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        col3.setPrefWidth(80);
        
        TableColumn col4 = new TableColumn("Ngày Kết thúc");
        col4.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        col4.setPrefWidth(80);
        
        TableColumn col5 = new TableColumn("% giảm");
        col5.setCellValueFactory(new PropertyValueFactory<>("reducePercentage"));
        col5.setPrefWidth(70);
        
        
        this.tableDiscount.getColumns().addAll( col1, col2, col3, col4, col5);
     
    }
   public void addDiscount(ActionEvent evt) throws SQLException, IOException {
      
        
        Discount q = new Discount(this.txt_NameDiscount.getText() , Timestamp.valueOf(s.SplitStringToDate(this.start_time.getEditor().getText())), 
                Timestamp.valueOf(s.SplitStringToDate(this.end_time.getEditor().getText())), Float.parseFloat(this.txtpercent.getText()));
        
        
        try{
        s.addDiscount(q);
//        this.reset();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Thêm mã giảm mới thành công!");
        alert.show();
        this.loadData(null);
        }catch(SQLException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Thêm mã không thành công!"+ ex.getMessage());
            alert.show();
        }
   }
   public void BackHandler(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TrangChu.fxml"));
        Parent root = fxmlLoader.load();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
        
    }
}
