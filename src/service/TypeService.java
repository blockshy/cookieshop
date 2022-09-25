package service;

import dao.TypeDao;
import java.sql.SQLException;
import java.util.List;
import model.Type;

public class TypeService {
    TypeDao tDao = new TypeDao();

    public TypeService() {
    }

    public List<Type> GetAllType() {
        List<Type> list = null;

        try {
            list = this.tDao.GetAllType();
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

        return list;
    }

    public Type selectTypeNameByID(int typeid) {
        Type type = null;

        try {
            type = this.tDao.selectTypeNameByID(typeid);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return type;
    }

    public Type select(int id) {
        Type t = null;

        try {
            t = this.tDao.select(id);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return t;
    }

    public void insert(Type t) {
        try {
            this.tDao.insert(t);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void update(Type t) {
        try {
            this.tDao.update(t);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public boolean delete(int id) {
        try {
            this.tDao.delete(id);
            return true;
        } catch (SQLException var3) {
            var3.printStackTrace();
            return false;
        }
    }
}
