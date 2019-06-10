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
public class TestCustomerServiceLogin {
    public static void main(String[] args) {
        try {
            CustomerService service = new CustomerService();
            
            Customer c  = service.login("A123456789","123456");
            System.out.println("c= " + c);
        } catch (VGBException ex) {
            Logger.getLogger(TestCustomerServiceLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
