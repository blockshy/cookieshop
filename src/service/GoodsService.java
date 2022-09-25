package service;

import dao.GoodsDao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import model.Goods;
import model.Page;

public class GoodsService {
    private final GoodsDao gDao = new GoodsDao();

    public GoodsService() {
    }

    public List<Map<String, Object>> getGoodsList(int recommendType) {
        List<Map<String, Object>> list = null;

        try {
            list = this.gDao.getGoodsList(recommendType);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return list;
    }

    public List<Map<String, Object>> getScrollGood() {
        List<Map<String, Object>> list = null;

        try {
            list = this.gDao.getScrollGood();
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

        return list;
    }

    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) {
        List<Goods> list = null;

        try {
            list = this.gDao.selectGoodsByTypeID(typeID, pageNumber, pageSize);
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return list;
    }

    public Page<Goods> selectPageByTypeID(int typeID, int pageNumber) {
        Page<Goods> p = new Page<>();
        p.setPageNumber(pageNumber);
        int totalCount = 0;

        try {
            totalCount = this.gDao.getCountOfGoodsByTypeID(typeID);
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

        p.SetPageSizeAndTotalCount(8, totalCount);
        List<Goods> list = null;

        try {
            list = this.gDao.selectGoodsByTypeID(typeID, pageNumber, 8);
        } catch (SQLException var7) {
            var7.printStackTrace();
        }

        p.setList(list);
        return p;
    }

    public Page<Goods> getGoodsRecommendPage(int type, int pageNumber) {
        Page<Goods> p = new Page<>();
        p.setPageNumber(pageNumber);
        int totalCount = 0;

        try {
            totalCount = this.gDao.getRecommendCountOfGoodsByTypeID(type);
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

        p.SetPageSizeAndTotalCount(8, totalCount);
        List<Goods> list = null;

        try {
            list = this.gDao.selectGoodsByRecommend(type, pageNumber, 8);

            for (Goods g : list) {
                g.setScroll(this.gDao.isScroll(g));
                g.setHot(this.gDao.isHot(g));
                g.setNew(this.gDao.isNew(g));
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        }
        p.setList(list);
        return p;
    }

    public Goods getGoodsById(int id) {
        Goods g = null;

        try {
            g = this.gDao.getGoodsById(id);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return g;
    }

    public Page<Goods> getSearchGoodsPage(String keyword, int pageNumber) {
        Page<Goods> p = new Page<>();
        p.setPageNumber(pageNumber);
        int totalCount = 0;

        try {
            totalCount = this.gDao.getSearchCount(keyword);
        } catch (SQLException var8) {
            var8.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List<Goods> list = null;
        try {
            list = this.gDao.selectSearchGoods(keyword, pageNumber, 8);
            //System.out.println(Arrays.toString(list.toArray()));
        } catch (SQLException var7) {
            var7.printStackTrace();
        }
        //System.out.println(Collections.singletonList(list));
        p.setList(list);
        return p;
    }

    public void addRecommend(int id, int type) {
        try {
            this.gDao.addRecommend(id, type);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public void removeRecommend(int id, int type) {
        try {
            this.gDao.removeRecommend(id, type);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public void insert(Goods goods) {
        try {
            this.gDao.insert(goods);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void update(Goods goods) {
        try {
            this.gDao.update(goods);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            this.gDao.delete(id);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }
}
