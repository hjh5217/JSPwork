package filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class LogFilter implements Filter{
   
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
	   System.out.println("실행");
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, ServletException {
         //로그파일의 기록 내용
         String clientAddr = request.getRemoteAddr(); //IP 주소
         System.out.printf("클라이언트 IP 주소: %s %n", clientAddr);
         System.out.println("접근한 URL 주소: "+getURLPath(request));
         
         long start = System.currentTimeMillis();
         System.out.println("요청 처리 시작 시간: "+getCurrentTime());
         
         long end = System.currentTimeMillis();
         System.out.println("요청 처리 종료 시간: "+getCurrentTime());
         
         System.out.println("총 소요 시간: "+ (end-start));
         
         chain.doFilter(request, response); //필터 처리
   }

   @Override
   public void destroy() {
      System.out.println("종료");
   }
   
   private String getURLPath(ServletRequest request) {
	   HttpServletRequest req;
	   String currentPath = "";
	   
	   String queryString = "";
	   
	   if(request instanceof HttpServletRequest) {
		   req=(HttpServletRequest) request;
		   currentPath = req.getRequestURI();
		   queryString = req.getQueryString();
		   queryString = (queryString == null) ? "" : "?" + queryString; 
	   }
	   return currentPath+queryString;
   }
   
   private String getCurrentTime() {
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter datetime =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm: ss");
      
      return now.format(datetime);
   }
}
