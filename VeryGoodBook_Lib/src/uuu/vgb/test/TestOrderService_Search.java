/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.Order;
import uuu.vgb.entity.VGBException;
import uuu.vgb.model.OrderService;

/**
 *
 * @author Admin
 */
public class TestOrderService_Search {
    public static void main(String[] args) {
       
        try {
            OrderService service = new OrderService();
            List<Order> list = service.searchtOrderByCustomerId("A223456781");
            System.out.println("list= " + list);
        } catch (VGBException ex) {
            Logger.getLogger(TestOrderService_Search.class.getName()).log(Level.SEVERE, null, ex);
        }
              
              
    }
    
    
    
}
