package a.b.c;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 

public class LOAD2 extends HttpServlet{
	static String JBx;
	static String JBy;
	static String JBSx;
	static String JBSy;
	static String gps;
	String a;
	int i;
	  private Connection con = null; //Database objects 
	  //��?�object 
	  private Statement stat = null; 
	  //��?�?�喳?��??�ql��?��游�?��
	  private ResultSet rs = null; 
	  //?��?���?
	  private PreparedStatement pst = null; 
	  //��?�?�喳?��??�ql�粹��?��?��???����?�?��??��??��?��
	  //��??����??��?��???
	private String dropdbSQL = "DROP TABLE User "; 
	  
	  private String createdbSQL = "CREATE TABLE User (" + 
	    "    id     INTEGER " + 
	    "  , name    VARCHAR(20) " + 
	    "  , passwd  VARCHAR(20))"; 
	  
	  private String insertdbSQL = "insert into User(id,gps,recopy) " + 
		      "select ifNULL(max(id),0)+1,?,? FROM User"; 
	  
	  private String selectSQL = "select * from User "; 
	  public static void main(String[] args) 
	  { 
	    //?��?��?��?��?迤撣�?

	  
	  } 
		  //�亥岷�??�� 
		  //�臭誑�?���蝯?����?���?���孵� 
		  public void SelectTable() 
		  { 
		    try 
		    { 
		      stat = con.createStatement(); 
		      rs = stat.executeQuery(selectSQL); 
		      while(rs.next()) 
		      { 
		    	 JBSx =rs.getString("gps");
		    	 JBSx="<div id=dd>"+JBSx+"</div>";
		    	 if(JBx!=null){
		    	  JBx=JBx+JBSx;
	
		    	 }else
		    	 {
		    		 JBx=JBSx;
		    		
		    	 }
		    		 JBSy =rs.getString("recopy");
		    		 JBSy="<div id=ff>"+JBSy+"</div>";
		    		 if(JBy!=null){
		    			  JBy=JBy+JBSy;
	
		    		 }
		    		 else
			    	 {
			    		 JBy=JBSy;
			    	
			    	 }
		      } 
		    } 
		    catch(SQLException e) 
		    { 
		      System.out.println("DropDB Exception :" + e.toString()); 
		    } 
//		    finally 
//		    { 
//		      Close(); 
//		    } 
		  } 
		  //?�?�雿輻�??����??��?��閬����?�bject 
		  //��?��?��?��?imeout���航?��?��Connection poor��?��?�?
		  private void Close() 
		  { 
		    try 
		    { 
		      if(rs!=null) 
		      { 
		        rs.close(); 
		        rs = null; 
		      } 
		      if(stat!=null) 
		      { 
		        stat.close(); 
		        stat = null; 
		      } 
		      if(pst!=null) 
		      { 
		        pst.close(); 
		        pst = null; 
		      } 
		    } 
		    catch(SQLException e) 
		    { 
		      System.out.println("Close Exception :" + e.toString()); 
		    } 
		  } 
	  public LOAD2() 
	  { 
	    try { 
	      Class.forName("com.mysql.jdbc.Driver"); 
	      //?��?�driver 
	      con = DriverManager.getConnection( 
	    	      "jdbc:mysql://hl2dm.synology.me:3306/teamb", 
	    	      "abc",""); 
	      //��connection

	//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
	//localhost�臭?��?�,test��?atabase��
	//useUnicode=true&characterEncoding=Big5?�輻?��?��???��
	      
	    } 
	    catch(ClassNotFoundException e) 
	    { 
	      System.out.println("DriverClassNotFound :"+e.toString()); 
	    }//�?���?���Ｙ�sqlexception 
	    catch(SQLException x) { 
	      System.out.println("Exception :"+x.toString()); 
	    } 
	    
	  }  
    public void doGet(HttpServletRequest rq, HttpServletResponse rp) 
    				throws ServletException, IOException{
    JBx=null;
     JBy=null;
    JBSx=null;
    JBSy=null;
        rq.setCharacterEncoding("UTF-8");
//    	 String gps = rq.getParameter("gps");
      
	    LOAD2 test = new LOAD2(); 
	    test.SelectTable(); 
        HttpSession session = rq.getSession();
//session.invalidate();
//        session.removeAttribute("reportx");
        session.setMaxInactiveInterval(3600);      
        session.setAttribute("Areportx",JBx);
        session.setAttribute("Areporty",JBy);
        rp.sendRedirect(rp.encodeRedirectURL("LOADPOST2"));
    }
    public void doPost(HttpServletRequest rq, HttpServletResponse rp) 
    				throws ServletException, IOException{
        doGet(rq, rp);
    }
}