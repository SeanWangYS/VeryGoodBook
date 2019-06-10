/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import uuu.vgb.entity.BloodType;
import uuu.vgb.entity.Customer;

/**
 *
 * @author Admin
 */
public class TestCustomerBloodType {
    public static void main(String[] args) {
        Customer c = new Customer();
        String bType = "AB";
        
        BloodType bloodType = BloodType.valueOf(bType); //將String轉換成BloodType物件
        c.setBloodtype(bloodType);
        System.out.println(c.getBloodtype());
        
        String bType2 = c.getBloodtype().toString();
        System.out.println(bType2);
        
        String bType3 = c.getBloodtype().name();
        System.out.println(bType3);
        
        //列舉型別不能 new 
        //只能換出來
                System.out.println(BloodType.values().length);
        for(int i=0;i<BloodType.values().length;i++){
            System.out.println(BloodType.values()[i]);      
        }
        
    }
    
}
