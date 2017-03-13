package govsystem.service;

import govsystem.domain.User;
import govsystem.formbean.backform.ModifyUserForm;

import java.util.List;

/**
 * Description: 后台业务
 * 具体业务：
 * 1.用户管理； 2.新闻管理 3.留言管理 4.问卷管理
 */
public interface BackService {
    /**
     * 列出所有用户
     * @return 用户List
     */
    List<User> listUsers();

    /**
     * 根据用户id删除用户
     * @param uid
     * @return
     */
    boolean deleteUserById(long uid);

    /**
     * 根据提交的表单内容（表单里是否有密码）修改用户信息
     * @param modifyUserForm
     * @return
     */
    boolean modifyUser(ModifyUserForm modifyUserForm);
}
