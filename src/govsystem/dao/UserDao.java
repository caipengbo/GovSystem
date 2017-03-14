package govsystem.dao;

import govsystem.domain.User;

import java.util.List;

/**
 * Description:
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
     * @param uid
     * @return 成功返回true
     */
    boolean deleteUserById(long uid);

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return 找到返回User对象，否则返回null
     */
    User searchUser(String username,String password);

    /**
     * 修改用户信息(根据Uid查找用户)
     * @param user
     * @return 成功返回true
     */
    boolean updateAll(User user);

    /**
     * 修改除了密码之外的用户信息(根据Uid查找用户)
     * @param user
     * @return 成功返回true
     */
    boolean updateExceptPsw(User user);

    /**
     * 列出所有用户信息
     * @return User列表
     */
    List<User> listAllUser();
}