/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.Order;
import uuu.vgb.entity.ShoppingCart;
import uuu.vgb.entity.PaymentType;
import uuu.vgb.entity.ShippingType;
import uuu.vgb.entity.VGBException;
import uuu.vgb.model.OrderService;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/member/check_out.do"})
public class CheckOutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        //1.讀取request中From的Parameter:
        String paymentType = request.getParameter("paymentType");
        String shippingType = request.getParameter("shippingType");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        if (paymentType == null && paymentType.length() == 0) {
            errors.add("請選擇付款方式");
        }
        if (shippingType == null && shippingType.length() == 0) {
            errors.add("請選擇貨運方式");
        }
        if (name == null && name.length() == 0) {
            errors.add("請輸入收件人姓名");
        }
        if (email == null && email.length() == 0) {
            errors.add("請輸入收件人email");
        }
        if (phone == null && phone.length() == 0) {
            errors.add("請輸入收件人電話");
        }
        if (address == null && address.length() == 0) {
            errors.add("請輸入收件地址");
        }
        HttpSession session = request.getSession();
        Customer member = (Customer) session.getAttribute("member");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (member == null || cart == null) {
            response.sendRedirect("cart.jsp");
            return;
        }

        //2.若無誤，則呼叫商業邏輯
        if (errors.isEmpty()) {
            Order order = new Order(); //要準備把取得的資訊包成Order物件
            order.setMember(member);
            try {
                PaymentType pType = PaymentType.valueOf(paymentType);
                order.setPaymentType(pType);
                order.setPaymentFee(pType.getFee());

                ShippingType shType = ShippingType.valueOf(shippingType);
                order.setShippingType(shType);
                order.setShippingFee(shType.getFee());

            } catch (RuntimeException ex) {
                errors.add(ex.toString());
            }
            if (errors.isEmpty()) {
                order.setRecipientName(name);
                order.setRecipientEmail(email);
                order.setRecipientPhone(phone);
                order.setShippingAddress(address);

                order.add(cart); //到這邊已經把購物車資訊以及其他相關訂單資訊包裝進Order物件中 ，但是這裡沒有設定Order物件的 id 屬性
                try {
                    OrderService service = new OrderService(); //這裡才呼叫商業邏輯，並且insert
                    service.insert(order); 
                    
                    session.removeAttribute("cart");  // 這行超重要，否則使用者回上一頁後，可在下單一次
                    //3.1 結帳成功，內部轉交./order.jsp
                    request.setAttribute("order", order);//這裡把order物件存在request裡，是為了轉交畫面之後給order.jsp讀出來
                    request.getRequestDispatcher("order.jsp").forward(request, response);
                    return;
                } catch (VGBException ex) {
                    this.log("結帳時建立訂單失敗", ex);
                    errors.add(ex.getMessage());
                }catch(Exception ex){
                    this.log("結帳時建立訂單發生非預期錯誤", ex);
                    errors.add("結帳時建立訂單發生非預期錯誤" + ex.getMessage());
                }
            }
        }
        //3.2
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/member/check_out.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
