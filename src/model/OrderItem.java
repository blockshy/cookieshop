package model;

public class OrderItem {
    private int id;
    private float price;
    private int amount;
    private String goodsName;
    private Goods goods;
    private Order order;

    public void setName(String name) {
        this.goodsName = name;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Goods getGoods() {
        return this.goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderItem() {
    }

    public OrderItem(float price, int amount, Goods goods, Order order) {
        this.price = price;
        this.amount = amount;
        this.goods = goods;
        this.order = order;
    }
}
