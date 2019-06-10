package uuu.vgb.entity;

import java.util.Objects;

public class OrderItem {
 
	private int orderId; //required, PKey
	private Product product; //required, PKey
	private String color="";//required, PKey
	private int quantity; //required
	private double price; //required

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "訂單明細{" + "訂單編號=" + orderId + ", 訂購產品=" + product + ", 顏色=" + color + ", 數量=" + quantity + ", 交易價格=" + price + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.orderId;
        hash = 79 * hash + Objects.hashCode(this.product);
        hash = 79 * hash + Objects.hashCode(this.color);
        return hash;
    }

    //euqald & hashcode 要加上的原因，大概是跟 之後要被丟到set裡有關，based on 集合管理的需求
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItem other = (OrderItem) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }
	 
    
}
 
