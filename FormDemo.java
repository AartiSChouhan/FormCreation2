

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormDemo
 */
@WebServlet("/FormDemo")
public class FormDemo  extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//response.setContentType("application/pdf");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String age=request.getParameter("age");
        String gender=request.getParameter("gender");
		out.print("Name : "+name+"<br>");
		out.print("Password : "+password+"<br>");
		out.print("Age : "+age+"<br>");
        out.print("Gender : "+gender+"<br>");

		String hobbies[]=request.getParameterValues("hobbies");
		out.print("Hobbies you have selected are : <br>");
		for(String values: hobbies )
		{
			out.print(values +"<br>");
		}
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(DbProperties.Url,DbProperties.Username,DbProperties.Password);
			
		out.print("connected"+"<br>");
		PreparedStatement ps=con.prepareStatement("insert into userDetails values(?,?,?,?,?)");
		ps.setString(1,"name");	
		ps.setString(2,"password");	

		ps.setString(3,"age");
		ps.setString(4,"gender");
		ps.setString(5,"hobbies");	
       int result=ps.executeUpdate();
      out.print(result);
      if(result>0)
{
	System.out.println("Registerd successfully");
}
else{
	System.out.println("Something went wrong");
}
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		}
		
	

	

}
