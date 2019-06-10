/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import uuu.vgb.entity.Customer;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.VIP;

/**
 *
 * @author Admin
 */
public class TestInstanceof {
    public static void main(String[] args) {
        Object o = new Object();
        Object c = new Customer();
        VIP v = new VIP();
        
//        Instanceof 的用途 (Instanceof是二元運算子)
//        是用來檢查左邊的變數參考到的物件(注意要是物件，不是值)是不是右邊類別或其子孫類別建立起來的物件(右邊只要填入類別名稱就好)，也可以看成是檢查兩邊型別是否相等

        
        System.out.println(o instanceof Object); //true
        System.out.println(c instanceof Object); //true
        System.out.println(v instanceof Object); //true
        
        System.out.println(o instanceof Customer); //false
        System.out.println(c instanceof Customer); //true
        System.out.println(v instanceof Customer); //true
        
        System.out.println(o instanceof VIP); //false
        System.out.println(c instanceof VIP); //false
        System.out.println(v instanceof VIP); //true
        
        Object o1 = null; //會造成左邊變數參考到的是值，而不是物件
        Customer c1 = null;
        VIP v1 = null;
        System.out.println(o1 instanceof Object); //false
        System.out.println(c1 instanceof Customer); //false
        System.out.println(v1 instanceof VIP); //false
        //System.out.println(v1 instanceof Product); //compile time error 
        
        
        
    }
    
}
