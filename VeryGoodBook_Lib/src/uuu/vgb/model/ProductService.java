package uuu.vgb.model;

import java.util.List;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.VGBException;

public class ProductService {
    private ProductsDAO dao = new ProductsDAO();  // ProductsDAO 是 package-friendy，這個概念出發，這邊用private修飾

    public List<Product> getAllProducts() throws VGBException {
        List<Product> list = dao.selectAllProducts();
        return list;
    }

    //代理人
    //思考 ProductService & ProductDAO 的關係，這邊可以用代理人程式 
    //最原本DAO程式也是寫在ProductService 商業邏輯裡，後來才把那段搬出去
    public List<Product> searchProductsByName(String search) throws VGBException{ //用insert Code加上 then change the method name and add public
        return dao.selectProductsByName(search);
    }

    public Product searchProductById(String id) throws VGBException{
        return dao.selectProductById(id);
    }
    
    
    
    
    
    //以下兩方法是示範PASS-BY-VALUE用的，不是商業邏輯
    public void addPrice(double price) {
        price = price + 50;
    }
//	public double addPrice(double price) {
//		return price+50;
//	}

    public void addPrice(Product p) {
        p.setUnitPrice(p.getUnitPrice() + 50);
    }

//	public double addPrice(Product p) {
//		return (p.getUnitPrice()+50);
//	}
}
