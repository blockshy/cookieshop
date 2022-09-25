package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Order;

@WebServlet(name = "goods_delete",urlPatterns = "/goods_delete")
public class GoodsDeleteServlet extends HttpServlet {

    public GoodsDeleteServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Order o = (Order)request.getSession().getAttribute("order");
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        o.delete(goodsid);
        response.getWriter().print("ok");
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }
}
