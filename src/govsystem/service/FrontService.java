package govsystem.service;

import govsystem.domain.Admin;
import govsystem.domain.User;
import govsystem.formbean.frontform.LoginForm;
import govsystem.formbean.frontform.RegistForm;
import govsystem.formbean.frontform.SettingForm;

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

    //TODO: 验证码

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
     * 用户设置个人信息
     * @param settingForm
     * @return 设置成功返回该对象，否则返回null
     */
    User setting(SettingForm settingForm);


    /**
     *  列出所有新闻
     */
//    void listAllNews();
//
//    void listPublicNews();
//    void listPrivateNews();
}
