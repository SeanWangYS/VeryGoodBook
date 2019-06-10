/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.Outlet;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.ShoppingCart;
import uuu.vgb.entity.VGBException;
import uuu.vgb.entity.VIP;

/**
 *
 * @author Admin
 */
public class TestShoppingCart {
    public static void main(String[] args) {
        try {
            Customer c1 = new Customer("A123456789", "蔡狄倫", "123456789");
            VIP vip = new VIP();
            vip.setId("A223456781");
            vip.setName("卓小芳");
            vip.setPassword("123456");
            vip.setDiscount(15);
            System.out.println("vip = " + vip);
            
            Product p = new Product(1, "mouse",200);
            
            
            ShoppingCart cart = new ShoppingCart();
            cart.setMember(vip);
            cart.addToCart(p, 2);
            
            Outlet outlet = new Outlet();
            outlet.setId(2);
            outlet.setName("keyboard");
            outlet.setUnitPrice(200);
            outlet.setDiscount(15);  //沒有設定discount 會??? 一樣會跑啊，因為 discount 在宣告時即使不指派變數，其預設值 = 0
            
            cart.addToCart(outlet, 2); // polymorphic 
        } catch (VGBException ex) {
            Logger.getLogger(TestShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
