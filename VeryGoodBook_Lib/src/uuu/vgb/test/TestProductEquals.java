/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import uuu.vgb.entity.Outlet;
import uuu.vgb.entity.Product;

/**
 *
 * @author Admin
 */
public class TestProductEquals {
    public static void main(String[] args) {
        Product p1 = new Product(1, "mouse", 200);
        Product p2 = new Product(1, "mouse", 200);
        System.out.println(p1 == p2); //false
        System.out.println(p1.equals(p2)); //ture
        
        Outlet outlet = new Outlet(1, "mouse", 200);
        outlet.setDiscount(50);
        System.out.println(outlet == p1);
        System.out.println(outlet.equals(p1)); //兩者是不同class，已經不用比內容了
        
        
        
        
        //equals 程式內 有用到getClass ，兩者的class要一樣才相等
        
        
        
        
        
        
    }
    
}
