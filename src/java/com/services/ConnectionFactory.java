/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mahmoud
 */
public class ConnectionFactory {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "employee", "employee"); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
