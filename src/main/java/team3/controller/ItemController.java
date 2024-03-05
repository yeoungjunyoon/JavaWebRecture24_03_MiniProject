package team3.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import team3.entity.Item;
import team3.entity.Item_order;
import team3.entity.Sales;
import team3.service.ItemService;
import team3.service.ItemServiceImpl;
import team3.service.OrderService;
import team3.service.OrderServiceImpl;
import team3.service.SalesService;
import team3.service.SalesServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/team3/item/list", "/team3/item/detail", "/team3/item/insert", "/team3/item/delete"})
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService isvc = new ItemServiceImpl();
	private OrderService osvc = new OrderServiceImpl();
	private SalesService ssvc = new SalesServiceImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI(); // 현재 주소 리턴
		String[] uri = requestUri.split("/"); // 슬래시로 스플릿하여 구성요소(jw, ch07, kcity 등)가 나눠짐
		String action = uri[uri.length - 1]; // kcity 까지는 공통요소이므로 uri의 마지막 요소를 사용
		
		String method = request.getMethod(); // insert 등은 post와 get 모두 있으므로 구분하기 위해 사용
		
		HttpSession session = request.getSession(); // 로그인 상태를 판별하기 위한 객체
		
		//// 각종 변수나 객체 선언부 ////
		RequestDispatcher rd = null; 
		String field = null;
		String query = null;
		int page = 0;
		int mid = 0;
		Item item = null;
		String sessUid = null;
		String uid = null;
		int quantity = 0;
		String msg = null;
		String url = null;
		String name = null;
		int price = 0;
		String category = null;
		String description = null;
		/////////////////////////////////
		
		switch(action) {
		case "list":
			String page_ = request.getParameter("p");
			field = request.getParameter("f"); // 검색 분야
			query = request.getParameter("q"); // 검색어
			
			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			field = (field == null || field.equals("")) ? "name" : field;
			query = (query == null || query.equals("")) ? "" : query;
			
			session.setAttribute("currentItemPage", page);
			session.setAttribute("field", field);
			session.setAttribute("query", query);
			
			List<Item> itemList = isvc.getItemList(field, query, page);
			request.setAttribute("itemList", itemList);
			
			///////////////////////////////////////// 페이지네이션
			int totalItems = isvc.getItemCount(field, query);
			int totalPages = (totalItems - 1) / 10 + 1;
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalPages; i++)
			{
				pageList.add(i + "");
			}
			request.setAttribute("pageList", pageList);
			
			rd = request.getRequestDispatcher("/WEB-INF/view/item/list.jsp");
			rd.forward(request, response);
			
			break;
		case "detail":
			sessUid = (String) session.getAttribute("sessUid");
			
			if (method.equals("GET"))
			{
				mid = Integer.parseInt(request.getParameter("mid"));
				
				item = isvc.getItem(mid);
				request.setAttribute("item", item);
				request.setAttribute("sessUid", sessUid);
				
				rd = request.getRequestDispatcher("/WEB-INF/view/item/detail.jsp");
				rd.forward(request, response);
			}
			else
			{
				if (sessUid == null || sessUid.equals(""))
				{
					response.sendRedirect("/mini/team3/user/login");
					break;
				}
				
				uid = request.getParameter("uid");
				mid = Integer.parseInt(request.getParameter("mid"));
				quantity = Integer.parseInt(request.getParameter("quantity"));
				
				if (quantity < 1)
				{
					msg = "수량이 잘못 입력되었습니다.";
					url = "/mini/team3/item/detail?mid=" + mid;
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
					break;
				}
				
				Item_order order = osvc.getOrder(sessUid, 0);
				
				// 주문이 없을 경우 주문 생성
				if (order == null)
				{
					order = new Item_order(sessUid);
					osvc.insertOrder(order);					
				}
				
				order = osvc.getOrder(sessUid, 0);
				
				Sales sales = ssvc.getSalesOid(order.getOid(), mid);
				
				if (sales == null)
				{
					// 구매 정보 생성
					sales = new Sales(mid, order.getOid(), quantity);
					ssvc.insertSales(sales);					
				}
				else
				{
					sales.setQuantity(quantity + sales.getQuantity());
					ssvc.updateSales(sales);
				}
				
				msg = "구매 완료";
				url = "/mini/team3/item/detail?mid=" + mid;
				
				rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				rd.forward(request, response);
			}
			break;
		case "insert":
			sessUid = (String) session.getAttribute("sessUid");
			
			if (sessUid == null || sessUid.equals(""))
			{
				response.sendRedirect("/mini/team3/user/login");
				break;
			}
			
			if (method.equals("GET"))
			{
				
				request.setAttribute("sessUid", sessUid);
				
				rd = request.getRequestDispatcher("/WEB-INF/view/item/insert.jsp");
				rd.forward(request, response);
			}
			else
			{
				name = request.getParameter("name");
				category = request.getParameter("category");
				// String image
				description = request.getParameter("description");
				
				if (name.isEmpty() || request.getParameter("price").isEmpty() || category.isEmpty())
				{
					msg = "이름, 가격, 카테고리 중 하나가 비어있습니다.";
					url = "/mini/team3/item/insert";
					
					session.setAttribute("Sname", name);
					session.setAttribute("Scategory", category);
					session.setAttribute("Sdescription", description);
					session.setAttribute("Sprice", request.getParameter("price"));
					
					rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else
				{					
					price = Integer.parseInt(request.getParameter("price"));
					item = new Item(name, price, category, description);
					isvc.insertItem(item);
					response.sendRedirect("/mini/team3/item/list?p=1");
				}
			}
			break;
		}
	}


}
