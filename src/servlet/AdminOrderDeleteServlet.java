package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.OrderService;

@WebServlet(name = "/admin/order_delete",urlPatterns = "/admin/order_delete")
public class AdminOrderDeleteServlet extends HttpServlet {

    private final OrderService oService;

    public AdminOrderDeleteServlet() {
        oService = new OrderService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        oService.delete(id);
        request.getRequestDispatcher("/admin/order_list").forward(request, response);
    }

}
