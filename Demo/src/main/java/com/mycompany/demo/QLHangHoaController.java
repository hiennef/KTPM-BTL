/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Address;
import com.mycompany.pojo.Discount;
import com.mycompany.pojo.District;
import com.mycompany.pojo.ExportDataTbEmployee;
import com.mycompany.pojo.Producer;
import com.mycompany.pojo.Product;
import com.mycompany.pojo.ProductSubtype;
import com.mycompany.pojo.ProductType;
import com.mycompany.pojo.ProductUnit;
import com.mycompany.pojo.Province;
import com.mycompany.pojo.Store;
import com.mycompany.pojo.UserRole;
import com.mycompany.pojo.Ward;
import com.mycompany.services.AllComboboxService;
import com.mycompany.services.ProductService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



/**
 *
 * @author Star
 */
public class QLHangHoaController extends TrangChudemo2Controller{
    private static final ProductService s = new ProductService();
    @FXML
    private TableView<Product> tableproduct;
    @FXML
    private ComboBox<ProductType> cbProductType;
    @FXML
    private ComboBox<ProductSubtype> cbProductSubtype;
    @FXML
    private ComboBox<ProductUnit> cbProductUnit;
    @FXML
    private ComboBox<Producer> cbProducer;
    
    @FXML
    private TextField txtKeyword ;
    @FXML
    private TextField txtID ;
    @FXML
    private TextField txtProductName ;
    @FXML
    private TextField txtPurchasePrice ;
    @FXML
    private TextField txtSalePrice ;
    
    @FXML
    private TextField txtQuantity ;
    @FXML
    private TextField txtDiscount;
    @FXML
    private Button btThemMaGiam;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductService t = new ProductService();
        AllComboboxService al = new AllComboboxService();
        try {
            this.cbProductType.setItems(FXCollections.observableList(t.getProductType()));
            this.cbProductUnit.setItems(FXCollections.observableList(t.getProductUnit()));
            this.cbProducer.setItems(FXCollections.observableList(t.getProducer()));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(QLHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadColumns();
        this.loadData(null);
        this.txtKeyword.textProperty().addListener((evt) -> {
            this.loadData(this.txtKeyword.getText());
        });
        this.tableproduct.setRowFactory(et ->{
            TableRow row = new TableRow();
            row.setOnMouseClicked(r ->{
                Product q = this.tableproduct.getSelectionModel().getSelectedItem();
                this.txtID.setText(String.valueOf(q.getId()));
                this.txtProductName.setText(q.getName());
                this.txtPurchasePrice.setText(String.valueOf(q.getPurchasePrice()));
                this.txtSalePrice.setText(String.valueOf(q.getSalePrice()));
                ProductSubtype ad = new ProductSubtype();
                ProductType w = new ProductType();
//                Product pr = new Product();
//                pr = t.getProductById(q.getId());
                ad = t.getProSubType_ById(q.getSubtypeId());
                w = t.getProType_ById(ad.getTypeId());
                this.cbProductSubtype.getSelectionModel().select(ad);
                this.cbProductType.getSelectionModel().select(w);
                this.cbProductUnit.getSelectionModel().select(t.getProductUnitById(q.getUnitId()));
                this.cbProducer.getSelectionModel().select(t.getProducerById(q.getProducerId()));
                
                
            });
            return row;
        });
       
    }
    public void SelectProductType (ActionEvent evt) throws SQLException {
       ProductService t = new ProductService();
       int ProductTypeID = this.cbProductType.getSelectionModel().getSelectedItem().getId();
       this.cbProductSubtype.setItems(FXCollections.observableList(t.getProSub_ByProTypeId(ProductTypeID)));
   }
    
    private void loadData(String kw) {
        try {
            this.tableproduct.setItems(FXCollections.observableList(s.getProduct(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(QLHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   private void loadColumns() {
        
        TableColumn col1 = new TableColumn("Mã SP");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col1.setPrefWidth(80);
        
        TableColumn col2 = new TableColumn("Tên Sản Phẩm");
        col2.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col2.setPrefWidth(210);
        
        TableColumn col3 = new TableColumn("Giá nhập");
        col3.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        col3.setPrefWidth(80);
        
        TableColumn col4 = new TableColumn("Giá bán");
        col4.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        col4.setPrefWidth(80);
        
        TableColumn col5 = new TableColumn("Nhà cung cấp");
        col5.setCellValueFactory(new PropertyValueFactory<>("producerName"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Loại SP");
        col6.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        col6.setPrefWidth(150);
        TableColumn col7 = new TableColumn("Đơn vị");
        col7.setCellValueFactory(new PropertyValueFactory<>("unitName"));
        col7.setPrefWidth(50);
        
        TableColumn col8 = new TableColumn("Thao tác");
        col8.setCellFactory((p) -> {
            Button btn = new Button("Xóa");
            
            btn.setOnAction((evt) -> {
                Alert confirm =  new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setContentText("Bạn có chắc chắn muốn xóa?");
                confirm.showAndWait().ifPresent(res->{
                    if(res== ButtonType.OK){
                        TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                        Product q = (Product) c.getTableRow().getItem();
                      
                        try {
                            s.deleteProduct(q.getId());
                            this.tableproduct.getItems().clear();
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
        this.tableproduct.getColumns().addAll( col1, col2, col3, col4, col5, col6, col7, col8);
     
    }
   public void addProduct(ActionEvent evt) throws SQLException {
      
        
        Product q = new Product(txtProductName.getText() , Float.parseFloat(txtPurchasePrice.getText()), 
        Float.parseFloat(txtSalePrice.getText()),this.cbProducer.getSelectionModel().getSelectedItem().getId(), this.cbProductSubtype.getSelectionModel().getSelectedItem().getId(),
        this.cbProductUnit.getSelectionModel().getSelectedItem().getId());
        
        
        try{
        s.addProduct(q);
        this.reset();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Thêm sản phẩm mới thành công!");
        alert.show();
        this.loadData(null);
        }catch(SQLException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText( ex.getMessage());
            alert.show();
        }
   }
   public void updateProductHandler (ActionEvent event ) throws SQLException{
        Product q = this.tableproduct.getSelectionModel().getSelectedItem();
        if(q!=null){
             try{
                 Connection conn = jdbcUtils.getConn();
                
                 //Lay dữ liệu cập nhật sản phẩm
                 int v1 = Integer.parseInt(this.txtID.getText());
                 String v2 = this.txtProductName.getText();
                 Float v3 = Float.parseFloat(this.txtPurchasePrice.getText());
                 Float v4 = Float.parseFloat(this.txtSalePrice.getText());
                 int v5 = this.cbProducer.getSelectionModel().getSelectedItem().getId();
                 int v6 = this.cbProductSubtype.getSelectionModel().getSelectedItem().getId();
                 int v7 = this.cbProductUnit.getSelectionModel().getSelectedItem().getId();
                 
                 String sql1 = "UPDATE product Set name ='"+v2+"', purchase_price = '"+v3+"', sale_price= '"+v4+"',"
                         + " producer_id ='"+v5+"', type_id ='"+v6+"', unit_id ='"+v7+"'  WHERE id ='"+v1+"' ";
                 PreparedStatement stm1 = conn.prepareStatement(sql1);

                 stm1.execute();    
                 this.tableproduct.getItems().clear();
                 this.reset();
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setContentText("Sửa thông tin sản phẩm thành công!");
                 alert.show();
                 this.loadData(null);
             }catch(SQLException ex){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setContentText(ex.getMessage());
                 alert.show();
             }
        }
   }
   public void reset(){
        this.txtID.setText("");
        this.txtProductName.setText("");
        this.txtPurchasePrice.setText("");
        this.txtSalePrice.setText("");  
        this.cbProductSubtype.getSelectionModel().select(null);
        this.cbProductType.getSelectionModel().select(null);
        this.cbProductUnit.getSelectionModel().select(null);
        this.cbProducer.getSelectionModel().select(null);
       
          
    }
   //update 26/4
   public void addDiscountHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ThemMaGiam.fxml"));
        Parent root = fxmlLoader.load();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
        
    }
   public void ApdungHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ApDungMa.fxml"));
        Parent root = fxmlLoader.load();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
        
    }
   
    @FXML
    public void Xuatnhaphang(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("XuatNhapHang.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
     
}
