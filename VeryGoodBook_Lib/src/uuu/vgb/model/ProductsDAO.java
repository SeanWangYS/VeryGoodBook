package uuu.vgb.model;

import java.util.ArrayList;
import java.util.List;

import uuu.vgb.entity.Outlet;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.VGBException;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.vgb.entity.Book;

class ProductsDAO {

    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM products";

    List<Product> selectAllProducts() throws VGBException {
        List<Product> list = new ArrayList<>();

        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PRODUCT);//3.準備指令
                ResultSet rs = pstmt.executeQuery(); //4.執行指令
                ) {
            //5.處裡rs
            while (rs.next()) {
                Product p;
                String className = rs.getString("class_name");
                if (className.equals("Outlet")) {
                    p = new Outlet();
                } else {
                    p = new Product();
                }

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setUnitPrice(rs.getDouble("unit_price"));
                p.setStock(rs.getInt("stock"));
                p.setDescription(rs.getString("description"));
                p.setPhotoUrl(rs.getString("photo_url"));
                if (p instanceof Outlet) {
                    ((Outlet) p).setDiscount(rs.getInt("discount"));
                }

                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new VGBException("查詢全部產品失敗", e);
        }

        return list;
    }

    private static final String SELECT_ALL_NAME = "SELECT * FROM products "
            + " WHERE name LIKE ?";

    List<Product> selectProductsByName(String search) throws VGBException {
        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_NAME);//3.準備指令
                ) {
            pstmt.setString(1, '%' + search + '%');//3.1傳入?的值
            try (
                    ResultSet rs = pstmt.executeQuery(); //4.執行指令
                    ) {
                //5.處裡rs
                while (rs.next()) {
                    Product p;
                    String className = rs.getString("class_name");
                    if ("Outlet".equals(className)) {
                        p = new Outlet();
                    } else {
                        p = new Product();
                    }
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setUnitPrice(rs.getDouble("unit_price"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setPhotoUrl(rs.getString("photo_url"));
                    if (p instanceof Outlet) {
                        ((Outlet) p).setDiscount(rs.getInt("discount"));
                    }
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new VGBException("查詢全部產品失敗", e);
        }
        return list;
    }
   

    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products "
            + "LEFT OUTER JOIN book_details ON products.id = book_details.product_id "
            + "WHERE id=?";
    Product selectProductById(String id) throws VGBException {
        Product p = null;
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_BY_ID); //3.準備指令
                ) {
            //3.1 傳入?的值
            pstmt.setString(1, id);
            try (
                    ResultSet rs = pstmt.executeQuery();//4. 執行指令
                    ) {
                //5.處理rs
                while (rs.next()) {
                    String className = rs.getString("class_name"); //Book
//                    if ("Outlet".equals(className)) {
//                        p = new Outlet();
//                    } else {
//                        p = new Product();
//                    }
                    p = createProductObject(className);
//                    String productClassName = Product.class.getName(); //uuu.vgb.entity.Product
//                    className = productClassName.replace("Product", className); //uuu.vgb.entity.Book                    
//                    try {
//                        p = (Product) Class.forName(className).newInstance();
//                    } catch (Exception ex) {
//                        Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, "讀取產品資料時物件類別名稱不正確", ex);
//                        p = new Product();
//                    }

                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setUnitPrice(rs.getDouble("unit_price"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setPhotoUrl(rs.getString("photo_url"));
                    if (p instanceof Outlet) {
                        ((Outlet) p).setDiscount(rs.getInt("discount"));
                    }
                    if(p instanceof Book){
                        String authors = rs.getString("authers");
                        if(authors!=null && authors.length()>0){
                            String[] authorArray = authors.split(",");
                            ((Book) p).setAuthors(authorArray);
                        }
                        ((Book) p).setPublisher(rs.getString("publisher"));
                        ((Book) p).setLanguage("繁體中文");                        
                        ((Book) p).setPublishedDate(LocalDate.parse(rs.getString("publish_date")));
                        ((Book) p).setIsbn(rs.getString("isbn"));
                    }
                    
                    String colors = rs.getString("colors");
                    if (colors != null && colors.length() > 0) {
                        String[] colorsArray = colors.split(",");
                        p.setColors(colorsArray);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new VGBException("用產品代號查詢產品失敗", ex);
        }
        return p;
    }
    
    private Product createProductObject(String className) {
        Product p;
        String productClassName = Product.class.getName(); //uuu.vgb.entity.Product
        className = productClassName.replace("Product", className); //uuu.vgb.entity.Book                    
        try {
            p = (Product) Class.forName(className).newInstance();
        } catch (Exception ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, "讀取產品資料時物件類別名稱不正確", ex);
            p = new Product();
        }
        return p;

    }
    
    
    public static void main(String[] args) {
        ProductsDAO dao = new ProductsDAO();
        try {
//            List<Product> list = dao.selectAllProducts();
//            System.out.println(list);

            List<Product> list2 = dao.selectProductsByName("色鉛筆");
            System.out.println("list2= " + list2);
            System.out.println("list2.size()= " + list2.size());
        } catch (VGBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
   
}
