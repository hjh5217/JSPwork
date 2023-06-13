package dispatches;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/disp/second")
public class Second extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨테르 유형 설정
		response.setContentType("text/html; charset=utf-8");
		//출력 스트림 객체 생성
		PrintWriter out = response.getWriter();
		//second?name=kim
		String name = request.getParameter("name");
		
		out.println("<h3>dispatch()를 이용한 dispatch 실습입니다.</h3>");
		out.println("<p>이름 "+name+"</p>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
