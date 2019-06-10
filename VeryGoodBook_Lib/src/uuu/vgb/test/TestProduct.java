/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import uuu.vgb.entity.Product;

/**
 *
 * @author Admin
 */
public class TestProduct {
    public static void main(String[] args) {
        int i = 1;
        int j = i;
        i = i + 1;
        System.out.println("i = " + i);
        System.out.println("j = " + j);
        
        Product p = new Product(1, "mouse", 200);
        System.out.println("p.id = " + p.getId()); 
        System.out.println("p.name = " + p.getName()); 
        System.out.println("p.unitPrice = " + p.getUnitPrice()); //200.0
        System.out.println("p.stock = " + p.getStock());
        System.out.println("p.description = " + p.getDescription());
        System.out.println("p.photoUrl = " + p.getPhotoUrl());
       
        
        Product p2 = p;  //p.clone();  //利用好玩的clone程式，複製一個新物件出來
      
        
        p.setUnitPrice(p.getUnitPrice() + 200);
        System.out.println("p.unitPrice = " + p.getUnitPrice());
        System.out.println("p2.unitPrice = " + p2.getUnitPrice());
        
        System.out.println(p2);

    }
}
