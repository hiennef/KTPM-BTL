/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Employee;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.Store;
import com.mycompany.pojo.StoreProduct;
import com.mycompany.pojo.TableProductData;
import com.mycompany.services.StoreProductService;
import com.mycompany.services.StoreService;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author HIEN
 */
public class XuatNhapHangController implements Initializable{
    @FXML private ComboBox<Store> cbStore;
    @FXML private TableView<TableProductData> tbvProducts;
    @FXML private TextField txtProductId, txtQuantity, txtTongTien;
    @FXML private TextArea taInfo;
    @FXML private Button btnXoa, btnGhi;
    
    private static final StoreService ss = new StoreService();
    private static final StoreProductService sps = new StoreProductService();
    
    public static List<TableProductData> list = new ArrayList<>();
    public static Employee e = TrangChudemo2Controller.em;
    public static Store store = new Store();
    public static float tongtien;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            khoitao();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void khoitao() throws SQLException{
        loadTableColumn();
        cbStore.setItems(FXCollections.observableList(ss.getStore()));
        cbStore.setValue(ss.getStoreById(e.getStoreId()));
        store = cbStore.getValue();
    }
    
    public void StoreChange(ActionEvent e){
        store = cbStore.getValue();
        System.out.println(store.getName());
    }
    
    public void loadTableColumn(){
        TableColumn col1 = new TableColumn("Mã sản phẩm");
        col1.setCellValueFactory(new PropertyValueFactory("productId"));
        col1.setPrefWidth(100);
        
        TableColumn col2 = new TableColumn("Tên sản phẩm");
        col2.setCellValueFactory(new PropertyValueFactory("productName"));
        col2.setPrefWidth(200);
        
        TableColumn col3 = new TableColumn("Số lượng");
        col3.setCellValueFactory(new PropertyValueFactory("quantity"));
        col3.setPrefWidth(100);
        
        TableColumn col4 = new TableColumn("Đơn giá");
        col4.setCellValueFactory(new PropertyValueFactory("productPurchaseP"));
        col4.setPrefWidth(100);
        
        TableColumn col5 = new TableColumn("Thành tiền");
        col5.setCellValueFactory(new PropertyValueFactory("productTotalP"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Thao tác");
        col6.setCellFactory((p) -> {
            Button btn = new Button("Xóa");
            
            btn.setOnAction((evt) -> {
                Alert confirm =  new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setContentText("Bạn có chắc chắn muốn xóa?");
                confirm.showAndWait().ifPresent(res->{
                    if(res== ButtonType.OK){
                        TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                        TableProductData q = (TableProductData) c.getTableRow().getItem();
                        if(sps.deleteTableProductData(q, list)){
                            tongtien = sps.tinhTongTien(tongtien, list);
                            txtTongTien.setText(String.valueOf(tongtien));
                            tbvProducts.refresh();
                        }
                        else{
                            Utils.getBox("Xóa không thành công", Alert.AlertType.ERROR).show();
                        }
                    }
                });
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });  
        
        tbvProducts.getColumns().addAll(col1, col2, col3, col4, col5, col6);
    }
    
    public void addProductData(String productId, String quantity){
        try{
            sps.addTableProductData(Integer.parseInt(productId), Double.parseDouble(quantity), list);
            txtProductId.setText("");
            txtQuantity.setText("");
            txtProductId.requestFocus();
        }
        catch (Exception ex){
            Utils.getBox(ex.getMessage(), Alert.AlertType.ERROR).show();
        }
    }
    
    public void addProductDataa(ActionEvent e){
        try{
            addProductData(txtProductId.getText(), txtQuantity.getText());
            this.tbvProducts.setItems(FXCollections.observableList(list));
            tongtien = sps.tinhTongTien(tongtien, list);
            txtTongTien.setText(String.valueOf(tongtien));
            this.tbvProducts.refresh();
        }
        catch(Exception ex){
            Utils.getBox("Thêm sản phẩm thất bại", Alert.AlertType.ERROR).show();
        }
    }
    
    public void Xoa(ActionEvent e){
        reset();
        
    }
    
    public void Ghi(ActionEvent e){
        if(list.size()>=1){
            sps.addImportBill(Timestamp.from(Instant.now()), store.getId(), list);
            reset();
        }
        else{
            Utils.getBox("Vui lòng thêm sản phẩm!", Alert.AlertType.NONE);
        }
    }
    
    public void reset(){
        list.clear();
        this.tbvProducts.setItems(FXCollections.observableList(list));
        tongtien = sps.tinhTongTien(tongtien, list);
        txtTongTien.setText(String.valueOf(tongtien));
        this.tbvProducts.refresh();
        txtProductId.setText("");
        txtQuantity.setText("");
    }
    
    
}
