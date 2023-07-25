package controller;

import java.io.IOException;
import java.net.CookieStore;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.Member;
import member.MemberDAO;
import product.Product;
import product.ProductDAO;

@WebServlet("*.do")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO;
	private MemberDAO memberDAO;
   
	public void init(ServletConfig config) throws ServletException {
		productDAO = new ProductDAO();
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(command);
		
		HttpSession session = request.getSession();
		
		String nextPage = null;
		
		if(command.equals("/productList.do")) {
			
			List<Product> productList = productDAO.getProductList();
			
			// 모델 생성
			request.setAttribute("productList", productList);
			
			nextPage = "/product/productList.jsp";
		}else if(command.equals("/productInfo.do")) {
			String id = request.getParameter("productId");
			
			Product product = productDAO.getProduct(id);
			
			request.setAttribute("product", product);
			
			nextPage = "/product/productInfo.jsp";
		}else if(command.equals("/productForm.do")) {
			
			
			nextPage = "/product/productForm.jsp";
		}else if(command.equals("/addProduct.do")) {
			String realFolder = "C:/green_project/JSPworks/Market/src/main/webapp/upload";
			MultipartRequest multi = new MultipartRequest(request, realFolder,
					5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			String id = multi.getParameter("productId");
			String pname = multi.getParameter("pname");
			Integer unitPrice = Integer.parseInt(multi.getParameter("unitPrice"));
			String description = multi.getParameter("description");
			String category = multi.getParameter("category");
			String manufacture = multi.getParameter("manufacture");
			Long unitsInStock = Long.parseLong(multi.getParameter("unitsInStock"));
			String condition = multi.getParameter("condition");
			
			String name = "";
			String productImage = "";
			Enumeration<String> files = multi.getFileNames();
			if(files.hasMoreElements()) {
				name = files.nextElement();
				productImage = multi.getFilesystemName(name);
			}
			Product product = new Product();
			product.setProductId(id);
			product.setPname(pname);
			product.setUnitPrice(unitPrice);
			product.setDescription(description);
			product.setCategory(category);
			product.setManufacture(manufacture);
			product.setUnitsInStock(unitsInStock);
			product.setProductImage(productImage);
			product.setCondition(condition);
			
			productDAO.addProduct(product);
			
			nextPage = "productList.do";
		}else if(command.equals("/editProduct.do")) {
			List<Product> productList = productDAO.getProductList();
			
			String edit = request.getParameter("edit");
			
			// 모델 생성
			request.setAttribute("productList", productList);
			request.setAttribute("edit", edit);
			
			nextPage = "/product/editProduct.jsp";
		}else if(command.equals("/deleteProduct.do")) {
			String id = request.getParameter("productId");
			String edit = request.getParameter("edit");
			
			productDAO.deleteProduct(id);
			
			//삭제 후 삭제 페이지로 이동함
			nextPage = "/editProduct.do?edit=" + edit;
		}else if(command.equals("/addCart.do")) {
	         String id = request.getParameter("productId");
	         
	         //상품 목록
	         List<Product> goodsList = productDAO.getProductList();
	         Product goods = new Product();
	         
	         //목록중에서 추가한 상품을 찾음
	         for(int i=0; i<goodsList.size(); i++) {      //포문 돌리고
	            goods = goodsList.get(i);            //리스트에 있는 상품 하나씩 꺼내고
	            if(goods.getProductId().equals(id)) {   //돌리다가 request 받은 id랑 같은 상품이 나오면 탈출
	               break;
	            }
	         }
	         
	         //장바구니 생성 및 세션 발급
	         List<Product> list = (ArrayList)session.getAttribute("cartList");
	         if(list == null) {
	            list = new ArrayList<>();
	            session.setAttribute("cartList", list);   //세션 이름 - cartList
	         }
	         
	         //수량 증가 - 요청 아이디의 상품이 기존에 장바구니에 잇으면 수량 증가
	         int cnt = 0;
	         Product goodsQnt = new Product();   //상품 객체(개수를 가진)
	         
	         for(int i=0; i<list.size(); i++) {
	            goodsQnt = list.get(i);
	            if(goodsQnt.getProductId().equals(id)) {
	               cnt++;   //해당 품목을 1증가
	               int orderQuantity = goodsQnt.getQuantity() + 1;   //주문수량
	               goodsQnt.setQuantity(orderQuantity);
	            }
	         }
	         
	         //장바구니에 이전에 담긴 품목이 아니라면 수량을 1로 하고, 장바구니에 추가
	         if(cnt == 0) {
	            goods.setQuantity(1);
	            list.add(goods);
	         }
	      }else if(command.equals("/cart.do")) {   //장바구니 페이지 요청
	    	 List<Product> cartList = (ArrayList)session.getAttribute("cartList");
	         if(cartList == null) {
	        	 cartList = new ArrayList<>();
	         }
	         Product product = null;
	         int total = 0, sum = 0;
	         for(int i=0; i<cartList.size(); i++) {
	        	 product = cartList.get(i);
	        	 total = product.getUnitPrice() * product.getQuantity();
	        	 sum += total;
	         }
	         
	         String cartId = session.getId();
	         
	         request.setAttribute("cartList", cartList);
	         request.setAttribute("sum", sum);
	         request.setAttribute("cartId", cartId);
	         
	         nextPage = "/product/cart.jsp";
	     }else if(command.equals("/deleteCart.do")) {  
	    	 session.invalidate();
	    	 
	     } else if (command.equals("/removeCart.do")) { // 장바구니 (상품) 삭제 요청
	         String id = request.getParameter("productId");
	         // 장바구니 가져오기 및 세션 유지
	         List<Product> cartList = (ArrayList) session.getAttribute("cartList");
	         Product selProduct = new Product(); // 삭제할 품목 객체
	         for (int i = 0; i < cartList.size(); i++) {
	            selProduct = cartList.get(i);
	            if (selProduct.getProductId().equals(id)) {
	               cartList.remove(selProduct);
	            }

	         }
	      }else if(command.equals("/shippingInfo.do")) {
				String cartId = request.getParameter("cartId");
				//cartId 모델 생성후 보내기
				request.setAttribute("cartId", cartId);
				
				nextPage = "/product/shippingInfo.jsp";
			}else if(command.equals("/processShippingInfo.do")) {
				Cookie[] cookies = request.getCookies();
				if(cookies != null) {
					for(int i=0; i<cookies.length; i++) {
						Cookie cookie = cookies[i];
						String cname = cookie.getName();
						if(cname.equals("Shipping_cartId"))
							cookie.setMaxAge(0);
						if(cname.equals("Shipping_name"))
							cookie.setMaxAge(0);
						if(cname.equals("Shipping_shippingDate"))
							cookie.setMaxAge(0);
						if(cname.equals("Shipping_country"))
							cookie.setMaxAge(0);
						if(cname.equals("Shipping_zipCode"))
							cookie.setMaxAge(0);
						if(cname.equals("Shipping_addressName"))
							cookie.setMaxAge(0);
					}
				}
				
				//쿠키 발행 - Cookie(쿠키이름, 쿠키값)
				Cookie shippingId = new Cookie("Shipping_cartId", 
						 URLEncoder.encode(request.getParameter("cartId"), "utf-8"));
				Cookie name = new Cookie("Shipping_name", 
						URLEncoder.encode(request.getParameter("name"), "utf-8"));
				Cookie shippingDate = new Cookie("Shipping_shippingDate", 
						URLEncoder.encode(request.getParameter("shippingDate"), "utf-8"));
				Cookie country = new Cookie("Shipping_country", 
						URLEncoder.encode(request.getParameter("country"), "utf-8"));
				Cookie zipCode = new Cookie("Shipping_zipCode", 
						URLEncoder.encode(request.getParameter("zipCode"), "utf-8"));
				Cookie addressName = new Cookie("Shipping_addressName", 
						URLEncoder.encode(request.getParameter("addressName"), "utf-8"));
				
				//쿠키 유효기간 1일
				shippingId.setMaxAge(24*60*60);
				name.setMaxAge(24*60*60);
				shippingDate.setMaxAge(24*60*60);
				country.setMaxAge(24*60*60);
				zipCode.setMaxAge(24*60*60);
				addressName.setMaxAge(24*60*60);
				
				//클라이언트 컴으로 쿠키 보내기
				response.addCookie(shippingId);
				response.addCookie(name);
				response.addCookie(shippingDate);
				response.addCookie(country);
				response.addCookie(zipCode);
				response.addCookie(addressName);
				
				//쿠키 받아서 인코딩 -> 디코딩 -> 모델로 보내기
				//변수 선언
				String shipping_cartId = "";   //주문번호
				String shipping_name = "";     //주문자 이름
				String shipping_shippingDate = "";  //배송일
				String shipping_country = "";   //국가
				String shipping_zipCode = "";   //우편번호
				String shipping_addressName = ""; //주소
				
				cookies = request.getCookies(); //쿠키를 받을 배열 생성
				if(cookies != null) {
					for(int i=0; i<cookies.length; i++) {
						Cookie cookie = cookies[i];
						String cname = cookie.getName(); //쿠키 이름
						if(cname.equals("Shipping_cartId")) //쿠키이름이 같으면 쿠키값을 복원(디코딩)
							shipping_cartId = URLDecoder.decode(cookie.getValue(), "utf-8");
						if(cname.equals("Shipping_name")) 
							shipping_name = URLDecoder.decode(cookie.getValue(), "utf-8");
						if(cname.equals("Shipping_shippingDate")) 
							shipping_shippingDate = URLDecoder.decode(cookie.getValue(), "utf-8");
						if(cname.equals("Shipping_country")) 
							shipping_country = URLDecoder.decode(cookie.getValue(), "utf-8");
						if(cname.equals("Shipping_zipCode"))
							shipping_zipCode = URLDecoder.decode(cookie.getValue(), "utf-8");
						if(cname.equals("Shipping_addressName")) 
							shipping_addressName = URLDecoder.decode(cookie.getValue(), "utf-8");
					}
				}
				
				//장바구니 가져오기 및 세션 유지
				List<Product> cartList = (ArrayList)session.getAttribute("cartList");
				if(cartList == null) {
					cartList = new ArrayList<>();
				}
				
				//총합계 계산하기
				int total = 0, sum = 0;  //소계, 총계
				for(int i=0; i<cartList.size(); i++) {
					Product product = cartList.get(i);
					total = product.getUnitPrice() * product.getQuantity(); //가격x수량
					sum += total;
				}
				
				//모델 생성(배송 관련)
				request.setAttribute("shipping_name", shipping_name);
				request.setAttribute("shipping_shippingDate", shipping_shippingDate);
				request.setAttribute("shipping_zipCode", shipping_zipCode);
				request.setAttribute("shipping_addressName", shipping_addressName);
				
				//상품 관련 모델 생성
				request.setAttribute("cartList", cartList);
				request.setAttribute("sum", sum);
			
				//이동할 페이지 - 주문 완료
				nextPage = "/product/orderConfirm.jsp";
			}else if(command.equals("/thanksCustomer.do")) {
				String shipping_cartId = "";   //주문번호
				String shipping_shippingDate = "";  //배송일
				
				Cookie[] cookies = request.getCookies();
				if(cookies != null) {
					for(int i=0; i<cookies.length; i++) {
						Cookie cookie = cookies[i];
						String cname = cookie.getName();
						if(cname.equals("Shipping_cartId")) {
							shipping_cartId = URLDecoder.decode(cookie.getValue(),"utf-8");
						}
						if(cname.equals("Shipping_shippingDate")) {
							shipping_shippingDate = URLDecoder.decode(cookie.getValue(),"utf-8");
						}
					}
				}
				
				request.setAttribute("shipping_cartId", shipping_cartId);
				request.setAttribute("shipping_shippingDate", shipping_shippingDate);
				
				nextPage = "/product/thanksCustomer.jsp";
			}else if(command.equals("/checkOutCancle.do")) {
				nextPage = "/product/checkOutCancle.jsp";
			}else if(command.equals("/updateProductForm.do")) {
				String id = request.getParameter("productId");
				
				Product product = productDAO.getProduct(id);
				
				//모델 생성
				request.setAttribute("product", product);
				
				nextPage = "/product/updateProductForm.jsp"; 
			}else if(command.equals("/updateProduct.do")) {
				String realFolder = "C:/green_project/JSPworks/Market/src/main/webapp/upload";
				
				MultipartRequest multi = new MultipartRequest(request, realFolder,
						5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
				
				//name 속성 가져오기
				String id = multi.getParameter("productId");
				String pname = multi.getParameter("pname");
				int unitPrice = Integer.parseInt(multi.getParameter("unitPrice"));
				String description = multi.getParameter("description");
				String category = multi.getParameter("category");
				String manufacture = multi.getParameter("manufacture");
				long unitsInStock = Long.parseLong(multi.getParameter("unitsInStock"));
				String condition = multi.getParameter("condition");
				
				//productImage 속성 가져오기
				String name = "";
				String productImage = "";
				Enumeration<String> files = multi.getFileNames();
				if(files.hasMoreElements()) {
					name = files.nextElement();  //파일이 있으면 이름을 저장
					productImage = multi.getFilesystemName(name); //서버에 저장된 파일 이름을 저장
				}
				
				//Product 객체 생성
				Product product = new Product();
				product.setProductId(id);
				product.setPname(pname);
				product.setUnitPrice(unitPrice);
				product.setDescription(description);
				product.setCategory(category);
				product.setManufacture(manufacture);
				product.setUnitsInStock(unitsInStock);
				product.setCondition(condition);
				product.setProductImage(productImage);
				
				if(productImage != null) {
					productDAO.updateProduct(product);
				}else {
					productDAO.updateProductNoImage(product);
				}
				
				nextPage = "/editProduct.do?edit=update";
			}
			
			if(command.equals("/memberList.do")) {
				List<Member> memberList = memberDAO.getMemberList();
				request.setAttribute("memberList", memberList);
				nextPage = "/member/memberList.jsp";
			}else if(command.equals("/memberInfo.do")) {
				String mid = request.getParameter("mid");
				Member member = memberDAO.getMember(mid);
				request.setAttribute("member", member);
				
				nextPage = "/member/memberInfo.jsp";
			}else if(command.equals("/memberForm.do")) {
				nextPage = "/member/memberForm.jsp";
			}else if(command.equals("/addMember.do")) {
				String mid = request.getParameter("mid");
				String passwd = request.getParameter("passwd1");
				String mname = request.getParameter("mname");
				String gender = request.getParameter("gender");
				
				String birthyy = request.getParameter("birthyy");
				String birthmm = request.getParameterValues("birthmm")[0];
				String birthdd = request.getParameter("birthdd");
				String birth = birthyy+"/"+birthmm+"/"+birthdd;
				
				String email1 = request.getParameter("email");
				String email2 = request.getParameter("email");
				String email = email1 + "@" + email2;
				
				String phone = request.getParameter("phone");
				String address = request.getParameter("address");
				
				Member newMember = new Member();
				newMember.setMid(mid);
				newMember.setPasswd(passwd);
				newMember.setMname(mname);
				newMember.setGender(gender);
				newMember.setBirth(birth);
				newMember.setEmail(email);
				newMember.setPhone(phone);
				newMember.setAddress(address);
				
				//dao의 addmember 호출
				memberDAO.addMember(newMember);
				
				nextPage = "/index.jsp";
			}else if(command.equals("/loginForm.do")) { //로그인 페이지 요청
				nextPage = "/member/loginForm.jsp";
			}else if(command.equals("/processLogin.do")) { //로그인 처리
				String mid = request.getParameter("mid");
				String passwd = request.getParameter("passwd");
				
				Member member = new Member();
				member.setMid(mid);
				member.setPasswd(passwd);
				
				boolean result = memberDAO.checkLogin(member);
				if(result) {
					session.setAttribute("sessionId", mid);
					nextPage = "index.jsp";
				}else {
					String error = "아이디나 비밀번호를 확인해 주세요";
					request.setAttribute("error", error);
					nextPage = "/loginForm.do";
				}
			}else if(command.equals("/logout.do")) { //로그아웃
				session.invalidate();
				nextPage = "index.jsp";
			}else if(command.equals("/memberInfo.do")) {  //나의 정보
				String mid = request.getParameter("mid");
				Member member = memberDAO.getMember(mid);
				request.setAttribute("member", member);
				nextPage = "/member/memberInfo.jsp";
			}
		
		
			
			//페이지 포워딩
			if(command.equals("/addCart.do")) { //상품 주문 요청
				String id = request.getParameter("productId");
				response.sendRedirect("/productInfo.do?productId=" + id);
			}else if(command.equals("/deleteCart.do") || command.equals("/removeCart.do")) { 
				response.sendRedirect("/cart.do"); //장바구니 전체 및 개별 품목 삭제후 페이지 이동
			}
			
			else {
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(nextPage);
				dispatcher.forward(request, response);
			}
		}
}
