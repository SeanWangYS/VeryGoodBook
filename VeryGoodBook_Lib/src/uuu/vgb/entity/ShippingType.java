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
public enum ShippingType {
    SHOP("門市取貨",0),HOME("送貨到府",100),STORE("超商取貨",65); // 在建立的時候直接給值
    
    private final String description;
    private final double fee;
    
    private ShippingType(String description, double fee) { //final 要在建構式給值
        this.description = description;
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return description + (fee>0?"-" + fee + "元":"");
    }
    
    
    
}
