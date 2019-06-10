package uuu.vgb.test;

import uuu.vgb.entity.Product;
import uuu.vgb.model.ProductService;

public class TestProduct_TestByValue {

	public static void main(String[] args) {
		Product p = new Product(1,"mouse",200);  // 如果只有建立 new Product(1,"mouse",200)  compile會過，代表建立物件，但沒有指向，一建立就變垃圾
		System.out.println("p.getId():" + p.getId());
		System.out.println("p.getName():" + p.getName());
		System.out.println("p.getUnitPrice():" + p.getUnitPrice());
		
//		p.setUnitPrice(p.getUnitPrice() + 50);
//		System.out.println("p.getUnitPrice():" + p.getUnitPrice());
		
		ProductService service = new ProductService();
		service.addPrice(p.getUnitPrice());
		System.out.println("p.getUnitPrice():" + p.getUnitPrice()); //200
		
		service.addPrice(p);
		System.out.println("p.getUnitPrice():" + p.getUnitPrice()); //250

	}

}
