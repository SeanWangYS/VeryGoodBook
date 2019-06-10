/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.BloodType;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.VGBException;
import uuu.vgb.entity.VIP;
import static uuu.vgb.model.RDBConnection.getConnection;

/**
 *
 * @author Admin
 */
/* 下面類別不加上public 是一種設計觀念 package-friendy*/
class CustomersDAO {

//    private static final String driver = "com.mysql.jdbc.Driver";
//    private static final String url = "jdbc:mysql://localhost:3306/vgb?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8";
//    private static final String userid = "root";
//    private static final String pwd = "1234";

    private static final String SELECT_BY_ID
            = "SELECT id,name,password,email,gender,birthday,"
            + "phone,address,married,blood_type,discount,class_name"
            + " FROM customers"
            + " WHERE id=?";

    Customer selectById(String id) throws VGBException {
        Customer c = null; //區域變數使用前須明確給值

        try (
                //1,2.取得連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ID);) {
            //3.1傳入?的值
            pstmt.setString(1, id);

            //4.執行指令
            try (ResultSet rs = pstmt.executeQuery();) {
                //5.處理rs
                while (rs.next()) { //一開始會指在rs 第一筆資料之前的假資料 before first ，最後一筆也是假資料
                    String className = rs.getString("class_name");
                    if ("VIP".equals(className)) {
                        c = new VIP();
                    } else {
                        c = new Customer();
                    }
                    c.setId(rs.getString("id"));
                    c.setName(rs.getString("name"));
                    c.setPassword(rs.getString("password"));
                    c.setEmail(rs.getString("email"));
                    c.setGender(rs.getString("gender").charAt(0));
                    c.setBirthday(rs.getString("birthday"));
                    c.setPhone(rs.getString("phone"));
                    c.setAddress(rs.getString("address"));
                    c.setMarried(rs.getBoolean("married"));
                    
                    String bType = rs.getString("blood_type");
                    if(bType!=null && bType.length()>0){
                    c.setBloodtype(BloodType.valueOf(bType));
                    }
                    
                    if (c instanceof VIP) {
                        ((VIP) c).setDiscount(rs.getInt("discount"));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "無法查詢客戶-建立連線或執行指令失敗", ex);
            throw new VGBException("無法查詢客戶", ex);
        }

        return c;
    }
    private static final String INSERT_CUSTOMER = "INSERT INTO customers"
            + " (id,name,password,email,gender,birthday,"
            + "phone,address,married,blood_type,discount,class_name)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    void insert(Customer c) throws VGBException {
        if (c == null) {
            throw new IllegalArgumentException("新增客戶時Customer物件不得為null"); // throw 跟return一樣，程式到那邊就結束了
        }
        try (
                //1,2.取得連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(INSERT_CUSTOMER);) {
            //3.1傳入?的值
            pstmt.setString(1, c.getId());
            pstmt.setString(2, c.getName());
            pstmt.setString(3, c.getPassword());
            pstmt.setString(4, c.getEmail());
            pstmt.setString(5, String.valueOf(c.getGender()));
            pstmt.setString(6, c.getBirthday() != null ? String.valueOf(c.getBirthday()) : "");
            pstmt.setString(7, c.getPhone());
            pstmt.setString(8, c.getAddress());
            pstmt.setBoolean(9, c.isMarried());
            pstmt.setString(10, c.getBloodtype()==null?null:c.getBloodtype().name()); 
            if (c instanceof VIP) {
                pstmt.setInt(11, ((VIP) c).getDiscount());
            } else {
                pstmt.setInt(11, 0);
            }
            pstmt.setString(12, c.getClass().getSimpleName());

            //4.執行指令
            int rowCount = pstmt.executeUpdate(); //這裡能做的，只是能得到新增的筆數，沒什麼用
            System.out.println("新增客戶成功rowCount= " + rowCount);
        } catch (SQLException ex) {
            throw new VGBException("新增客戶失敗", ex);
        }
    }
private static final String UPDATE_CUSTOMER = "UPDATE customers"
            + " SET name=?,password=?,email=?,gender=?,birthday=?,"
            + "phone=?,address=?,married=?,blood_type=?,discount=?,class_name=?"
            + " WHERE id=?";
    void update(Customer c) throws VGBException {
        if (c == null) {
            throw new IllegalArgumentException("修改客戶時Customer物件不得為null"); 
        }
        try(
            //1.2.取得連線
            Connection connection = RDBConnection.getConnection();
            //3.準備指令
            PreparedStatement pstmt = connection.prepareStatement(UPDATE_CUSTOMER);    
                ) {
            //3.1傳入?的值
            pstmt.setString(12, c.getId());
            pstmt.setString(1, c.getName());
            pstmt.setString(2, c.getPassword());
            pstmt.setString(3, c.getEmail());
            pstmt.setString(4, String.valueOf(c.getGender()));
            pstmt.setString(5, c.getBirthday() != null ? String.valueOf(c.getBirthday()) : "");
            pstmt.setString(6, c.getPhone());
            pstmt.setString(7, c.getAddress());
            pstmt.setBoolean(8, c.isMarried());
            pstmt.setString(9, c.getBloodtype()==null?null:c.getBloodtype().name()); 
            if (c instanceof VIP) {
                pstmt.setInt(10, ((VIP) c).getDiscount());
            } else {
                pstmt.setInt(10, 0);
            }
            pstmt.setString(11, c.getClass().getSimpleName());

            //4.執行指令
            int rowCount = pstmt.executeUpdate(); //這裡能做的，只是能得到新增的筆數，沒什麼用
            System.out.println("修改客戶資訊成功rowCount= " + rowCount);
        } catch (SQLException ex) {
            throw new VGBException("修改客戶資訊客戶失敗", ex);
        }
            
            
            
      
        
    }

}
