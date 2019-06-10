/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.entity;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class ShoppingCart {

    public static final NumberFormat priceNumberFormat = NumberFormat.getCurrencyInstance();
    public static final NumberFormat amountNumberFormat = NumberFormat.getIntegerInstance();
    private Customer member;
    private Map<CartItem, Integer> cart = new HashMap<>();

    public Customer getMember() {
        return member;
    }

    public void setMember(Customer member) {
        this.member = member;
    }

    // mutator(s):取代setter ，存值方法
    public void addToCart(Product p, int quantity) {
        //實作加入購物車的邏輯
        addToCart(p, "", quantity);
    }

    public void addToCart(Product p, String color, int quantity) {
        if (p == null || quantity <= 0) {
            throw new IllegalArgumentException("加入購物車產品不得為null，數量必須大於0");
        }
        if (color == null) {
            color = "";
        } else {
            //TODO:檢查顏色color有出現在產品p的color陣列中
//            String[] productColorArray = p.getColors();
//            String productColor = null;
//            for (String i : productColorArray) {
//                if (i.equals(color)) {
//                    productColor = color;
//                }
//            }
//            if(productColor==null){
//                throw new IllegalArgumentException("加入購物車產品顏色輸入錯誤");
//            }

        }
        CartItem item = new CartItem();
        item.setProduct(p);
        item.setColor(color);
        Integer oldQuantity = cart.get(item);
        if (oldQuantity == null) {
            oldQuantity = 0;
        }
        cart.put(item, quantity + oldQuantity);
    }

    public void update(CartItem item, int quantity) {
        if (cart.get(item) != null) {
            cart.put(item, quantity);
        }
    }

    public void remove(CartItem item) {
        if (cart.get(item) != null) {
            cart.remove(item);
        }
    }

    // accessor(s) : 取代getter，取值方法
    // 從 Delegate method 來，代理人程式，從集合原來就有的一些方法抄過來 
    public int size() {  //取得cart中有幾項明細
        return cart.size();
    }

    public boolean isEmpty() { //檢查cart是否為空的
        return cart.isEmpty();
    }

    public Integer getQuantity(CartItem key) {  //取得cart中該明細的購買數量 (原名:get)
        return cart.get(key);
    }

    public String getUnitPrice(CartItem key) { //取得cart中有該明細的產品價格
        return priceNumberFormat.format(key.getProduct().getUnitPrice());
    }

    public Set<CartItem> getCartItemSet() { // (原名:keySet)  //取得cart中有購物明細的set
        return cart.keySet();
    }

    public int getTotalQuantity() { //取得總購買數量
        int sum = 0;
        Collection<Integer> quantityValues = cart.values();
        for (Integer quantity : quantityValues) {
            sum += quantity;
        }
        return sum;
    }

    public String getTotalAmount() {  //計算總購買金額
        double sum = 0;
        for (CartItem item : cart.keySet()) {
            double amount = item.getProduct().getUnitPrice() * this.getQuantity(item);
            sum += amount;
        }
//        return sum;
        return amountNumberFormat.format(sum);
    }

    //TO DO: //計算VIP購買總金額
    public String getVIPTotlaAmount() {
        double sum = 0;
        if (this.member instanceof VIP) {
            //TO DO 計算購買金額...
        } else {
            return getTotalAmount();
        }
        return amountNumberFormat.format(sum);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "會員=" + member + ", 購物車內容=" + cart
                + "共" + this.size() + "項"
                + this.getTotalQuantity() + "件，總金額:" + this.getTotalQuantity() + '}';
    }

}
