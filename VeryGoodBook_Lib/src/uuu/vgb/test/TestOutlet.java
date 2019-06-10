package uuu.vgb.test;

import uuu.vgb.entity.Outlet;

public class TestOutlet {

	public static void main(String[] args) {
		Outlet outlet = new Outlet();
		
		outlet.setId(2);
		outlet.setName("鍵盤");
		outlet.setUnitPrice(200);
		outlet.setDiscount(20);
		
		System.out.println(outlet.getId());
		System.out.println(outlet.getName());
		System.out.println(outlet.getUnitPrice());
		System.out.println(outlet.getDiscount());
		System.out.println(outlet.getDiscountString());
                System.out.println(outlet.getClass().getName());  // 可知道outlet當初建立時是用那個類別定義，建立起來的
		System.out.println(outlet);  //
//		double price = outlet.getUnitPrice()*(100-outlet.getDiscount())/100;
//		System.out.println("售價為: " + price);
		
		
	}

}
