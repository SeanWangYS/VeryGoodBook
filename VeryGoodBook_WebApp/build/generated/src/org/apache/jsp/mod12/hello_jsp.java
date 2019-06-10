package org.apache.jsp.mod12;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;

public final class hello_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    private String welcome = "歡迎";

    public void jspInit() {
        String welcome = this.getInitParameter("welcome");
        if (welcome != null) {
        this.welcome= welcome;
        }

    }


  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Hello JSP</title>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <style>\n");
      out.write("            /* 這是CSS註解*/\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!--這是html註解-->\n");
      out.write("        <script>\n");
      out.write("            /*這是js註解*/\n");
      out.write("        </script>\n");
      out.write("        <script src=\"hello.js\" charset=\"BIG5\">/*charset 是跟瀏覽器說要用什麼編碼方式讀取js檔案(這個js檔案適用BIG5編碼)*/</script>\n");
      out.write("        <div>");
      out.print( welcome);
      out.write("，Served at: \n");
      out.write("            ");

                //這是java註解
                /*這是java註解*/
                out.print(request.getContextPath());
            
      out.write("\n");
      out.write("            <div>\n");
      out.write("                <mark>jsp sciptlet語法</mark>示範:\n");
      out.write("                    ");
 //  示範課本範例CH12_20
                        final int i = 20; //這裡宣告的是區域變數，他會在_jspService方法中，若要另外宣告屬性或方法，則要用另個jsp語法
                        if (i > 10) {
                    
      out.write("\n");
      out.write("                It is a big number\n");
      out.write("                ");
} else {
      out.write("\n");
      out.write("                It is a small number\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <p>\n");
      out.write("                用<mark>jsp expression語法</mark>輸出後端伺服器的時間:");
      out.print( new Date());
      out.write("\n");
      out.write("            </p>\n");
      out.write("            <hr>\n");
      out.write("            <h1>jsp隱含變數(Implicit Variables)</h1>\n");
      out.write("            <h3>request</h3>\n");
      out.write("            <p>method type: ");
      out.print( request.getMethod());
      out.write("</p>\n");
      out.write("            <p>url: ");
      out.print( request.getRequestURL());
      out.write("</p>\n");
      out.write("            <p>uri: ");
      out.print( request.getRequestURI());
      out.write("</p>\n");
      out.write("            <p>protocol: ");
      out.print( request.getProtocol());
      out.write("</p>\n");
      out.write("            <p>server: ");
      out.print( request.getLocalName());
      out.write("</p>\n");
      out.write("            <p>server port: ");
      out.print( request.getLocalPort());
      out.write("</p>\n");
      out.write("            <p>client: ");
      out.print( request.getRemoteHost());
      out.write("</p>\n");
      out.write("            <p>client IP: ");
      out.print( request.getLocalAddr());
      out.write("</p>\n");
      out.write("            <p>client port: ");
      out.print( request.getRemotePort());
      out.write("</p>\n");
      out.write("            <p>context-path: ");
      out.print( request.getContextPath());
      out.write("</p>\n");
      out.write("            <p>user-agent: ");
      out.print( request.getHeader("user-agent"));
      out.write("</p>\n");
      out.write("            <p>使用者輸入的name: ");
      out.print( request.getParameter("name"));
      out.write("</p>\n");
      out.write("            <p>\n");
      out.write("                ");
 request.setCharacterEncoding("UTF-8");
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("            <form method=\"POST\">\n");
      out.write("                <lable>輸入使用者name: </lable><input type=\"text\" name=\"name\" placeholder=\"請輸入名稱\">\n");
      out.write("                <input type=\"submit\" value=\"submit\">\n");
      out.write("            </form>\n");
      out.write("        </p>\n");
      out.write("        <p>\n");
      out.write("            query string: ");
      out.print( request.getQueryString());
      out.write("\n");
      out.write("        </p>\n");
      out.write("        <hr>\n");
      out.write("        <h3>response</h3>\n");
      out.write("        <p>response content type: ");
      out.print( response.getContentType());
      out.write("</p>\n");
      out.write("        <p>response status code: ");
      out.print( response.getStatus());
      out.write("</p>\n");
      out.write("        <hr>\n");
      out.write("        <h3>out</h3>\n");
      out.write("        <p>");
 out.print("Hello");
      out.write("</p>\n");
      out.write("        <p>out buffer size: ");
      out.print(out.getBufferSize());
      out.write("</p>\n");
      out.write("        <hr>\n");
      out.write("        <h3>session</h3>\n");
      out.write("        <p>session id: ");
      out.print( session.getId());
      out.write("</p>\n");
      out.write("        <p>session 建立時間: ");
      out.print( session.getCreationTime() );
      out.write("</p>\n");
      out.write("        <p>session 建立時間(換成Date單位): ");
      out.print( new Date(session.getCreationTime()) );
      out.write("</p>\n");
      out.write("        <p>session timeout: ");
      out.print( session.getMaxInactiveInterval() );
      out.write("</p>  <!--單位是秒-->\n");
      out.write("        <p>最後一次去伺服器的時間: ");
      out.print( new Date(session.getLastAccessedTime() ) );
      out.write("</p>\n");
      out.write("        <hr>\n");
      out.write("        <h3>application</h3>\n");
      out.write("        <p>application REAL PATH(網站實際上執行的位置，並不是你存放程式的地方): ");
      out.print( application.getRealPath("/"));
      out.write("</p>\n");
      out.write("        <hr>\n");
      out.write("        <h3>config(通常用來讀初始化參數)</h3>\n");
      out.write("        <p>ServletName: ");
      out.print( config.getServletName());
      out.write("</p>\n");
      out.write("        <p>ServletName: ");
      out.print( this.getServletName());
      out.write("</p>\n");
      out.write("        <p>init parameter(welcome): ");
      out.print( welcome);
      out.write("</p>\n");
      out.write("        <hr>\n");
      out.write("        <h3>page</h3>\n");
      out.write("        <p>page==this: ");
      out.print( page==this );
      out.write("</p> \n");
      out.write("        <p>this.toString(): ");
      out.print( this.toString());
      out.write("</p>\n");
      out.write("        <p>ServletName: ");
      out.print( ((HttpServlet)page).getServletName());
      out.write("</p>\n");
      out.write("        <hr>\n");
      out.write("        <h3>pageContext(content資訊打成一包)</h3>\n");
      out.write("        <p>session id: ");
      out.print( pageContext.getSession().getId());
      out.write("</p>\n");
      out.write("        <p>session id: ");
      out.print( session.getId());
      out.write("</p>\n");
      out.write("        <hr>\n");
      out.write("        <h3>exception</h3>\n");
      out.write("        <p>exception message(已註解): ");
      out.write("</p>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
