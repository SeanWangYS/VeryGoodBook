/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import uuu.vgb.entity.Customer;
import uuu.vgb.entity.VIP;

/**
 *
 * @author Admin
 */
public class TestGetClass {
    public static void main(String[] args) {
        Object o = new Object();
        Customer c = new Customer();
        VIP v = new VIP();
        //檢查左邊的物件是不是由右邊的class建立起來
        System.out.println(o.getClass() == Object.class);//true //不可以是子孫類別，這裡是類別(當成attribute)跟類別比 //.class 是定義在類別中 的屬性名稱，參照到自己的屬性
        System.out.println(o.getClass() == Customer.class); //false
        System.out.println(o.getClass() == VIP.class); //false

        
//        System.out.println(v.getClass() == Object.class);//無法編譯，用下層型別建立的物件，不可能參考到用上層類別建立的物件
//        System.out.println(v.getClass() == Customer.class); //無法編譯
        System.out.println(v.getClass() == VIP.class); //true
    
        Object o1 = null;
        Customer c1 = null;
        VIP v1 = null;
        System.out.println(o1.getClass() == VIP.class);//執行時期發生錯誤: NullPointerException
        System.out.println(c1.getClass() == VIP.class); //執行時期發生錯誤: NullPointerException
        System.out.println(v1.getClass() == VIP.class); //執行時期發生錯誤: NullPointerException
        
       
                
    
    
    }
    
}
