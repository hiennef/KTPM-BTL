/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conf;

import javafx.scene.control.Alert;

/**
 *
 * @author HIEN
 */
public class Utils {
    public static Alert getBox(String content, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setContentText(content);
        
        return alert;
    }
    
}
