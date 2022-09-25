package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "order_submit",urlPatterns = "/order_submit")
public class OrderSubmitServlet extends HttpServlet {

    public OrderSubmitServlet() {
    }

    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if(request.getSession().getAttribute("user") != null) {
            request.getRequestDispatcher("/order_submit.jsp").forward(request, response);
        } else {
            request.setAttribute("failMsg", "请先登录再提交订单捏！");
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
        }
    }
}
