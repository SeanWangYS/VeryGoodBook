/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.Order;
import uuu.vgb.entity.Outlet;
import uuu.vgb.entity.PaymentType;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.ShippingType;
import uuu.vgb.entity.ShoppingCart;
import uuu.vgb.entity.VGBException;
import uuu.vgb.entity.VIP;
import uuu.vgb.model.CustomerService;
import uuu.vgb.model.OrderService;
import uuu.vgb.model.ProductService;

/**
 *
 * @author Admin
 */
public class TestShoppingCart_To_Order_OrderService_Insert {

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
            
            //結帳:
            Order order = new Order();
            order.setMember(cart.getMember());
            order.setPaymentType(PaymentType.ATM);
            order.setPaymentFee(PaymentType.ATM.getFee());
            order.setShippingType(ShippingType.HOME);
            order.setShippingFee(ShippingType.HOME.getFee());
            order.setRecipientName("張三豐");
            order.setRecipientEmail("test001@gmail.com");
            order.setRecipientPhone("0987123456");
            order.setShippingAddress("台北市新生南路100號");
            
            order.add(cart);
            System.out.println("order= "  + order);
            
            //insert to database
            OrderService oService = new OrderService();
            oService.insert(order);
            System.out.println("order= "  + order);

        } catch (VGBException ex) {
            Logger.getLogger(TestShoppingCart_To_Order_OrderService_Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
