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
public enum PaymentType {
    //1.宣告常數
    SHOP("門市付款",new ShippingType[]{ShippingType.SHOP}),
    ATM("ATM轉帳", new ShippingType[]{ShippingType.HOME,ShippingType.STORE}),
    HOME("貨到付款", 50,new ShippingType[]{ShippingType.HOME}),
    STORE("超商付款", new ShippingType[]{ShippingType.STORE}),
    CARD("信用卡付款", ShippingType.HOME, ShippingType.STORE);//用可變動參數的觀念輸入參數
    
    //2.宣告要放在常數物件內的屬性
    private final String description; //都只能寫final屬性 // final 屬性要在建構式給初值
    private final double fee;
    private final ShippingType[] shippingTypeArray;
    
    //3.建構式寫完才能在之前宣告的常數內給屬性值
    private PaymentType(String description,ShippingType... shippingTypeArray) { 
//列舉型別建構式不能寫public，列舉型別是由compile幫你建起來，而且也不能被new 
//final 屬性 要在建構式給值
//只能在參數裡把  ShippingType[] 改成 ShippingType... ，可變動參數的意思
//ShippingType[]這個是一維陣列

//        this.description = description;
//        this.fee = 0;
           this(description,0,shippingTypeArray);
    }

    private PaymentType(String description, double fee, ShippingType... shippingTypeArray) { 
        this.description = description;
        this.fee = fee;
        this.shippingTypeArray = shippingTypeArray;
    }
    
    public String getDescription() {
        return description;
    }

    public double getFee() {
        return fee;
    }

    public ShippingType[] getShippingTypeArray() {
        return shippingTypeArray;
    }


    @Override
    public String toString() {
        return description + (fee>0?"-" + fee + "元":"");
    }
    
    

}
