/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Customer;
import com.mycompany.pojo.Employee;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.TableReceiptDetailData;
import com.mycompany.services.CustomerService;
import com.mycompany.services.ProductService;
import com.mycompany.services.ReceiptDetailService;
import com.mycompany.services.ReceiptService;
import com.mycompany.services.StoreProductService;
import com.mycompany.services.StoreService;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author HIEN
 */
public class ThanhToanController implements Initializable{
    //TableView
    @FXML private TextField txtCustomerId,
            txtCustomerName, txtDiscountId, txtProductId, txtTongTien, 
            txtDaGiam, txtThanhTien, txtTienKhachDua;
    @FXML private Label lbEmployee;
    @FXML private ComboBox cbStore;
    @FXML private DatePicker dpCreatedDate;
    @FXML private TableView<TableReceiptDetailData> tbvReceiptDetail;
    @FXML private Button btnAddProduct, btnDeleteAll, btnSave, btnPayment;
    //Sản phẩm
    @FXML private TextField txtMa, txtTen, txtSoLuong, txtDonGia, 
            txtMaKhuyenMai;
    @FXML private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, 
            btn9, btnDot, btnC, btnCancel, btnDel, btnUpdate;
    
    
    
    private static List<TextField> listTF = new ArrayList<>();
    private Employee employee = TrangChudemo2Controller.em;
    private TableReceiptDetailData product = new TableReceiptDetailData();
    private static final StoreService ss = new StoreService();
    private static final ReceiptService rs = new ReceiptService();
    private static final CustomerService cs = new CustomerService();
    private static final ProductService ps = new ProductService();
    private static final ReceiptDetailService rds = new ReceiptDetailService();
    private static final StoreProductService sps = new StoreProductService();
    private static int count;
    private static boolean dot = false;
    
    
    public static Customer customer = new Customer();
    public static List<TableReceiptDetailData> list = new ArrayList<>();
    public static float tongTien, daGiam, thanhTien, tienKhachDua=0;
    public static int customerId = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        khoiTao();
        loadTableView();
    }
    
    
    public void khoiTao(){
        lbEmployee.setText(employee.getFirstName()+" "+employee.getLastName());
        dpCreatedDate.setValue(LocalDate.now());
        listTF.add(txtMa);
        listTF.add(txtTen);
        listTF.add(txtSoLuong);
        listTF.add(txtDonGia);
        listTF.add(txtMaKhuyenMai);
        
        try {
            cbStore.setValue(ss.getStoreById(employee.getStoreId()));
            cbStore.setItems(FXCollections.observableList(ss.getStore()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void searchCustomer(KeyEvent e){
        if(e.getCode()==KeyCode.ENTER){
            searchCustomer(txtCustomerId.getText());
        }
    }
    
    public void searchCustomer(String id){
        try{
            customer = cs.getCustomerById(Integer.parseInt(id));
            txtCustomerName.setText(customer.getFirstName()+" "+customer.getLastName());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            Utils.getBox("Không tìm thấy", Alert.AlertType.ERROR);
        }
    }
    
    public void loadTableView(){
        TableColumn colSoThuTu = new TableColumn("STT");
        colSoThuTu.setCellValueFactory(new PropertyValueFactory("soThuTu"));
        colSoThuTu.setPrefWidth(50);
        
        TableColumn colProductId = new TableColumn("Mã sản phẩm");
        colProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        colProductId.setPrefWidth(60);
        
        TableColumn colProductName = new TableColumn("Tên sản phẩm");
        colProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        colProductName.setPrefWidth(100);
        
        TableColumn colQuantity = new TableColumn("Số lượng");
        colQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        colQuantity.setPrefWidth(70);
        
        TableColumn colProductPrice = new TableColumn("Đơn giá");
        colProductPrice.setCellValueFactory(new PropertyValueFactory("productPrice"));
        colProductPrice.setPrefWidth(70);
        
        TableColumn colProductDroppedPrice = new TableColumn("Giảm giá");
        colProductDroppedPrice.setCellValueFactory(new PropertyValueFactory("productDroppedPrice"));
        colProductDroppedPrice.setPrefWidth(70);
        
        TableColumn colProductTotalPrice = new TableColumn("Thành tiền");
        colProductTotalPrice.setCellValueFactory(new PropertyValueFactory("productTotalPrice"));
        colProductTotalPrice.setPrefWidth(70);
        
        this.tbvReceiptDetail.getColumns().addAll(colSoThuTu, colProductId, 
                colProductName, colQuantity, colProductPrice, colProductDroppedPrice, colProductTotalPrice);
    }
    
    public void addProduct(ActionEvent e){
        System.out.println(e.toString());
        addProduct(txtProductId.getText());
        this.tbvReceiptDetail.setItems(FXCollections.observableList(list));
        txtProductId.setText("");
        txtProductId.requestFocus();
    }
    
    public void addProduct(KeyEvent e){
        if(e.getCode()==KeyCode.ENTER){
            addProduct(txtProductId.getText());
            this.tbvReceiptDetail.setItems(FXCollections.observableList(list));
            txtProductId.setText("");
        }
    }
    
    public void addProduct(String id){
        Product p = ps.getProductById(Integer.parseInt(id));
        try{
            this.product = rds.addRowProductData(p, list);
            this.tbvReceiptDetail.refresh();
            rds.loadProductDataView(product, listTF);
            updatePriceText();
            count = 0;
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void addText(ActionEvent e){
        Button b = (Button)e.getSource();
        try{
            if(sps.checkProductQuantity(employee.getStoreId(), 
                    product.getProductId(), 
                    Double.parseDouble(txtSoLuong.getText()+b.getText()))){
                count ++;
                if(count <=1){
                    txtSoLuong.setText(b.getText());
                }
                else if (count>1){
                    txtSoLuong.setText(txtSoLuong.getText()+b.getText());
                }
            }
            else{
                Utils.getBox("Số lượng sản phẩm còn: " + 
                        sps.getProductQuantity(employee.getStoreId(), 
                                product.getProductId()), Alert.AlertType.WARNING)
                        .show();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void addDot(ActionEvent e){
        try{
            if(txtSoLuong.getText().indexOf('.')!=-1){
                dot = true;
            }
            if(!dot){
                txtSoLuong.setText(txtSoLuong.getText()+".");
            }
            System.out.println(dot);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteText(ActionEvent e){
        try{
            txtSoLuong.setText(txtSoLuong.getText(0, txtSoLuong.getText().length()-1));
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateProduct(ActionEvent e){
        product = rds.updateRowProductData(product, list, Double.parseDouble(txtSoLuong.getText()));
        this.tbvReceiptDetail.refresh();
        updatePriceText();
        count = 0;
        txtProductId.requestFocus();
    }
    
    public void cancel(ActionEvent e){
        txtProductId.requestFocus();
        rds.loadProductDataView(product, listTF);
        count = 0;
    }
    
    public void remove(ActionEvent e){
        if(rds.removeRowProductData(product, list)){
            tbvReceiptDetail.refresh();
            if(list.isEmpty()){
                txtMa.setText("");
                txtTen.setText("");
                txtSoLuong.setText("");
                txtDonGia.setText("");
                txtMaKhuyenMai.setText("");
                txtTongTien.setText("");
                txtDaGiam.setText("");
                txtThanhTien.setText("");
                product = new TableReceiptDetailData();
            }
            product = list.get(list.size()-1);
            rds.loadProductDataView(product, listTF);
            count = 0;
            updatePriceText();
        }
        else{
            Utils.getBox("Error", Alert.AlertType.ERROR).show();
        }
    }
    
    public void removeAllProductData(ActionEvent e){
        list.removeAll(list);
        updatePriceText();
        tbvReceiptDetail.refresh();
        txtMa.setText("");
        txtTen.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtMaKhuyenMai.setText("");
        txtTongTien.setText("");
        txtDaGiam.setText("");
        txtThanhTien.setText("");
        System.out.println(list.size());
    }
    
   
    public void onSelectedChange(MouseEvent e){
        try{
            product = tbvReceiptDetail.getSelectionModel().getSelectedItem();
            rds.loadProductDataView(product, listTF);
            count = 0;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void updatePriceText(){
        try{
            List<Float> listF = rds.updatePrice(list, tongTien, daGiam, thanhTien);
            tongTien = listF.get(0);
            daGiam = listF.get(1);
            thanhTien = listF.get(2);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        txtTongTien.setText(String.valueOf(tongTien));
        txtDaGiam.setText(String.valueOf(daGiam));
        txtThanhTien.setText(String.valueOf(thanhTien));
    }
    
    public void thanhToan(ActionEvent e) throws SQLException{
        try{
            if(!txtTienKhachDua.getText().isEmpty()){
                tienKhachDua = Float.parseFloat(txtTienKhachDua.getText());
            }
            else
                tienKhachDua = thanhTien;
            if(!txtCustomerId.getText().isEmpty()){
                customerId = Integer.parseInt(txtCustomerId.getText());
            }
            Alert alert = Utils.getBox("Số tiền cần thối: "+ String.valueOf(tienKhachDua- thanhTien), Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Thanh toán");
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get()==ButtonType.OK){
                rs.addReceipt(Timestamp.valueOf(LocalDateTime.now()), thanhTien, customerId, employee.getId(), list);
                Utils.getBox("Thêm hóa đơn thành công", Alert.AlertType.INFORMATION).show();
                reset();
            }
        }
        catch(Exception ex){
            Utils.getBox(ex.getMessage(), Alert.AlertType.ERROR).show();
        }
    }
    
    public void reset(){
        list.removeAll(list);
        product = new TableReceiptDetailData();
        updatePriceText();
        tbvReceiptDetail.refresh();
        txtMa.setText("");
        txtTen.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtMaKhuyenMai.setText("");
        txtTongTien.setText("");
        txtDaGiam.setText("");
        txtThanhTien.setText("");
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtTienKhachDua.setText("");
    }
    
}