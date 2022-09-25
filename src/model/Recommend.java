package model;

public class Recommend {
    private int id;
    private int type;
    private Goods goods;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Goods getGoods() {
        return this.goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Recommend(int id, int type, Goods goods) {
        this.id = id;
        this.type = type;
        this.goods = goods;
    }

    public Recommend() {
    }
}
