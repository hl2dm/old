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

public class SAVE2 extends HttpServlet{
	static String JB;
	static String gps;
	String a;
	int i;
	  private Connection con = null; //Database objects 
	  //??é•object 
	  private Statement stat = null; 
	  //?∑Ë?,?≥ÂÖ•‰πãsql?∫Â??¥Â?‰∏?
	  private ResultSet rs = null; 
	  //ÁµêÊ???
	  private PreparedStatement pst = null; 
	  //?∑Ë?,?≥ÂÖ•‰πãsql?∫È??≤‰?Â≠óÁî≥,????≥ÂÖ•ËÆäÊï∏‰πã‰?ÁΩ?
	  //?àÂà©??‰æÜÂ?Ê®ôÁ§∫ 
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
	    //Ê∏¨Á??ãÊòØ?¶Ê≠£Â∏?

	  
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
		  //?•Ë©¢Ë≥áÊ? 
		  //?Ø‰ª•?ãÁ??ûÂÇ≥ÁµêÊ??ÜÂ??ñÂ?Ë≥áÊ??πÂ? 
		  //ÂÆåÊï¥‰ΩøÁî®ÂÆåË??ôÂ∫´Âæ?Ë®òÂ?Ë¶ÅÈ??âÊ??âObject 
		  //?¶Â??®Á?ÂæÖTimeout???ØËÉΩ?ÉÊ?Connection poor?ÑÁ?Ê≥?
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
	  public SAVE2() 
	  { 
	    try { 
	      Class.forName("com.mysql.jdbc.Driver"); 
	      //Ë®ªÂ?driver 
	      con = DriverManager.getConnection( 
	    	      "jdbc:mysql://hl2dm.synology.me:3306/teamb", 
	    	      "abc",""); 
	      //?ñÂ?connection

	//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
	//localhost?Ø‰∏ªÊ©üÂ?,test?Ødatabase??
	//useUnicode=true&characterEncoding=Big5‰ΩøÁî®?ÑÁ∑®Á¢?
	      
	    } 
	    catch(ClassNotFoundException e) 
	    { 
	      System.out.println("DriverClassNotFound :"+e.toString()); 
	    }//?âÂèØ?ΩÊ??¢Á?sqlexception 
	    catch(SQLException x) { 
	      System.out.println("Exception :"+x.toString()); 
	    } 
	    
	  }  
    public void doGet(HttpServletRequest rq, HttpServletResponse rp) 
    				throws ServletException, IOException{
        rq.setCharacterEncoding("UTF-8");
    	 String gpsx = rq.getParameter("gpsx");
    	 String gpsy = rq.getParameter("gpsy");
	    SAVE2 test = new SAVE2(); ; 
	    test.insertTable(gpsx,gpsy );    	
        HttpSession session = rq.getSession();
        session.setMaxInactiveInterval(3600);       
        session.setAttribute("Ashow",gpsx);
        session.setAttribute("Ashoww",gpsy);
        rp.sendRedirect(rp.encodeRedirectURL("SHOW2"));
    }
    public void doPost(HttpServletRequest rq, HttpServletResponse rp) 
    				throws ServletException, IOException{
        doGet(rq, rp);
    }
}