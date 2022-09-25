package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Goods;
import model.Order;
import service.GoodsService;

@WebServlet(name = "goods_buy",urlPatterns = "/goods_buy")
public class GoodsBuyServlet extends HttpServlet {

    private final GoodsService gService;

    public GoodsBuyServlet() {
        gService = new GoodsService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Order o;
        if(request.getSession().getAttribute("order") != null) {
            o = (Order)request.getSession().getAttribute("order");
        } else {
            o = new Order();
            request.getSession().setAttribute("order", o);
        }
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        Goods goods = gService.getGoodsById(goodsid);
        if(goods.getStock() > 0) {
            o.addGoods(goods);
            response.getWriter().print("ok");
        } else {
            response.getWriter().print("fail");
        }
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

}
