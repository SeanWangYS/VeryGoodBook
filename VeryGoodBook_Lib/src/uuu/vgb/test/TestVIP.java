package uuu.vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.VGBException;
import uuu.vgb.entity.VIP;

public class TestVIP {
	public static void main(String[] args) {
            try {
                VIP v = new VIP();
                v.setId("A223456781");
                System.out.println(v.getId());
                
                v.setName("林莉莉");
                System.out.println(v.getName());
                
                v.setGender('F');
                System.out.println(v.getGender());
                
                System.out.println(v.getDiscount());
                
                System.out.println(v.getDiscountString());
                
                System.out.println(v);
            } catch (VGBException ex) {
                Logger.getLogger(TestVIP.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}
