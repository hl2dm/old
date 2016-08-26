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

public class SAVE extends HttpServlet{
	static String JB;
	static String gps;
	String a;
	int i;
	  private Connection con = null; //Database objects 
	  //??��object 
	  private Statement stat = null; 
	  //?��?,?�入之sql?��??��?�?
	  private ResultSet rs = null; 
	  //結�???
	  private PreparedStatement pst = null; 
	  //?��?,?�入之sql?��??��?字申,????�入變數之�?�?
	  //?�利??來�?標示 
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
	    //測�??�是?�正�?

	  
	  } 
	  public void insertTable( String name,String passwd) 
	  { 
	    try 
	    { 
	      pst = con.prepareStatement(insertdbSQL); 
	      
	      pst.setString(1, name); 
	      pst.setString(2, passwd);
	      pst.executeUpdate(); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("InsertDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  }  
		  //?�詢資�? 
		  //?�以?��??�傳結�??��??��?資�??��? 
		  //完整使用完�??�庫�?記�?要�??��??�Object 
		  //?��??��?待Timeout???�能?��?Connection poor?��?�?
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
	  public SAVE() 
	  { 
	    try { 
	      Class.forName("com.mysql.jdbc.Driver"); 
	      //註�?driver 
	      con = DriverManager.getConnection( 
	    	      "jdbc:mysql://hl2dm.synology.me:3306/teama", 
	    	      "abc",""); 
	      //?��?connection

	//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
	//localhost?�主機�?,test?�database??
	//useUnicode=true&characterEncoding=Big5使用?�編�?
	      
	    } 
	    catch(ClassNotFoundException e) 
	    { 
	      System.out.println("DriverClassNotFound :"+e.toString()); 
	    }//?�可?��??��?sqlexception 
	    catch(SQLException x) { 
	      System.out.println("Exception :"+x.toString()); 
	    } 
	    
	  }  
    public void doGet(HttpServletRequest rq, HttpServletResponse rp) 
    				throws ServletException, IOException{
        rq.setCharacterEncoding("UTF-8");
    	 String gpsx = rq.getParameter("Agpsx");
    	 String gpsy = rq.getParameter("Agpsy");
	    SAVE test = new SAVE(); ; 
	    test.insertTable(gpsx,gpsy );    	
        HttpSession session = rq.getSession();
        session.setMaxInactiveInterval(3600);       
        session.setAttribute("show",gpsx);
        session.setAttribute("showw",gpsy);
        rp.sendRedirect(rp.encodeRedirectURL("SHOW"));
    }
    public void doPost(HttpServletRequest rq, HttpServletResponse rp) 
    				throws ServletException, IOException{
        doGet(rq, rp);
    }
}