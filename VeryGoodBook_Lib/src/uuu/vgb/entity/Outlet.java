package uuu.vgb.entity;


public class Outlet extends Product {
	private int discount;

    public Outlet() {
    }
    
    public Outlet(int id, String name, double unitPrice) {
        super(id, name, unitPrice);
    }

    public Outlet(int id, String name, double unitPrice, int discount) {
        super(id, name, unitPrice);
        this.discount = discount;
    }
        //子類別會跟父類別有一樣的建構式 p9-30

	public int getDiscount() {
		return discount;
	}
	
//	public void setDiscount(int discount) {
//		this.discount = discount;
//	}
	
	public String getDiscountString() {
		String discountStr = "";
		
		int data = 100 - this.discount;
		
		
		if (data%10==0) {
			discountStr = data/10 + "折";
		}else {
			discountStr = data + "折";
		}
		return discountStr;
	}
	
	public void setDiscount(int discount) {
		if(discount>=0 && discount<=100) {
			this.discount = discount;
		}else {
			System.out.println("VIP折購資料不正確: "+ discount);
		}
	}
	
	@Override
	public double getUnitPrice() { //查詢售價 (依照系統功能，選擇在重點功能做override)
		double price = super.getUnitPrice()*(100-discount)/100;
		
		return price;  //演進，unitPrice, getUnitPrice() ,this.getUnitPrice(), super.getUnitPrice()
	}
	
	public double getListPrice() { //查定價
		return super.getUnitPrice();
	}

    @Override
    public String toString() {
        return super.toString() + ",\ndiscount=" + discount +"% off "
        + ", 售價" + this.getUnitPrice() ;
    }
        
        
	
	

}
