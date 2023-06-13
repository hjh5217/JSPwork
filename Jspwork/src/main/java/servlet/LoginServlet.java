package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서버측에서 한글 인코딩
		/* response.setCharacterEncoding("utf-8"); */
		request.setCharacterEncoding("utf-8");
		
		//jsp 폼에 입력된 자료 수집
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		System.out.println("아이디: "+ userid);
		System.out.println("비 번: "+ passwd);
		
	}

}
