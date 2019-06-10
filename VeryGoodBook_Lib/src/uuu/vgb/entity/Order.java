package uuu.vgb.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Order {

    private int id; //PKey, auto-increment

    private Customer member; //required

    private LocalDate orderDate = LocalDate.now(); //required

    private LocalTime orderTime = LocalTime.now();//required

    private PaymentType paymentType; //required

    private ShippingType shippingType; //required	

    private double paymentFee; //required

    private double shippingFee; //required

    private String paymentNote; //optional

    private String shippingNote; //optional

    private int status = 0; //0-新訂單,1-已付款,2-已入帳,3-已出貨,4-已到店,5-已簽收,6-已完成	 	

    private final Set<OrderItem> orderItemSet = new HashSet<>(); //required // 在這邊先new 是Early binding 設計觀念 // 加final 是為了不讓此Set在方法中又被new一個，然後原本的資料就被蓋掉

    private String recipientName; //required

    private String recipientEmail; //required

    private String recipientPhone; //required

    private String shippingAddress; //required

    private double totalAmount; // derived  這個屬性是被算出來的

    public int getId() {
        return id;
    }
    
    public String getFormatedId() {
        return String.format("VGB%08d",this.getId()); //補充格式方法，之後信用卡會用到
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getMember() {
        return member;
    }

    public void setMember(Customer member) {
        this.member = member;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) { //get information form database then put in this method
        this.orderDate = orderDate;
    }

    public void setOrderDate(String date){ //from database
        if(date!=null){
            this.setOrderDate(LocalDate.parse(date));
        }
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public void setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
    }

    public double getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(double paymentFee) {
        this.paymentFee = paymentFee;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getPaymentNote() {
        return paymentNote;
    }

    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }

    public String getShippingNote() {
        return shippingNote;
    }

    public void setShippingNote(String shippingNote) {
        this.shippingNote = shippingNote;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getTotalAmount() { //derived
        if (this.orderItemSet != null && this.orderItemSet.size() > 0) {
            double sum = 0;
            for (OrderItem item : orderItemSet) {
                sum += item.getPrice() * item.getQuantity();
            }
            return sum;
        } else {
            return totalAmount;
        }
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    //TO DO...集合管理的程式
    //orderItemSet mutators: 2 add method
    public void add(ShoppingCart cart) { // 給controller從畫面將購物車(CartItem, quantity) 加入Order物件(OrderItem)中
        for (CartItem cartItem : cart.getCartItemSet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setColor(cartItem.getColor());
            orderItem.setQuantity(cart.getQuantity(cartItem));
            orderItem.setPrice(cartItem.getProduct().getUnitPrice()); //TO DO VIP折扣
            this.orderItemSet.add(orderItem);
        }
    }

    public void add(OrderItem item) { //給dao從database將訂單明細料讀出並存入OrderItem物件的屬性，然後加入Order物件()中
        this.orderItemSet.add(item);

    }

    //orderItemSet accessors
    public Set<OrderItem> getOrderItemSet() {
        return orderItemSet;
    }

    //override toString
    @Override
    public String toString() {
        return "編號=" + id + ", 訂購人=" + member
                + ",\n 訂購日期=" + orderDate + ", 訂購時間=" + orderTime + ", 付款方式=" + paymentType
                + ", 貨運方式=" + shippingType + ", 付款手續費=" + paymentFee + ", 物流費用=" + shippingFee
                + ", 付款備註=" + paymentNote + ", 物流備註=" + shippingNote + ", 狀態=" + status
                + ",\n 訂單明細" + orderItemSet
                + ",\n 收件人:[ " + recipientName + ", " + recipientEmail + ", " + recipientPhone + ", " + shippingAddress + "]"
                + ",\n 總金額=" + getTotalAmount();
    }

}
