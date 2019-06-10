/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.vgb.web;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 *
 * @author Admin
 */
@WebFilter(filterName = "CharSetFilter", urlPatterns = {"*.do", "*.jsp"}, initParams = {
    @WebInitParam(name = "charset", value = "UTF-8")})
public class CharSetFilter implements Filter {
    private FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       this.filterConfig = filterConfig;  //設定檔傳來的物件(filterConfig)，存在自己預先宣告的屬性中，之後才能繼續用
       //ps. 區域變數在方法執行完畢後就消失
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String charset = filterConfig.getInitParameter("charset");
        if(charset == null) charset = "UTF-8";

        //前置處理
        request.setCharacterEncoding(charset);
        request.getParameterNames();//與method功能無關，只為了達到鎖住編碼效果
        
        response.setCharacterEncoding(charset);
        response.getWriter();
        
        //交給下一棒
        chain.doFilter(request, response);
                
        //後續處理
        
    }

    @Override
    public void destroy() {
        //內容可寫可不寫
        this.filterConfig = null;
    }
}
