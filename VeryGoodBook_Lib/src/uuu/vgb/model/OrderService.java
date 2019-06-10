/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.Order;
import uuu.vgb.entity.VGBException;

/**
 *
 * @author Admin
 */
public class OrderService {

    private OrdersDAO dao = new OrdersDAO();
    

    public void insert(Order order) throws VGBException {
        dao.insert(order);
    }

    public List<Order> searchtOrderByCustomerId(String customerId) throws VGBException {
        return dao.selectOrderByCustomerId(customerId);
    }
    
    

}
