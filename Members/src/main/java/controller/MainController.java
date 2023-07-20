package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.Board;
import board.BoardDAO;
import member.Member;
import member.MemberDAO;

@WebServlet("*.do") // 경로를 .do로 끝나도록 설정
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 4L;

	MemberDAO memberDAO; // DAO 객체 선언
	BoardDAO boardDAO;

	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO(); // DAO 객체 생성
		boardDAO = new BoardDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 인코딩
		request.setCharacterEncoding("utf-8");
		// 한글 컨텐츠 유형 응답
		response.setContentType("text/html; charset=utf-8");

		// command 패턴으로 url 설정하기
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf('/'));

		System.out.println(uri);
		System.out.println();
		System.out.println(uri.lastIndexOf('/'));
		System.out.println("command: " + command);

		String nextPage = null;

		// 출력 스트림 객체 생성
		PrintWriter out = response.getWriter();

		// 세션 객체 생성
		HttpSession session = request.getSession();

		// 회원 목록 조회
		if (command.equals("/memberList.do")) {
			ArrayList<Member> memberList = memberDAO.getMemberList();

			// 모델 생성 보내기
			request.setAttribute("memberList", memberList);

			nextPage = "member/memberList.jsp";
		} else if (command.equals("/memberform.do")) {
			nextPage = "member/memberform.jsp";
		} else if (command.equals("/addMember.do")) {
			// 폼에 입력된 데이터를 받아오기
			String memberId = request.getParameter("memberId");
			String passwd = request.getParameter("passwd1");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");

			Member newMember = new Member();// 회원 객체 생성
			newMember.setMemberId(memberId);
			newMember.setPasswd(passwd);
			newMember.setName(name);
			newMember.setGender(gender);

			memberDAO.addMember(newMember); // 외원 매개로 DB에 저장
			session.setAttribute("sessionId", memberId);
			nextPage = "index.jsp";
		} else if (command.equals("/memberView.do")) {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			// memberId 받기
			String memberId = request.getParameter("memberId");

			Member member = memberDAO.getMember(memberId);

			request.setAttribute("member", member);

			nextPage = "/member/memberView.jsp";
		} else if (command.equals("/loginForm.do")) {
			nextPage = "/member/loginForm.jsp";
		} else if (command.equals("/loginProcess.do")) {
			// 로그인 폼에 입력된 데이터 받아오기
			String memberId = request.getParameter("memberId");
			String passwd = request.getParameter("passwd");

			Member loginMember = new Member();
			loginMember.setMemberId(memberId);
			loginMember.setPasswd(passwd);

			boolean result = memberDAO.checkLogin(loginMember);

			if (result) {

				session.setAttribute("sessionId", memberId);
				nextPage = "/index.jsp";
			} else {
				// 2가지 방법, alert, errorMsg
				out.println("<script>");
				out.println("alert('아이디와 비밀번호를 확인해 주세요')");
				out.println("history.go(-1)"); // 이전 페이지로 이동
				out.println("</script>");
			}
		} else if (command.equals("/logout.do")) {
			// 세션 모두 삭제(해제)
			session.invalidate();
			nextPage = "/index.jsp";
		}

		// 게시판 관리
		else if (command.equals("/boardList.do")) {

			//페이징 처리
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) {
				pageNum = "1";
			}
			// 1page -> 1, 2page -> 11, 3page -> 21
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10;
			int startRaw = (currentPage-1)*10 + 1;
			
			// 시작 페이지 13번-> 2page , 23 -> 3page
			int startPage = startRaw / 10 + 1;
			
			int total = boardDAO.getBoardCount();
			// 종료 페이지
			int endPage = total/pageSize;
			endPage = (total % pageSize == 0) ? endPage : endPage + 1;
			
			ArrayList<Board> boardList = boardDAO.getBoardList(currentPage);
			
			request.setAttribute("boardList", boardList);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage",endPage);
			
			nextPage = "/board/boardList.jsp";
		} else if (command.equals("/boardForm.do")) {
			nextPage = "/board/boardForm.jsp";
		} else if (command.equals("/addBoard.do")) {
			String realFolder="C:/green_project/JSPworks/Members/src/main/webapp/upload";
			MultipartRequest multi = new MultipartRequest(request, realFolder,
					5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			// 글쓰기 폼에 입력된 데이타 받아오기. (request 는 사용하지 않고 multi 사용)
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			// memberId 세션을 가져와야함
			String memberId = (String) session.getAttribute("sessionId");
			
			//fileName 속성 가져오기
			Enumeration<String> files = multi.getFileNames();
			String name = "";
			String fileName = "";
			if(files.hasMoreElements()) {
				name = (String)files.nextElement();
				fileName = multi.getFilesystemName(name);
			}

			Board newBoard = new Board();
			newBoard.setTitle(title);
			newBoard.setContent(content);
			newBoard.setMemberId(memberId);
			newBoard.setFileUpload(fileName);

			// 글쓰기 처리 메서드 호출
			boardDAO.addBoard(newBoard);
		} else if (command.equals("/boardView.do")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			Board board = boardDAO.getBoard(bnum);

			request.setAttribute("board", board);

			nextPage = "/board/boardView.jsp";
		} else if (command.equals("/deleteBoard.do")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			boardDAO.deleteBoard(bnum);

			
			nextPage = "/boardList.do";
		} else if (command.equals("/deleteMember.do")) {
			String memberId = request.getParameter("memberId");
			memberDAO.deleteMember(memberId);
			nextPage = "/memberList.do";
		} else if (command.equals("/updateBoard.do")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			
			Board board = boardDAO.getBoard(bnum);
			
			request.setAttribute("board", board);
			nextPage = "/board/updateBoard.jsp";
		} else if(command.equals("/updateProcess.do")){
			//수정 폼에서 입력 내용 받기
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			Board updateBoard = new Board();
			updateBoard.setTitle(title);
			updateBoard.setContent(content);
			updateBoard.setBnum(bnum);
			boardDAO.updateBoard(updateBoard);
			
			nextPage = "/boardView.do";
		}

		if (command.equals("/addBoard.do")) {
			response.sendRedirect("/boardList.do");
		} else {
			// 포워딩
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}

	}

}
