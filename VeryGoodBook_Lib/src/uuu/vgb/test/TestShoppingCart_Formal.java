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
import uuu.vgb.model.CustomerService;
import uuu.vgb.model.ProductService;

/**
 *
 * @author Admin
 */
public class TestShoppingCart_Formal {

    public static void main(String[] args) {
        try {
            CustomerService service = new CustomerService();

            Customer c1 = service.login("A223456781", "123456");
            System.out.println("c1= " + c1);
//            VIP vip = new VIP();
//            vip.setId("A223456781");
//            vip.setName("卓小芳");
//            vip.setPassword("123456");
//            vip.setDiscount(15);
//            System.out.println("vip = " + vip);

            ProductService pService = new ProductService();
            Product p1 = pService.searchProductById("1");
            Product p2 = pService.searchProductById("2");
            Product p1_again = pService.searchProductById("1");
            Product p55 = pService.searchProductById("55");

            ShoppingCart cart = new ShoppingCart();
            cart.setMember(c1);
            cart.addToCart(p1, 1);
            System.out.println(cart);

            cart.addToCart(p2, 1);
            System.out.println(cart);

            cart.addToCart(p1_again, 6);
            System.out.println(cart);

            cart.addToCart(p55,"湖水綠",1);
            System.out.println(cart);

            cart.addToCart(p55,"櫻花粉", 1);
            System.out.println(cart);

        } catch (VGBException ex) {
            Logger.getLogger(TestShoppingCart_Formal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
