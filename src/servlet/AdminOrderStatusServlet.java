package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.OrderService;

@WebServlet(name = "/admin/order_status",urlPatterns = "/admin/order_status")
public class AdminOrderStatusServlet extends HttpServlet {

    private final OrderService oService;

    public AdminOrderStatusServlet() {
        oService = new OrderService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        oService.updateStatus(id, status);
        response.sendRedirect("/admin/order_list?status=" + status);
    }

}
