package uuu.vgb.test;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.vgb.entity.Customer;
import uuu.vgb.entity.VGBException;

public class TestCustomerBirthday {
	
	public static void main(String[] args) {
            try {
                Customer c = new Customer();
//		c.setBirthday(1990, 10, 10);
//		System.out.println(c.getBirthday());
//		
//		c.setBirthday("1991/11/12");
//		System.out.println(c.getBirthday());
//		
//		c.setBirthday(LocalDate.of(1991, 12, 13));
//		System.out.println(c.getBirthday());
//		
//		c.setBirthday(-1, 12, 30);
//		System.out.println(c.getBirthday());
//		
//		c.setBirthday("2020-12-12");
//		c.setBirthday("abcd-12-12"); //執行時期錯誤 java.time.format.DateTimeParseException
//		c.setBirthday("");  //執行時期錯誤 java.time.format.DateTimeParseException
//		System.out.println(c.getBirthday());

                c.setBirthday((String)null);
                System.out.println(c.getBirthday());
                System.out.println(c.getAge());

                c.setBirthday((LocalDate)null);  //若不轉型，則產生編譯時期錯誤 The method setBirthday(LocalDate) is ambiguous for the type Customer
                System.out.println(c.getBirthday());
                System.out.println(c.getAge());
            } catch (VGBException ex) {
                Logger.getLogger(TestCustomerBirthday.class.getName()).log(Level.SEVERE, "客戶生日資料發生非預期錯誤", ex);
            }
	}

}
