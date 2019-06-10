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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.VGBException;
import uuu.vgb.model.CustomerService;

/**
 *
 * @author Admin
 */
//這裡的路徑寫法是一個寫給伺服器的名稱宣告，一定要用絕對路徑寫法。 
//@annotation是給底層看的語法，必定寫在HttpServlet的相關類別前(想想之前學的@Override)
//@annotation是給底層看的語法，
@WebServlet(name = "LoginServlet", urlPatterns = {"/login.do"}, loadOnStartup = 1) //urlPatterns = {"/login.do"} 這裡寫的路徑要加斜線是規定，跟絕對/相對路徑無關，這裡是urlPatterns，它是pattern阿~~，不是url，所以不是用url的寫法
public class LoginServlet extends HttpServlet {  //HttpServlet ，這個父類別 是由伺服器的library提供，這也是這支java程式要寫在WebApp專案中的原因之一

    public LoginServlet() {
        System.out.println("LoginServlet has been created....");
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
    //HttpServletRequest request 心中請對應到request傳送給網站的那個表
    //HttpServletResponse response  心中請對應到 網站傳回的response那張表
    //是誰把上面兩個參數放進去 processRequest方法，是系統做出來幫你放進去的
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList<>();

        //1.取得request中的parameter
        request.setCharacterEncoding("UTF-8"); //在取出request內資訊之前，要先告訴伺服器要用哪種編碼方式讀取(伺服器的預設編碼方式是ISO8859-1)
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String captcha = request.getParameter("captcha");
        String auto = request.getParameter("auto"); //for Coockie 程式用的屬性

        //檢查id, pwd ,captcha 為必要欄位，在這邊做的是基本的檢查，嚴謹的檢查都寫在後端Lib中
        if (id == null || id.length() == 0) {
            errors.add("必須輸入帳號");
        }
        if (pwd == null || pwd.length() < 6 || pwd.length() > 20) {
            errors.add("必須輸入6~20個字的密碼");
        }
        HttpSession session = request.getSession();
        if (captcha == null || captcha.length() == 0) {
            errors.add("必須輸入驗證碼");
        } else {
            String oldCaptcha = (String)session.getAttribute("captcha");
            if (!captcha.equalsIgnoreCase(oldCaptcha)) {
                errors.add("驗證碼不正確");
            }
        }
        session.removeAttribute("captcha"); //當資訊使用完畢，好習慣就是把他從記憶體移除，不要累積垃圾
        
        if (errors.isEmpty()) {
            //2.呼叫CustomerService的login(執行商業邏輯)
            CustomerService service = new CustomerService();
            try {
                Customer c = service.login(id, pwd);
                
                //login成功則依據使用者選擇來add Cookie / remove Cookie
                Cookie idCookie  = new Cookie("id",id);
                Cookie autoCookie  = new Cookie("auto","checked");
                if(auto==null){//使用者選擇不要記住
                    idCookie.setMaxAge(0);
                    autoCookie.setMaxAge(0);
                }else{//使用者選擇要記住
                    idCookie.setMaxAge(7*24*60*60);
                    autoCookie.setMaxAge(7*24*60*60);
                }
                
                response.addCookie(idCookie);
                response.addCookie(autoCookie);
               
                session.setAttribute("member", c); // 取得會員資料後，也把產生的會員物件存在session內
                
                //3.1 forward登入成功網頁login_ok.jsp (第二版) //不用這個方式
//                request.getRequestDispatcher("/").forward(request, response);
//                return;
                
                //3.1 redirect 首頁
                response.sendRedirect(request.getContextPath()); //request.getContextPath() 交給瀏覽器執行，路徑要用外部路徑 //所以外部轉交可以填其他網域名稱(可跨網域)
                return;

            } catch (VGBException ex) {
                System.out.println("登入失敗:" + ex);
//                errors.add(ex.getMessage());
                if (ex.getCause() != null) {
                    errors.add(ex.getMessage() + ",請聯絡系統管理人員");
                } else {
                    errors.add(ex.getMessage());
                }
            } catch (Exception ex) {
                this.log("登入發生非預期錯誤", ex); //log 還會紀錄日期跟時間，log是給管理人看的
                errors.add("登入發生非預期錯誤" + ex.toString());
            }
        }
        System.out.println("errors:" + errors);

        //3.2產生登入失敗畫面
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
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
    /*
    @Override
    //為了資安考量，不能寫doGet，因為會被攻擊。駭客可以在網址列打上，login.do + GET請求語法，來達到登入效果
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
     */
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
