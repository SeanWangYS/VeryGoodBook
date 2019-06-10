/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.entity;

/**
 *
 * @author Admin
 */
public class Product extends Object{
    private int id; // 必要欄位，PKey(surrogate key)，AUTO_INCREMENT
    private String name;
    private double unitPrice; //是售價，也是定價
    private int stock;
    private String description;
    private String photoUrl;
    private String[] colors;
    //public int status = 1; //0:新產品, 1:已上架, -1:已停售
    
    public Product(){
    }

    public Product(int id, String name, double unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() { //查詢售價也是訂價
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the photoUrl
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl the photoUrl to set
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    
    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    @Override
    public void finalize() throws Throwable {  // for garbage collection ，開發人員幾乎用不到
                                                //作為釋放外部資源所用
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product clone() { // 複製屬性，在此寫著好玩用的
        Product p = new Product();
        p.setId(this.id);
        p.setName(this.name);
        p.setUnitPrice(this.unitPrice);
        p.setStock(this.stock);
        return p;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
            + "代號=" + id + ", 名稱=" + name
            + ", 單價=" + unitPrice
            + ", 庫存=" + stock
            + ", 描述=" + description
            + ", 圖檔路徑=" + photoUrl + '}';
    }

    
    /*@Override
    public boolean equals(Object obj) {
        if( obj!=null && this.getClass() == obj.getClass()){  //防止obj是null，要做null檢查
            return this.id == ((Product)obj).id;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 17;  //follow the paper from IBM , we take 質數
        hash = hash * this.id;
        return hash; //To change body of generated methods, choose Tools | Templates.
    }
*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        //if (!(obj instanceof Product)) { //也可改成只要是Product 或其子類別 就可以通過
        if (getClass() != obj.getClass()) { 
            return false;
        }
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    

    
    

    
}
