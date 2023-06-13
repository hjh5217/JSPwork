package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

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
			ArrayList<Board> boardList = boardDAO.getBoardList();

			request.setAttribute("boardList", boardList);
			nextPage = "/board/boardList.jsp";
		} else if (command.equals("/boardForm.do")) {
			nextPage = "/board/boardForm.jsp";
		} else if (command.equals("/addBoard.do")) {
			// 글쓰기 폼에 입력된 데이타 받아오기.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			// memberId 세션을 가져와야함
			String memberId = (String) session.getAttribute("sessionId");

			Board board = new Board();
			board.setTitle(title);
			board.setContent(content);
			board.setMemberId(memberId);

			// 글쓰기 처리 메서드 호출
			boardDAO.addBoard(board);
		} else if (command.equals("/boardView.do")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			Board board = boardDAO.getBoard(bnum);

			request.setAttribute("board", board);

			nextPage = "/board/boardView.jsp";
		} else if (command.equals("/deleteBoard.do")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			boardDAO.deleteBoard(bnum);

			nextPage = "/board/boardList.jsp";
		} else if (command.equals("/deleteMember.do")) {
			String memberId = request.getParameter("memberId");
			memberDAO.deleteMember(memberId);
			nextPage = "/memberList.do";
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
