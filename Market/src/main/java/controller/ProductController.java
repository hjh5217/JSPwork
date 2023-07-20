package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.Product;
import product.ProductDAO;

@WebServlet("*.do")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO;
   
	public void init(ServletConfig config) throws ServletException {
		productDAO = new ProductDAO();
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
			Long unitsInStockLong = Long.parseLong(multi.getParameter("unitsInStock"));
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
			product.setUnitsInstock(unitsInStockLong);
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
			
			String productId = request.getParameter("ProductId");
			
			productDAO.deleteProduct(productId);
			
			nextPage = "editProduct.do?edit=delete";
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
	         
	         request.setAttribute("cartList", cartList);
	         request.setAttribute("sum", sum);
	         
	         nextPage = "/product/cart.jsp";
	       }
	       
		if(command.equals("/addCart.do")) {   //상품 주문 요청
	         String id = request.getParameter("productId");
	         response.sendRedirect("/productInfo.do?productId=" + id);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}
}
