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
public class TestCustomerEquals {
    public static void main(String[] args) throws Exception{      //main 方法(測試用) 可以直接丟 Exception 較comliper不要檢查了
        Customer c1 = new Customer("A123456789", "蔡狄倫", "123456789");
        Customer c2 = new Customer("A123456789", "蔡狄倫", "123456789");
        
        System.out.println(c1 == c2); //false ,這兩個變數參考到不同物件
        System.out.println(c1.equals(c2)); //true
        
        VIP vip = new VIP();
        vip.setId("A123456789");
        vip.setName("菜狄倫");
        vip.setPhone("123456789");
        
        System.out.println(vip == c2); //false
        System.out.println(c1.equals(vip)); //true
        
    }
    
    
    
    
    
}
