/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.VGBException;
import uuu.vgb.model.CustomerService;

/**
 *
 * @author Admin
 */
public class TestCustomerServiceRegister {
    public static void main(String[] args) {
        try {
            Customer c = new Customer("A223456718","林友立","123456");
            c.setGender(Customer.FEMALE);
            c.setEmail("test3@gmail.com");
            c.setBirthday("1990-01-02");
            
            CustomerService service = new CustomerService();
            service.register(c);
            
        } catch (VGBException ex) {
            Logger.getLogger(TestCustomerServiceRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
