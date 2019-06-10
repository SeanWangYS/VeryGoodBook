/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.VGBException;

/**
 *
 * @author Admin
 */
public class RDBConnection {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/vgb?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8"; //程式與MySQL建立連線時的語系是由connection決定
    private static final String userid = "root";
    private static final String pwd = "1234";

    public static Connection getConnection() throws VGBException {
        try {
            //1.載入Driver
            Class.forName(driver);
            try {
                //2.建立連線
                Connection connection = DriverManager.getConnection(url, userid, pwd);
                return connection;
            } catch (SQLException ex) {
                //Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "建立連線失敗", ex);
                throw new VGBException("建立連線失敗", ex);
            }
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "載入Driver失敗", ex);
            throw new VGBException("載入Driver失敗", ex);
        }
    }
}
