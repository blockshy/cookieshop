package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.PriceUtils;

public class Order {
    private int id;
    private float total;
    private int amount;
    private int status;
    private int paytype;
    private String name;
    private String phone;
    private String address;
    private Date datetime;
    private User user;
    private Map<Integer, OrderItem> itemMap = new HashMap<>();
    private List<OrderItem> itemList = new ArrayList<>();

    public void setUsername(String username) {
        this.user = new User();
        this.user.setUsername(username);
    }

    public void addGoods(Goods g) {
        OrderItem item;
        if (this.itemMap.containsKey(g.getId())) {
            item = (OrderItem)this.itemMap.get(g.getId());
            item.setAmount(item.getAmount() + 1);
        } else {
            item = new OrderItem(g.getPrice(), 1, g, this);
            this.itemMap.put(g.getId(), item);
        }

        ++this.amount;
        this.total = PriceUtils.add(this.total, g.getPrice());
    }

    public List<OrderItem> getItemList() {
        return this.itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public void lessen(int goodsid) {
        if (this.itemMap.containsKey(goodsid)) {
            OrderItem item = (OrderItem)this.itemMap.get(goodsid);
            item.setAmount(item.getAmount() - 1);
            --this.amount;
            this.total = PriceUtils.subtract(this.total, item.getPrice());
            if (item.getAmount() <= 0) {
                this.itemMap.remove(goodsid);
            }
        }

    }

    public void delete(int goodsid) {
        if (this.itemMap.containsKey(goodsid)) {
            OrderItem item = (OrderItem)this.itemMap.get(goodsid);
            this.total = PriceUtils.subtract(this.total, (float)item.getAmount() * item.getPrice());
            this.amount -= item.getAmount();
            this.itemMap.remove(goodsid);
        }

    }

    public Map<Integer, OrderItem> getItemMap() {
        return this.itemMap;
    }

    public void setItemMap(Map<Integer, OrderItem> itemMap) {
        this.itemMap = itemMap;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPaytype() {
        return this.paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDatetime() {
        return this.datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order() {
    }
}
