/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Star
 */
public class AddressService {
    public Address getAddressById(int id) throws SQLException{
        Address s = new Address();
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM address WHERE id LIKE ?");
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                s.setId(rs.getInt("id"));
            }
        }
        return s;
    }
}
