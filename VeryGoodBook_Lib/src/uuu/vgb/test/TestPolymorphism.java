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
import uuu.vgb.entity.VIP;



/**
 *
 * @author Admin
 */
public class TestPolymorphism {
    public static void main(String[] args) {
        try {
            // 所有型別都能指給Object 型別
            //所以這個物件只能當成Object 來用，只能用Object 提供的方法
            //用上層型別宣告，只能用上層型別所定義的方法
            
            Object o = "Hello"; //polymorphism
            System.out.println(o.toString()); //"Hello" //這裡會call 到 String的 toString //效果同 System.out.println(o)
            System.out.println(o.getClass().getName()); //java.lang.String
            //System.out.println(o.length()); //length() (屬於String calss) 不是Object定義的方法
            
            
            o = LocalDate.now(); //polymorphism
            System.out.println(o); // //這裡會call 到 LocalDate的 toString
            System.out.println(o.getClass().getName()); //java.time.LocalDate
            System.out.println(((LocalDate)o).getYear());  // getYear()不是Object定義的
            
            
            o = true; //auto-boxing + polymorphism  //基本型別原本無法建立物件 藉由 auto boxing
            System.out.println(o.toString()); //?
            System.out.println(o.getClass().getName()); //java.lang.Boolean
            //System.out.println(o.booleanValue()); //booleanValue() 不是Object 定義的
            
            o = new Integer(1); //auto-boxing + polymorphism
            System.out.println(o.toString());
            System.out.println(o.getClass().getName()); //java.lang.Integer
            System.out.println(((Integer)o).intValue()); //intValue() 不是Object 定義的，轉型完就可以用
            
            
            Object ol = new Customer("A123456789", "張三豐", "123456"); //polymorphism
            System.out.println(ol.toString());
            System.out.println(ol.getClass().getName()); //java.lang.Customer
            System.out.println(((Customer)ol).getAge()); //getAge() 不是Object 定義的
            
            Customer c = new VIP();
            c.setId("A223456781");
            c.setName("林梅莉");
            c.setEmail("marie@gmail.com");
            //c.setDiscont(15); // setDiscount(15) 不是 Customer定義的  //除非你強迫轉型去用
            System.out.println(c);
            //System.out.println(c.getDisocun); //getDiscount() 不是Customer定義的
        } catch (VGBException ex) {
            Logger.getLogger(TestPolymorphism.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
