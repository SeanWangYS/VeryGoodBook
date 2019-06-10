/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.model;

import java.sql.SQLException;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.VGBException;

/**
 *
 * @author Admin
 */
public class CustomerService {

    private CustomersDAO dao = new CustomersDAO();

    public Customer login(String id, String password) throws VGBException {
        Customer c = dao.selectById(id);
        if (c != null && password != null && password.equals(c.getPassword())) {
            return c;
        } else {
            throw new VGBException("登入失敗，帳號或密碼不正確");
        }
    }

    public void register(Customer c) throws VGBException {
        try {
            dao.insert(c);
        } catch (VGBException ex) {
            if (ex.getCause() instanceof SQLException) {
                SQLException cause = (SQLException) ex.getCause(); //getCause(); 把之前放進VGBException型別內的error物件抓出來
                if (cause.getErrorCode() == 1062) {
                    if (cause.getMessage().indexOf("unix_email") > 0) {
                        throw new VGBException("Email重複註冊", ex);
                    } else {
                        throw new VGBException("主鍵值(身分證號)重複註冊", ex);
                    }
                }
            }
            throw ex;
        }
    }

    public void update(Customer c) throws VGBException {
        dao.update(c);
    }

}
