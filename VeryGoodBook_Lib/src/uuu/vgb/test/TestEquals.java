/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import java.time.LocalDate;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.VGBException;

/**
 *
 * @author Admin
 */
public class TestEquals { //目的: 探討比較方式
    public static void main(String[] args) throws VGBException {
        //複習:  == 運算子
        //
        System.out.println(1==2); //false 
        System.out.println(1 != 1.0); //false 
        System.out.println('1' == 1 ); //false  ( 49!=1  )
        //整數型態間有相容性，int 可以跟 double做比較        
        System.out.println(true == false); //false
        //boolean 可跟 boolean做比較        
        
        //當參考型別做比較，用equals
        //參考型別才要做null檢查
        Customer c1 = new Customer("A123456789", "蔡狄倫", "123456789");
        Customer c2 = new Customer("A123456789", "蔡狄倫", "123456789");
        Object o = new Object();
        Product p = new Product(1, "mouse", 200);
        System.out.println(c1 == c2); //false ,這兩個變數參考到不同物件
        System.out.println(c1.equals(c2)); //true
        
        System.out.println(c1 == o); //false (左右兩邊型別相容)
        //System.out.println(c1 == p); //無法編譯，Product 與 Customer 本來就不相容
        
        c2 = c1;
        System.out.println("");
        
        c1.setName("John");
        System.out.println(c2.getName());
        LocalDate tomorrow1 = LocalDate.of(2019,1,25); //static方法 建立物件不用new
        LocalDate tomorrow2 = LocalDate.now().plusDays(1);
        
        System.out.println(tomorrow1 == tomorrow2);
        System.out.println(tomorrow1.equals(tomorrow2));
        
     }
    //equals 實務上就是要知道 前後兩個建出來的物件，內容是否一樣，在設計購物車時會用到
}
