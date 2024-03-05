package team3.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import team3.entity.Item_order;
import team3.entity.Sales;
import team3.entity.Users;
import team3.service.ItemService;
import team3.service.ItemServiceImpl;
import team3.service.OrderService;
import team3.service.OrderServiceImpl;
import team3.service.SalesService;
import team3.service.SalesServiceImpl;
import team3.service.UserService;
import team3.service.UserServiceImpl;

import java.io.IOException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet({"/team3/user/login", "/team3/user/logout", "/team3/user/register", "/team3/user/update"
	, "/team3/user/delete", "/team3/user/mypage", "/team3/user/order", "/team3/user/orderDetail"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService usvc = new UserServiceImpl();
	private OrderService osvc = new OrderServiceImpl();
	private SalesService ssvc = new SalesServiceImpl();
	private ItemService isvc = new ItemServiceImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI(); // 현재 주소 리턴
		String[] uri = requestUri.split("/"); // 슬래시로 스플릿하여 구성요소(jw, ch07, kcity 등)가 나눠짐
		String action = uri[uri.length - 1]; // kcity 까지는 공통요소이므로 uri의 마지막 요소를 사용
		
		String method = request.getMethod(); // insert 등은 post와 get 모두 있으므로 구분하기 위해 사용
		
		HttpSession session = request.getSession(); // 로그인 상태를 판별하기 위한 객체
		
		//// 각종 변수나 객체 선언부 ////
		RequestDispatcher rd = null; 
		String uid = null;
		String pwd = null;
		String pwd2 = null;
		String email = null;
		String uname = null;
		String msg = null;
		String url = null;
		Users u = null;
		String hashpwd = null;
		String address = null;
		String sessUid = null;
		/////////////////////////////////
		
		switch (action) {
		case "login":
			if (method.equals("GET"))
			{
				// 로그인 화면 띄우기
				rd = request.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
				rd.forward(request, response);
			}
			else 
			{
				// 화면의 form에서 입력받은 데이터를 가지고 로그인 성공 여부를 판단한 다음
				// 성공할 경우 세션의 값을 설정하고 리스트로
				// 실패할 경우 알맞은 메시지를 띄우고 로그인 화면으로
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				int result = usvc.login(uid, pwd);
				
				if (result == usvc.CORRECT_LOGIN)
				{
					u = usvc.getUserByUid(uid);
					
					// 로그인 성공했으므로 세션에 값을 설정
					session.setAttribute("sessUid", uid);
					session.setAttribute("sessUname", u.getUname());
					
					msg = u.getUname() + "님 환영합니다.";
					url = "/mini/team3/main"; // 초기 화면으로 가는 url
				}
				else if (result == usvc.WRONG_PASSWORD)
				{
					msg = "패스워드가 틀립니다";
					url = "/mini/team3/user/login";
					
					session.setAttribute("Suid", uid);
				}
				else
				{
					msg = "ID 입력이 잘못되었습니다";
					url = "/mini/team3/user/login";
				}
				
				// 알림창 띄우기
				rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				rd.forward(request, response);
			}
			break;
		case "logout":
			session.invalidate(); 
			response.sendRedirect("/mini/team3/user/login");
			break;
		case "register":
			if (method.equals("GET"))
			{
				rd = request.getRequestDispatcher("/WEB-INF/view/user/register.jsp");
				rd.forward(request, response);
			}
			else
			{
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				address = request.getParameter("address");
				
				if (pwd.isEmpty() || pwd2.isEmpty() || uid.isEmpty() || uname.isEmpty())
				{
					msg = "필수로 입력되지 않은 요소가 있습니다.";
					url = "/mini/team3/user/register";
					
					session.setAttribute("Suid", uid);
					session.setAttribute("Spwd", pwd);
					session.setAttribute("Spwd2", pwd2);
					session.setAttribute("Suname", uname);
					session.setAttribute("Semail", email);
					session.setAttribute("Saddress", address);
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else if (!pwd.equals(pwd2))
				{
					msg = "패스워드가 잘못 입력되었습니다";
					url = "/mini/team3/user/register";
					
					session.setAttribute("Suid", uid);
					session.setAttribute("Spwd", pwd);
					session.setAttribute("Spwd2", pwd2);
					session.setAttribute("Suname", uname);
					session.setAttribute("Semail", email);
					session.setAttribute("Saddress", address);
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else if (usvc.getUserByUid(uid) != null)
				{
					msg = "이미 존재하는 계정입니다";
					url = "/mini/team3/user/register";
					
					session.setAttribute("Suid", uid);
					session.setAttribute("Spwd", pwd);
					session.setAttribute("Spwd2", pwd2);
					session.setAttribute("Suname", uname);
					session.setAttribute("Semail", email);
					session.setAttribute("Saddress", address);
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else
				{
					u = new Users(uid, pwd, uname, email, address);
					usvc.registerUser(u);
					
					msg = "가입을 환영합니다";
					url = "/mini/team3/main";
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}				
			}
			break;
		case "update":
			if (method.equals("GET"))
			{
				uid = request.getParameter("uid");
				u = usvc.getUserByUid(uid);
				
				rd = request.getRequestDispatcher("/WEB-INF/view/user/update.jsp");
				request.setAttribute("user", u);
				rd.forward(request, response);
			}
			else
			{
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				address = request.getParameter("address");
				
				if (pwd.isEmpty() || pwd2.isEmpty())
				{
					msg = "패스워드가 비어있습니다.";
					url = "/mini/team3/user/update";
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else if (!pwd.equals(pwd2))
				{
					msg = "패스워드가 잘못 입력되었습니다";
					url = "/mini/team3/user/update";
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else
				{	
					if (pwd != null && pwd.equals(pwd2))
						hashpwd = BCrypt.hashpw(pwd, BCrypt.gensalt());

					u = new Users(uid, hashpwd, uname, email, address);
					System.out.println(u);
					usvc.updateUser(u);
					
					msg = "회원 정보가 수정되었습니다";
					url = "/mini/team3/main";
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}			
			}
			break;
		case "delete":
			uid = request.getParameter("uid");
			usvc.deleteUser(uid);
			session.invalidate();
			
			response.sendRedirect("/mini/team3/main");
			break;
		case "mypage":
			uid = request.getParameter("uid");
			u = usvc.getUserByUid(uid);
			request.setAttribute("user", u);
			
			rd = request.getRequestDispatcher("/WEB-INF/view/user/mypage.jsp");
			rd.forward(request, response);
			break;
		case "order":
			uid = request.getParameter("uid");
			
			sessUid = (String) session.getAttribute("sessUid");
			if (sessUid == null || sessUid.equals(""))
			{
				response.sendRedirect("/mini/team3/user/login");
				break;
			}
			if (method.equals("GET"))
			{
				List<Item_order> orderList = osvc.getOrderList(uid);
				
				rd = request.getRequestDispatcher("/WEB-INF/view/user/order.jsp");
				request.setAttribute("uid", uid);
				request.setAttribute("orderList", orderList);
				rd.forward(request, response);				
			}
			else
			{
				String behavior = request.getParameter("be");
				
				int oid = Integer.parseInt(request.getParameter("oid"));
				Item_order order = osvc.getOrderOid(oid);
				
				if (behavior.equals("ins")) // 주문 확정
				{
					order.setStatus(1);
					osvc.updateOrder(order);
					
					msg = "주문 확정 완료";
					url = "/mini/team3/user/order?uid=" + uid;
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else // 주문 취소
				{
					osvc.cancelOrder(order);
					
					msg = "주문 취소 완료";
					url = "/mini/team3/user/order?uid=" + uid;
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
			}
			break;
		case "orderDetail":
			int oid = Integer.parseInt(request.getParameter("oid"));
			
			if (method.equals("GET"))
			{
				List<Sales> salesList = ssvc.getSalesList(oid);
				Item_order order = osvc.getOrderOid(oid);
				
				rd = request.getRequestDispatcher("/WEB-INF/view/user/orderDetail.jsp");
				request.setAttribute("order", order);
				request.setAttribute("salesList", salesList);
				rd.forward(request, response);	
			}
			else
			{
				int sid = Integer.parseInt(request.getParameter("sid"));
				
				ssvc.deleteSales(ssvc.getSales(sid));
				
				msg = "항목 취소 완료";
				url = "/mini/team3/user/orderDetail?oid=" + oid;
				
				rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				rd.forward(request, response);
			}
			break;
		default:
			rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
			request.setAttribute("msg", "잘못된 접근입니다");
			request.setAttribute("url", "/mini/team3/user/login");
			rd.forward(request, response);
		}
	}


}
