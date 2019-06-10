package uuu.vgb.entity;

public class VIP extends Customer{
	private int discount =  10;

        //可以請IDE tools產生
    public VIP() {
    }

    public VIP(String id, String name, String password)throws VGBException {
        super(id, name, password);
    }

    public VIP(String id, String name, String password, String email, char gender)throws VGBException {
        super(id, name, password, email, gender);
    }
        
        

	public int getDiscount() {
		return discount;
	}
	public String getDiscountString() {
		String discountStr = "";
		
		int data = 100 - this.discount;
		
		if (data%10==0) {
			discountStr = data/10 + "折"; //此運算式，data/10 是 int型別，但之後做字串相加，整個運算結果變成String型別，避免了兩邊型別不match的問題
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
    public String toString() {
        return super.toString() 
                + ",\ndiscount=" + discount 
                +" ,discountString="+ this.getDiscountString() ;
    }	
}
