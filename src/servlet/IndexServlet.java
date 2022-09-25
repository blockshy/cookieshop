package servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.GoodsService;
import java.util.*;

@WebServlet(name = "index",urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    private final GoodsService gService;

    public IndexServlet() {
        gService = new GoodsService();
    }

    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        List<Map<String, Object>> ScrollGood = gService.getScrollGood();
        request.setAttribute("scroll", ScrollGood);
        System.out.println(Arrays.toString(ScrollGood.toArray()));
        List<Map<String, Object>> newList = gService.getGoodsList(3);
        request.setAttribute("newList", newList);
        System.out.println(Arrays.toString(newList.toArray()));
        List<Map<String, Object>> hotList = gService.getGoodsList(2);
        request.setAttribute("hotList", hotList);
        System.out.println(Arrays.toString(hotList.toArray()));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
