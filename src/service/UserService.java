package service;

import dao.UserDao;
import java.sql.SQLException;
import java.util.List;
import model.Page;
import model.User;

public class UserService {
    private final UserDao uDao = new UserDao();

    public UserService() {
    }

    public boolean register(User user) {
        try {
            if (this.uDao.isUsernameExist(user.getUsername())) {
                return false;
            } else if (this.uDao.isEmailExist(user.getEmail())) {
                return false;
            } else {
                this.uDao.addUser(user);
                return true;
            }
        } catch (SQLException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public User login(String ue, String password) {
        User user = null;

        try {
            user = this.uDao.selectByUsernamePassword(ue, password);
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        if (user == null) {
            try {
                user = this.uDao.selectByEmailPassword(ue, password);
            } catch (SQLException var5) {
                var5.printStackTrace();
            }
        }
        return user;
    }

    public User selectById(int id) {
        User u = null;

        try {
            u = this.uDao.selectById(id);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return u;
    }

    public void updateUserAddress(User user) {
        try {
            this.uDao.updateUserAddress(user);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void updatePwd(User user) {
        try {
            this.uDao.updatePwd(user);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public Page<User> getUserPage(int pageNumber) {
        Page<User> p = new Page<>();
        p.setPageNumber(pageNumber);
        int pageSize = 7;
        int totalCount = 0;

        try {
            totalCount = this.uDao.selectUserCount();
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List<User> list = null;

        try {
            list = this.uDao.selectUserList(pageNumber, pageSize);
        } catch (SQLException var7) {
            var7.printStackTrace();
        }

        p.setList(list);
        return p;
    }

    public boolean delete(int id) {
        try {
            this.uDao.delete(id);
            return true;
        } catch (SQLException var3) {
            var3.printStackTrace();
            return false;
        }
    }
}
