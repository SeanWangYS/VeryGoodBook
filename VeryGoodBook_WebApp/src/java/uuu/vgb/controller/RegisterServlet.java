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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.vgb.entity.BloodType;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.VGBException;
import uuu.vgb.model.CustomerService;

/**
 *
 * @author Sean
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register.do"}, loadOnStartup = 1)
public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {
        System.out.println("RegisterServlet has been created....");
    }

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
        //1.取得request中的parameter，建立客戶物件
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("userid");
        String name = request.getParameter("name");
        String pwd1 = request.getParameter("password1");
        String pwd2 = request.getParameter("password2");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String married = request.getParameter("married");
        String bType = request.getParameter("bloodType");
        String captcha = request.getParameter("captcha");

        //檢查 id,name,pwd1,pwd2,gender,email,birthday,captcha為必要欄位
        if (id == null || id.length() == 0) {
            errors.add("必須輸入帳號");
        }
        if (name == null || name.length() == 0) {
            errors.add("必須輸入帳號");
        }
        if (pwd1 == null || pwd1.length() < 6 || pwd1.length() > 20) {
            errors.add("必須輸入6~20個字元的密碼");
        } else if (!pwd1.equals(pwd2)) {
            errors.add("必須輸入6~20個字元的密碼與相同的確認密碼");
        }
        if (gender == null || gender.length() != 1) {
            errors.add("必須輸入性別");
        }
        if (email == null || email.length() == 0) {
            errors.add("必須輸入Email");
        }
        if (birthday == null || birthday.length() == 0) {
            errors.add("必須輸入生日");
        }
        if (captcha == null || captcha.length() == 0) {
            errors.add("必須輸入驗證碼");
        }
        HttpSession session = request.getSession();
        if (captcha == null || captcha.length() == 0) {
            errors.add("必須輸入驗證碼");
        } else {
            String oldCaptcha = (String) session.getAttribute("catpcha");
            if (!oldCaptcha.equalsIgnoreCase(captcha)) {
                errors.add("驗證碼不正確");
            }

        }

        if (errors.isEmpty()) {
            try {
                Customer c = new Customer();
                c.setId(request.getParameter("userid"));
                c.setName(request.getParameter("name"));
                c.setPassword(request.getParameter("password1"));
                c.setGender(request.getParameter("gender").charAt(0));
                c.setEmail(request.getParameter("email"));
                c.setBirthday(request.getParameter("birthday"));
                c.setPhone(request.getParameter("phone"));

//                if (married == null || married.length() == 0) {
//                    c.setMarried(false);
//                } else {
//                    c.setMarried(true);
//                }
                c.setMarried(married != null); //老師的寫法

                if (bType != null && bType.length() > 0) {
                    BloodType bloodType = BloodType.valueOf(bType);
                    c.setBloodtype(bloodType);
                }

                //2.呼叫CustomerService的register(執行商業邏輯)
                CustomerService service = new CustomerService();
                service.register(c);
                
                session.setAttribute("member", c);

                //3.1 產生註冊成功畫面login_ok.jsp(第二版)
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/");
//                dispatcher.forward(request, response);
                
                //TO DO 想想如何註冊成功同時就登入成功
                //3.1 redirect 首頁
                response.sendRedirect(request.getContextPath());
                return;

            } catch (VGBException ex) {
                this.log("會員註冊發生錯誤", ex);
                errors.add(ex.getMessage());
            } catch (Exception ex) {
                this.log("會員註冊發生非預期錯誤", ex);
                errors.add("會員註冊發生非預期錯誤:" + ex);
            }
        }
        //3.2 產生註冊失敗畫面
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);

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
