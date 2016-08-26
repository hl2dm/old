package a.b.c;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SHOW2 extends HttpServlet{
    String contentType;
	public void init() throws ServletException {
		ServletContext context = getServletContext();
		contentType = context.getInitParameter("contentType");
	}
    public void doGet(HttpServletRequest rq, HttpServletResponse rp) 
    				throws ServletException, IOException{
        rp.setContentType(contentType);
        PrintWriter out = rp.getWriter();
        HttpSession session = rq.getSession();
        String a = (String)session.getAttribute("Ashow");
        String b = (String)session.getAttribute("Ashoww");
        out.println("<HTML>");
        out.println("<FONT color=\"Red\">"+a+"</FONT> <H3>å·²ç?? å…¥å®Œæ?</H3>");
        out.println("<FONT color=\"Red\">"+b+"</FONT> <H3>å·²ç?? å…¥å®Œæ?</H3>");
        out.println("</HTML>");
    }
    public void doPost(HttpServletRequest rq, HttpServletResponse rp) 
    				throws ServletException, IOException{
        doGet(rq, rp);
    }
}