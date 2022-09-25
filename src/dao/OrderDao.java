package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Order;
import model.OrderItem;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

public class OrderDao {
    public OrderDao() {
    }

    public void insertOrder(Connection con, Order order) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql = "insert into `order`(total,amount,status,paytype,name,phone,address,datetime,user_id) values(?,?,?,?,?,?,?,?,?)";
        r.update(con, sql, order.getTotal(), order.getAmount(), order.getStatus(), order.getPaytype(), order.getName(), order.getPhone(), order.getAddress(), order.getDatetime(), order.getUser().getId());
    }

    public int getLastInsertId(Connection con) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql = "select last_insert_id()";
        BigInteger bi = new BigInteger(r.query(con, sql, new ScalarHandler<>()).toString());
        return Integer.parseInt(bi.toString());
    }

    public void insertOrderItem(Connection con, OrderItem item) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql = "insert into orderitem(price,amount,goods_id,order_id) values(?,?,?,?)";
        r.update(con, sql, item.getPrice(), item.getAmount(), item.getGoods().getId(), item.getOrder().getId());
    }

    public List<Order> selectAll(int userid) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from `order` where user_id=? order by datetime desc";
        return r.query(sql, new BeanListHandler<>(Order.class), userid);
    }

    public List<OrderItem> selectAllItem(int orderId) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select i.id,i.price,i.amount,g.name from orderitem i,goods g where order_id=? and i.goods_id=g.id";
        return r.query(sql, new BeanListHandler<>(OrderItem.class), orderId);
    }

    public int getOrderCount(int status) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql;
        if (status == 0) {
            sql = "select count(*) from `order`";
            return ((Long)r.query(sql, new ScalarHandler<>())).intValue();
        } else {
            sql = "select count(*) from `order` where status=?";
            return ((Long)r.query(sql, new ScalarHandler<>(), new Object[]{status})).intValue();
        }
    }

    public List<Order> selectOrderList(int status, int pageNumber, int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql;
        if (status == 0) {
            sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id order by o.datetime desc limit ?,?";
            return r.query(sql, new BeanListHandler<>(Order.class), (pageNumber - 1) * pageSize, pageSize);
        } else {
            sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id and o.status=? order by o.datetime desc limit ?,?";
            return r.query(sql, new BeanListHandler<>(Order.class), status, (pageNumber - 1) * pageSize, pageSize);
        }
    }

    public void updateStatus(int id, int status) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update `order` set status=? where id = ?";
        r.update(sql, status, id);
    }

    public void deleteOrder(Connection con, int id) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql = "delete from `order` where id = ?";
        r.update(con, sql, id);
    }

    public void deleteOrderItem(Connection con, int id) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql = "delete from orderitem where order_id=?";
        r.update(con, sql, id);
    }
}
