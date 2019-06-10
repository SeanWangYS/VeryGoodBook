/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.model;

import uuu.vgb.entity.Order;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.OrderItem;
import uuu.vgb.entity.PaymentType;
import uuu.vgb.entity.ShippingType;
import uuu.vgb.entity.VGBException;

/**
 *
 * @author Admin
 */
class OrdersDAO {

    private static final String INSERT_ORDER = "INSERT INTO orders "
            + "(id,customer_id,order_date,order_time,payment_type,payment_fee,"
            + "shipping_type,shipping_fee,recipient_name,recipient_email,recipient_phone,recipient_address) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String INSERT_ORDER_ITEM = "INSERT INTO order_items "
            + "(order_id,product_id,color,price,quantity) "
            + "VALUES(?,?,?,?,?)";

    void insert(Order order) throws VGBException {
        if (order == null) {
            throw new IllegalArgumentException("建立訂單時資料不得為mull");
        }

        try (
                Connection connection = RDBConnection.getConnection(); //1.2 建立連線
                PreparedStatement pstmt1 = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS); //3.準備指令pstmt1
                PreparedStatement pstmt2 = connection.prepareStatement(INSERT_ORDER_ITEM); //3.準備指令pstmt2
                ) {
            connection.setAutoCommit(false);
            try {
                //3.1傳入?的值pstmt1
                pstmt1.setInt(1, order.getId()); // 第一個?  會丟進去default值 = 0 (因為先前沒設定過)
                pstmt1.setString(2, order.getMember().getId());
                pstmt1.setString(3, order.getOrderDate().toString());
                pstmt1.setString(4, order.getOrderTime().toString());
                pstmt1.setString(5, order.getPaymentType().name());
                pstmt1.setDouble(6, order.getPaymentType().getFee());
                pstmt1.setString(7, order.getShippingType().name());
                pstmt1.setDouble(8, order.getShippingType().getFee());
                pstmt1.setString(9, order.getRecipientName());
                pstmt1.setString(10, order.getRecipientEmail());
                pstmt1.setString(11, order.getRecipientPhone());
                pstmt1.setString(12, order.getShippingAddress());

                //4.執行指令pstmt1
                pstmt1.executeUpdate(); //這裡執行完，order table中的 id column(PKey) 已經自動給號

                //5.處理rs 
                try (ResultSet rs = pstmt1.getGeneratedKeys()) { //.getGeneratedKeys() 會把 做過.executeUpdate() 有auto-increasement的column值抓出來
                    while (rs.next()) {
                        int id = rs.getInt(1);//這裡從rs把剛剛有自動給值的值讀出來，接著指派給order 的 id 
                        order.setId(id);   //Order entity中 id屬性的設計上  我們不會給他值  因為他的值是由 新增到資料庫時 auto-increasement所產生 
                    }
                }

                //新增OrderItem
                for (OrderItem item : order.getOrderItemSet()) {
                    //3.1傳入pstmt2中?的值
                    pstmt2.setInt(1, order.getId());
                    pstmt2.setInt(2, item.getProduct().getId());
                    pstmt2.setString(3, item.getColor());
                    pstmt2.setDouble(4, item.getPrice());
                    pstmt2.setInt(5, item.getQuantity());

                    //4.執行指令pstmt2
                    pstmt2.executeUpdate();
                }

                connection.commit();
            } catch (Exception ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            throw new VGBException("建立訂單失敗", ex);
        }

    }
    private static final String SELECT_ORDER_BY_CUSTOMER_ID
            = "SELECT id, customer_id, order_date,order_time,payment_type,payment_fee,shipping_type,shipping_fee, "
            + "SUM(price*quantity) as total_amount "
            + "FROM orders LEFT JOIN order_items ON orders.id = order_items.order_id "
            + "WHERE customer_id=? "
            + "GROUP BY orders.id "
            + "ORDER BY order_date desc,order_time desc";

    List<Order> selectOrderByCustomerId(String customerId) throws VGBException {
        List<Order> list = new ArrayList<>();
        try (
                //1.2.建立連線
                Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ORDER_BY_CUSTOMER_ID);//3.準備指令
                ) {
            //3.1傳入?值
            pstmt.setString(1, customerId);

            //4.執行指令
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    //5.處理rs
                    Order order = new Order();
                    order.setId(rs.getInt("id"));

                    Customer c = new Customer();
                    c.setId(rs.getString("customer_id"));
                    order.setMember(c);

                    order.setOrderDate(rs.getString("order_date"));
                    order.setOrderTime(LocalTime.parse(rs.getString("order_time")));

                    String pType = rs.getString("payment_type");
                    order.setPaymentType(PaymentType.valueOf(pType));
                    String shType = rs.getString("shipping_type");
                    order.setShippingType(ShippingType.valueOf(shType));

                    order.setPaymentFee(rs.getDouble("payment_fee"));
                    order.setShippingFee(rs.getDouble("shipping_fee"));
                    order.setTotalAmount(rs.getDouble("total_amount"));

                    list.add(order);
                }

            }

        } catch (SQLException ex) {
            throw new VGBException("查詢客戶訂單發生錯誤", ex);
        }
        return list;
    }

}
