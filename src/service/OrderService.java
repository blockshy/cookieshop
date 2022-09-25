package service;

import dao.OrderDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.Order;
import model.OrderItem;
import model.Page;
import utils.DataSourceUtils;

public class OrderService {
    private final OrderDao oDao = new OrderDao();

    public OrderService() {
    }

    public void addOrder(Order order) {
        Connection con = null;

        try {
            con = DataSourceUtils.getConnection();
            con.setAutoCommit(false);
            this.oDao.insertOrder(con, order);
            int id = this.oDao.getLastInsertId(con);
            order.setId(id);

            for (OrderItem item : order.getItemMap().values()) {
                this.oDao.insertOrderItem(con, item);
            }

            con.commit();
        } catch (SQLException var7) {
            var7.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException var6) {
                    var6.printStackTrace();
                }
            }
        }

    }

    public List<Order> selectAll(int userid) {
        List<Order> list = null;

        try {
            list = this.oDao.selectAll(userid);

            for (Order o : list) {
                List<OrderItem> l = this.oDao.selectAllItem(o.getId());
                o.setItemList(l);
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return list;
    }

    public Page<Order> getOrderPage(int status, int pageNumber) {
        Page<Order> p = new Page<>();
        p.setPageNumber(pageNumber);
        int pageSize = 10;
        int totalCount = 0;

        try {
            totalCount = this.oDao.getOrderCount(status);
        } catch (SQLException var10) {
            var10.printStackTrace();
        }

        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List<Order> list = null;

        try {
            list = this.oDao.selectOrderList(status, pageNumber, pageSize);

            for (Order o : list) {
                List<OrderItem> l = this.oDao.selectAllItem(o.getId());
                o.setItemList(l);
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        }

        p.setList(list);
        return p;
    }

    public void updateStatus(int id, int status) {
        try {
            this.oDao.updateStatus(id, status);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public void delete(int id) {
        Connection con = null;

        try {
            con = DataSourceUtils.getDataSource().getConnection();
            con.setAutoCommit(false);
            this.oDao.deleteOrderItem(con, id);
            this.oDao.deleteOrder(con, id);
            con.commit();
        } catch (SQLException var6) {
            var6.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException var5) {
                    var5.printStackTrace();
                }
            }
        }

    }
}
