package servlet;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Order;
import model.User;
import service.OrderService;

@WebServlet(name = "order_list",urlPatterns = "/order_list")
public class OrderListServlet extends HttpServlet {

    private final OrderService oService;

    public OrderListServlet() {
        oService = new OrderService();
    }

    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("user");
        if(u == null) {
            response.sendRedirect("/index");
        } else {
            List<Order> list = oService.selectAll(u.getId());
            request.setAttribute("orderList", list);
            request.getRequestDispatcher("/order_list.jsp").forward(request, response);
        }
    }

}
