package govsystem.dao;

import govsystem.domain.Admin;

import java.util.List;

/**
 * Description: 管理员类
 * Created by Myth on 3/18/2017.
 */
public interface AdminDao {
    /**
     * 根据用户名和密码查找管理员
     * @param admin
     * @return 查找失败返回null
     */
    Admin search(Admin admin);
    /**
     * 增加管理员
     * @param admin
     * @return 添加成功返回true
     */
    boolean add(Admin admin);

    /**
     * 删除用户
     * @param admin
     * @return 成功返回true
     */
    boolean delete(Admin admin);

    /**
     * 列出所有普通管理员
     * @return
     */
    List<Admin> list();

    /**
     * 修改用户信息(根据Aid查找用户)
     * @param admin
     * @return 成功返回true
     */
    boolean update(Admin admin);
}
