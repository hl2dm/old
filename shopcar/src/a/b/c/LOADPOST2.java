package a.b.c;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LOADPOST2 extends HttpServlet{
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
        String a = (String)session.getAttribute("Areportx");
        String b = (String)session.getAttribute("Areporty");
        out.println(a+b);
    }
    public void doPost(HttpServletRequest rq, HttpServletResponse rp) 
    				throws ServletException, IOException{
        doGet(rq, rp);
    }
}