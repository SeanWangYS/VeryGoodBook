/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.VGBException;

/**
 *
 * @author Admin
 */
public class TestCustomer {
    
    public static void main(String[] args) {
        try {                                    //
            String id = "A127203079";
            Customer c = new Customer("A123456789", "郝冶仁", "123456"); //就像是宣告Customer型別的變數
            
            //c.id = id;
            c.setId(id);
            //c.name = "郝冶仁";
            //c.password = "123456";
            c.setEmail("RichMan.Good.tw@gmail.com");
            //c.birthday = LocalDate.of(2020, 11, 15); //run出來的格式 1999-11-15 是目前全球通用的格式，網站上的標準格式
            c.setBirthday(2009,10, 28);
            
            System.out.println("c.id = " + c.getId());
            System.out.println("c.name = " + c.getName());
            System.out.println("c.password = " + c.getPassword());
            System.out.println("c.birthday = " + c.getBirthday());
            //計算客戶c年齡
            System.out.println("c.age:  " + c.getAge());
            //======================================================
            //計算客戶c2年齡
            Customer c2 = new Customer("abcd123456", "林梅莉","123456");
            //c2.id = "A223456781";
            //c2.name = "林梅莉";
            //c2.password = "123456";
            System.out.println(c2.getId());
            System.out.println(c2.getName());
            System.out.println(c2.getPassword());
            
            //c2.birthday = LocalDate.of(1988, 12, 25);
            //c2.birthday = LocalDate.parse("1988-12-25"); //input 字串的格式要像這樣 (ISO8601)
            c2.setBirthday("1998/11/31");
            System.out.println("c2.birthday = " + c2.getBirthday());
            System.out.println("c2.age:  " + c2.getAge());
            
            System.out.println(c2);
        } catch (VGBException ex) {
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "客戶資料錯誤", ex);
        } catch (Exception ex) {
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "發生非預期錯誤", ex);
        }
        
        

    }
}
