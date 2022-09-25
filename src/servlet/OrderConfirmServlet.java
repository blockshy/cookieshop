package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Order;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.OrderService;

@WebServlet(name = "order_confirm",urlPatterns = "/order_confirm")
public class OrderConfirmServlet extends HttpServlet {

    private final OrderService oService;

    public OrderConfirmServlet() {
        oService = new OrderService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Order o = (Order)request.getSession().getAttribute("order");
        try {
            BeanUtils.copyProperties(o, request.getParameterMap());
        } catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        o.setDatetime(new Date());
        o.setStatus(2);
        o.setUser((User)request.getSession().getAttribute("user"));
        oService.addOrder(o);
        request.getSession().removeAttribute("order");
        request.setAttribute("msg", "订单支付成功捏！");
        request.getRequestDispatcher("/order_success.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }
}
