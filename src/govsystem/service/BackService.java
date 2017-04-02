package govsystem.service;

import govsystem.domain.*;
import govsystem.formbean.backform.*;

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
     * @param news
     * @return
     */
    boolean addNews(News news);

    /**
     * 根据id删除新闻
     * @param nid
     * @return 成功返回true
     */
    boolean deleteNewsById(int nid);

    /**
     * 根据提交的表单内容修改新闻信息
     * @param news
     * @return
     */
    boolean modifyNews(News news);

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

    /**
     * 列出所有问卷
     * @return
     */
    List<Question> listQuestion();

    /**
     * 列出问卷对应的具体问题
     * @param question
     * @return
     */
    List<QuestionItem> listQuetionItem(Question question);

    /**
     * 删除问卷
     * @param question
     * @return
     */
    boolean deleteQuestion(Question question);

    /**
     * 删除问卷问题
     * @param questionItem
     * @return
     */
    boolean deleteQuestionItem(QuestionItem questionItem);

    /**
     * 添加问卷
     * @param question
     * @return
     */
    boolean addQuestion(Question question);

    /**
     * 添加问卷 的问题
     * @param addQuestionItemForm
     * @return
     */
    boolean addQuestionItem(AddQuestionItemForm addQuestionItemForm);

    /**
     * 修改问卷
     * @param question
     * @return
     */
    boolean modifyQuestion(Question question);

    /**
     * 添加 听证视频
     * @param video
     * @return
     */
    boolean addVideo(Video video);

    /**
     * 根据vid删除Video
     * @param vid
     * @return
     */
    boolean deleteVideo(int vid);

    /**
     * 更新Video内容（但是不可更改商品内容）
     * @param video
     * @return
     */
    boolean updateVideo(Video video);

    /**
     * 列出所有Video
     * @return
     */
    List<Video> listVideo();

}
