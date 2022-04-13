/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 *
 * @author Administrator
 */
public class QLNhanVien implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private BorderPane borderpaneThemNV, borderpane;
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    private void QLNhanvien(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("QLNhanvien.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void ThemNV(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("ThemNV.fxml"));
        borderpaneThemNV.setCenter(root);
    }
   
   
    
    
}
