/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Admin
 */
public class Customer {

    //列舉型別，用此方式宣告常數，程式中用對應變數描述該常數(M / F)
    public static final char MALE = 'M';
    public static final char FEMALE = 'F';

    private String id; //ROC ID, PKey Required(必要屬性), (Attribute, Field)(from ch3)
    private String name = ""; //Required attribute
    private String password; //Required attribute, 6~20個字長度
    private String email; //Required attribute
    private char gender; //男: M, 女: F, Required attribute
    private LocalDate birthday; //Requried attribute 
    private String phone; //非必要屬性 (看你的商業流程決定，送貨時是否要電話或簡訊通知)
    private String address; //非必要屬性
    private boolean married; //非必要屬性
    private BloodType bloodtype; //O, A, B, AB // 這裡用列舉型別寫 //default是null

    public BloodType getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(BloodType bloodtype) {
        this.bloodtype = bloodtype;
    }

    public Customer() {
    }

    public Customer(String id, String name, String password) throws VGBException {  //在這邊 throws VGBException 是為了物件建立失敗時要，繼續把錯誤往前端拋
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
    }

    public Customer(String id, String name, String password, String email, char gender) throws VGBException {
        //當參數都是同型別String, 之後輸入資訊的順序很重要，
        //有可能資訊輸入錯誤，對不上原來的意義，但因為型別正確仍能compile 
        //只寫空建構式的話，就可以避免這種問題
        //這種錯誤最常出現在公司的程式改版，不同工程師亂亂改

        //190124
        //只有建構式能在建構式中呼叫自己的建構式 ，課本p5-6
//        this.setId(id);
//        this.setName(name);
//        this.setPassword(password);
        this(id, name, password);
        this.setEmail(email);
        this.setGender(gender);
    }

    public void setId(String id) throws VGBException {
        if (checkId(id)) {
            this.id = id;
        } else {
            System.out.println("身分證號不正確!");
            throw new VGBException("身分證號不正確");
        }
    }

    public String getId() {
        return id;
    }

    public int getAge() { //method 又稱作 function ,operation procedual(form ch4)
        if (getBirthday() == null) {
            return 0;
        }
        int birthYear = this.getBirthday().getYear();  //當區域變數故意宣告的跟屬性一樣的時候，用this.抓到屬性
        //System.out.println("birthYear = " + birthYear);
        int thisYear = LocalDate.now().getYear();
        int age = thisYear - birthYear;
        //System.out.println("age = " + age);
        return age;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    //下面這種java註解，叫做文件式註解，可拿來做說明文件，要寫在方法或屬性前面
    /**
     * @param birthday 該birthday參數將指派給birthday屬性 
     */
    public void setBirthday(LocalDate birthday) throws VGBException {  //標準的setter是這個，檢查程式照慣例是寫在這
        if (birthday != null && birthday.isBefore(LocalDate.now())) {
            this.birthday = birthday;
        } else {
            System.out.println("出生日期請給值，且必須早於今天");
            throw new VGBException("出生日期請給值，且必須早於今天");
        }
    }

    public void setBirthday(String date) throws VGBException {  //設計給輸入字串的使用者
        if (date != null) {
            date = date.replace('/', '-');
        }
        try {
            this.setBirthday(date != null ? LocalDate.parse(date) : null); //三元運算子結構   布林運算式  ? 則  : 否則
        } catch (DateTimeParseException ex) {
            System.out.println("客戶生日日期格式不正確");
            throw new VGBException("客戶生日日期格式不正確");
        }
    }

    public void setBirthday(int year, int month, int day) throws VGBException {  //設計給輸入三個int的使用者
        LocalDate date = LocalDate.of(year, month, day);
        this.setBirthday(date);
    }

    private static final String idRegExp = "[A-Z][12][0-9]{8}";  //宣告此static變數，效能會變好，從記憶體角度想

    public boolean checkId(String id) {
        if (id != null && id.matches(idRegExp)) {
            String prefix = id.substring(0, 1);
            String rightside = id.substring(1);

            int leftside;
            switch (prefix) {
                case "B":
                case "N":
                case "Z":
                    leftside = 0;
                    break;
                case "A":
                case "M":
                case "W":
                    leftside = 1;
                    break;
                case "K":
                case "L":
                case "Y":
                    leftside = 2;
                    break;
                case "J":
                case "V":
                case "X":
                    leftside = 3;
                    break;
                case "H":
                case "U":
                    leftside = 4;
                    break;
                case "G":
                case "T":
                    leftside = 5;
                    break;
                case "F":
                case "S":
                    leftside = 6;
                    break;
                case "E":
                case "R":
                    leftside = 7;
                    break;
                case "D":
                case "O":
                case "Q":
                    leftside = 8;
                    break;
                case "C":
                case "I":
                case "P":
                    leftside = 9;
                    break;
                default:
                    leftside = 99999;
                    break;
            }
            int sum = 0;
            for (int i = 0; i <= 7; i++) {
                sum += Integer.parseInt(rightside.substring(i, i + 1)) * (8 - i);
            }
            sum = sum + leftside + Integer.parseInt(rightside.substring(8));
            //System.out.println("sum=:"+sum);
            if (sum % 10 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws VGBException {
        if (name != "") {
            this.name = name;
        } else {
            System.out.println("請輸入正確姓名");
            throw new VGBException("請輸入正確姓名");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws VGBException {
        if (password != null && password.length() >= 6 && password.length() <= 20) {
            this.password = password;
        } else {
            System.out.println("請輸入6~20碼的密碼");
            throw new VGBException("請輸入6~20碼的密碼");
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws VGBException {  
        if(email!=null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            this.email = email;
        }else{
            System.out.println("必須輸入格式正確的客戶Email");
            throw new VGBException("必須輸入格是正確的客戶Email");
        }
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) throws VGBException {
        if (gender == MALE || gender == FEMALE) {
            this.gender = gender;
        } else {
            System.out.println("請輸入正確性別，M = male，F = female");
            throw new VGBException("請輸入正確性別，M = male，F = female");
        }

    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        if (phone != null && phone.matches("[0-9]{10}")) {
            this.phone = phone;
        } else {
            System.out.println("電話號碼格式不符");
        }
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the married
     */
    public boolean isMarried() {
        return married;
    }

    /**
     * @param married the married to set
     */
    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " ,{"
                + "id=" + id
                + ", name=" + name
                + ", password=" + password
                + ", email=" + email
                + ", gender=" + gender
                + ", birthday=" + birthday
                + "Age= " + this.getAge()
                + ",\n phone=" + phone
                + ", address=" + address
                + ", married=" + married + '}';
    }

    @Override
    public boolean equals(Object obj) {  //通常以主鍵值作為equals的條件
        if (obj instanceof Customer) { //這裡用instanceof 是因為考慮到VIP ，同一人可能從Customer 變成VIP，比的是id要相同

            Customer other = (Customer) obj;
            return this.id != null && this.id.equals(other.id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 19;
        hash = hash * (this.id != null ? this.id.hashCode() : 0); //這裡id 是字串，不能直接乘，用特殊技巧，叫出該字串物件底下的hash code
        return hash;//To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
     */
}

//[封裝]information hidding ，把資料檢查的規則隱藏在類別提供的方法(setter)裡面，並修改屬性為private

