package govsystem.service;

import govsystem.domain.Admin;
import govsystem.domain.Message;
import govsystem.domain.News;
import govsystem.domain.User;
import govsystem.formbean.backform.AddNewsForm;
import govsystem.formbean.backform.ModifyNewsForm;
import govsystem.formbean.backform.ModifyUserForm;

import java.util.List;

/**
 * Description: 后台业务
 * 具体业务：
 * 1.用户管理； 2.新闻管理 3.留言管理 4.问卷管理
 */
public interface BackService {
    /**
     * 列出用户
     * @param username 为空时，列出所有用户
     * @return
     */
    List<User> listUsers(String username);

    /**
     * 根据用户id删除用户
     * @param uid
     * @return
     */
    boolean deleteUserById(int uid);

    /**
     * 根据提交的表单内容修改用户信息
     * @param modifyUserForm
     * @return
     */
    boolean modifyUser(ModifyUserForm modifyUserForm);

    /**
     * 列出新闻
     * @param nid 为空时候，列出所有新闻
     * @return
     */
    List<News> listNews(Integer nid);

    /**
     * 添加新闻
     * @param addNewsForm
     * @return
     */
    boolean addNews(AddNewsForm addNewsForm);

    /**
     * 根据id删除新闻
     * @param nid
     * @return 成功返回true
     */
    boolean deleteNewsById(int nid);

    /**
     * 根据提交的表单内容修改新闻信息
     * @param modifyNewsForm
     * @return
     */
    boolean modifyNews(ModifyNewsForm modifyNewsForm);

    /**
     * 列出新闻对应的留言
     * @param nid
     * @return
     */
    List<Message> listMessage(Integer nid);

    /**
     * 列出可以查看该新闻的用户
     * @param nid 新闻id
     * @return
     */
    List<User> listLookedUser(Integer nid);

    /**
     * 列出可以申请该新闻的用户
     * @param nid 新闻id
     * @return
     */
    List<User> listApplyUser(Integer nid);

    /**
     * 允许用户申请
     * @param nid
     * @param uid
     * @return
     */
     boolean allowApply(Integer nid,Integer uid);
    /**
     * 拒绝用户申请
     * @param nid
     * @param uid
     * @return
     */
     boolean refuseApply(Integer nid,Integer uid);
    /**
     * 拒绝用户查看
     * @param nid
     * @param uid
     * @return
     */
     boolean refuseView(Integer nid,Integer uid);

    /**
     * 删除留言
     * @param message
     * @return
     */
     boolean deleteMessage(Message message);

    /**
     * 列出所有普通管理员
     * @return
     */
    List<Admin> listAdmin();

    /**
     * 添加新管理员（默认是普通管理员，只有超级管理员可以添加）
     * @param admin
     * @return
     */
    boolean addAdmin(Admin admin);

    /**
     * 更新管理员信息
     * @param admin
     * @return
     */
    boolean modifyAdmin(Admin admin);

    /**
     * 根据id删除guan管理员
     * @param aid
     * @return 成功返回true
     */
    boolean deleteAdminById(int aid);

}
