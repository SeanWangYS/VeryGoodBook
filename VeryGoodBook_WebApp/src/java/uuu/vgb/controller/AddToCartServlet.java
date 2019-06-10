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
import uuu.vgb.entity.Product;
import uuu.vgb.entity.ShoppingCart;
import uuu.vgb.entity.VGBException;
import uuu.vgb.model.ProductService;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add_cart.do"})
public class AddToCartServlet extends HttpServlet {

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
        //1.取得request中的Form Data: productId, color, quantity, 並檢查
        String productId = request.getParameter("productId");
        String color = request.getParameter("color");
        String quantity = request.getParameter("quantity");
        if (productId == null || !productId.matches("\\d+")) {
            errors.add("加入購物車產品代碼不正確");
        }
        if (quantity == null || !quantity.matches("\\d+")) {
            errors.add("加入購物車時數量不正確: " + quantity);
        }

        //2.若無誤，呼叫商業邏輯
        if (errors.isEmpty()) {
            ProductService service = new ProductService();
            try {
                Product p = service.searchProductById(productId);//MySQL 搜尋欄位的值的型別不是varchat時，也可以在java用String搜尋
                if (p != null) {
                    HttpSession session = request.getSession();
                    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                    if (cart == null) {
                        cart = new ShoppingCart();
                        session.setAttribute("cart", cart);  //[問題]
                    }
                    if (p.getColors() == null || p.getColors().length == 0) {
                        cart.addToCart(p, Integer.parseInt(quantity));
                    } else if (p.getColors()!=null && p.getColors().length>0 && color!=null && color.length()>0) {
                        cart.addToCart(p, color, Integer.parseInt(quantity));
                    }
                    
                }
            } catch (VGBException ex) {
                this.log("加入購物車發生錯誤", ex); //這是web server提供log功能，會在伺服器留下日誌紀錄
                errors.add("加入購物車發生錯誤" + ex.getMessage());
            } catch (Exception ex) {
                this.log("加入購物車發生非預期錯誤", ex); //這是web server提供log功能，會在伺服器留下日誌紀錄
                errors.add("加入購物車發生非預期錯誤" + ex);
            }
        }
        if(errors.size()>0){
            this.log(errors.toString());
        }
        
        //3.切換畫面(同步請求:redirect to /vgb/member/cart.jsp | 非同步請求:forword to /cart_total_quantity.jsp)
        //response.sendRedirect(request.getContextPath() + "/member/cart.jsp");
        //request.getRequestDispatcher("/cart_total_quantity.jsp").forward(request, response);
        
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
        //3.切換畫面(同步GET請求:redirect to /vgb/products_list.jsp | 非同步請求:forward to /cart_total_quantity.jsp)
        response.sendRedirect("products_list.jsp");
        //request.getRequestDispatcher("/cart_total_quantity.jsp").forward(request, response);
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
        //3.切換畫面(同步GET請求:redirect to /vgb/member/cart.jsp | 非同步POST請求:forward to /cart_total_quantity.jsp)
        //response.sendRedirect(request.getContextPath() + "/member/cart.jsp");
        request.getRequestDispatcher("/cart_total_quantity.jsp").forward(request, response);
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
