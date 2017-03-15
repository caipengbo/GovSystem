package govsystem.dao;

import govsystem.domain.User;

import java.util.List;

/**
 * Description: 用户DAO
 * Created by Myth on 3/12/2017.
 */
public interface UserDao {
    /**
     * 增加用户
     * @param user
     * @return 添加成功返回true
     */
    boolean add(User user);

    /**
     * 删除用户
     * @param user
     * @return 成功返回true
     */
    boolean delete(User user);

    /**
     * 查找用户(根据用户名和密码)
     * @param user
     * @return
     */
    User search(User user);

    /**
     * 根据username列出用户
     * @param user（username为空时，列出所有用户）
     * @return
     */
    List<User> list(User user);

    /**
     * 修改用户信息(根据Uid查找用户)
     * @param user
     * @return 成功返回true
     */
    boolean update(User user);


}
