package hotel_managment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotel_managment.dao.ItemsDao;
import hotel_managment.dto.Items;


@WebServlet("/deleteorder")
public class DeleteOrderController extends HttpServlet {
	static Items items3;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		ItemsDao dao = new ItemsDao();
		for (Items items2 : ConfirmOrderController.list) {
			if (id == items2.getId()) {
				items3 = items2;
			}
		}
		ConfirmOrderController.list.remove(items3);
		boolean result = dao.deleteItems(id);
		if (result) {
			req.setAttribute("list", ConfirmOrderController.list);
			req.setAttribute("user", SaveUserController.user);

			RequestDispatcher dispatcher = req.getRequestDispatcher("foodorder.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
