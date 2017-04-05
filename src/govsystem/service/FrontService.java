package govsystem.service;

import govsystem.domain.Admin;
import govsystem.domain.News;
import govsystem.domain.User;
import govsystem.domain.Video;
import govsystem.formbean.frontform.LoginForm;
import govsystem.formbean.frontform.RegistForm;

import java.util.List;

/**
 * 前台业务接口
 * 具体业务：
 * 1.用户注册登录、用户设置；2.信息查看；3.发邮件、添加建议、问卷调查；4.查看视频
 *
 */
public interface FrontService {
    /**
     * 用户注册
     * @param registForm
     * @return 注册成功返回注册的用户，否则，返回null
     */
    User regist(RegistForm registForm);

    /**
     * 用户登录
     * @param loginForm
     * @return 登录成功返回User，返回null
     */
    User loginUser(LoginForm loginForm);

    /**
     *
     * @param loginForm
     * @return
     */
    Admin loginAdmin(LoginForm loginForm);


    /**
     * 个人设置，修改用户
     * @param user
     * @return
     */
    boolean modifyUser(User user);
    /**
     *  列出所有新闻
     */
    List<News> listAllNews();

    /**
     * 根据条件列出新闻
     * @param publicChoice 0列出非公开News，1列出公开News
     */
    List<News> listNews(int publicChoice);


    /**
     * 列出所有听证视频
     * @return
     */
    List<Video> listAllVideo();

    /**
     * 根据Nid获得News
     * @return
     */
    News getNews(int nid);
}
