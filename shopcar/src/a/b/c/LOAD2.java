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
	  //ï¿½ï¿½?”object 
	  private Statement stat = null; 
	  //ï¿½ç?ï¿?ï¿½å–³?¯é??qlï¿½ç?ï¿½ï¿½æ¸¸ï¿½?ï¿½
	  private ResultSet rs = null; 
	  //?¯î?ï¿½ï¿½ï¿?
	  private PreparedStatement pst = null; 
	  //ï¿½ç?ï¿?ï¿½å–³?¯é??qlï¿½ç²¹ï¿½ï¿½?–ï¿½?®î???ï¿½ï¿½ï¿½ï¿½?³ï…¯?ˆï??²é??œï¿½?µï¿½
	  //ï¿½ï??Šï¿½ï¿½é??Œï¿½?…î???
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
	    //?œç?ï¿½ï¿½?î?ï¿½è?è¿¤æ’£ï¿?

	  
	  } 
		  //ï¿½äº¥å²·é??ªï¿½ 
		  //ï¿½è‡­èª‘ï¿½?Ÿï¿½ï¿½îµ¤î¾¦è¯?ï¿½ï¿½ï?ï¿½ï¿½î¡¼ï¿½?ˆï‹ªï¿½ï¿½å­µï¿½ 
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
		  //?°ï—»?®é›¿è¼»î??°ï—½ï¿½ï¿½î©“æ??ºï¿½?®î¦¶ï¿½é–¬î¼¿ï¿½ï¿½ï¤ï¿½ï¿½?¯bject 
		  //ï¿½è?ï¿½ï¿½?½ï¿½?ºï?imeoutï¿½ï¿½ï¿½èˆª?ï¿½?¶ï¿½Connection poorï¿½ï?ï¿½ç?ï¿?
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
	      //?®é?ï¿½driver 
	      con = DriverManager.getConnection( 
	    	      "jdbc:mysql://hl2dm.synology.me:3306/teamb", 
	    	      "abc",""); 
	      //ï¿½î¡¼ï¿½connection

	//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
	//localhostï¿½è‡­?“ç?î¸ï¿½,testï¿½ç?atabaseï¿½ï¿½
	//useUnicode=true&characterEncoding=Big5?¿è¼»?…ï¿½?”æ???¿½
	      
	    } 
	    catch(ClassNotFoundException e) 
	    { 
	      System.out.println("DriverClassNotFound :"+e.toString()); 
	    }//ï¿½ï£?»ï¿½è³?¿½ï¿½ï¼¹ï¿½sqlexception 
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