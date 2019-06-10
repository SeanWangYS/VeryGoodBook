/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.VGBException;
import uuu.vgb.model.ProductService;

/**
 *
 * @author Admin
 */
public class TestProductServiceSelectProductById {
    public static void main(String[] args) {
        ProductService service = new ProductService();
        try {
            Product p = service.searchProductById("2000");
            System.out.println("p=" + p);
        } catch (VGBException ex) {
            Logger.getLogger(TestProductServiceSelectProductById.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
