package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Order;
import model.Page;
import service.OrderService;

@WebServlet(name = "/admin/order_list",urlPatterns = "/admin/order_list")
public class AdminOrderListServlet extends HttpServlet{

    private final OrderService oService;

    public AdminOrderListServlet(){
        oService = new OrderService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int status = 0;
        if(request.getParameter("status") != null)
            status = Integer.parseInt(request.getParameter("status"));
        request.setAttribute("status", status);
        int pageNumber = 1;
        if(request.getParameter("pageNumber") != null)
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch(Exception ignored) { }
        if(pageNumber <= 0)
            pageNumber = 1;
        Page<Order> p = oService.getOrderPage(status, pageNumber);
        if(p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        } else
        if(pageNumber >= p.getTotalPage() + 1)
            p = oService.getOrderPage(status, pageNumber);
        request.setAttribute("p", p);
        request.getRequestDispatcher("/admin/order_list.jsp").forward(request, response);
    }

}
