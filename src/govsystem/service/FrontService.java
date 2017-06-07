package govsystem.service;

import govsystem.domain.*;
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
     * 实名验证
     * @param name
     * @param identitycode
     * @return
     */
    boolean authenticate(String name,String identitycode);


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
     * 列出符合特定用户的非公开信息
     * @param uid
     */
    List<News> listUserNews(int uid);

    /**
     * 添加申请
     * @param uid
     * @param nid
     * @return
     */
    boolean addApply(int uid,int nid);
    /**
     * 获得新闻对应的留言
     * @param nid 新闻编号
     * @return
     */
    List<Message> listMessage(int nid);

    /**
     * 留言
     * @param message
     * @return
     */
    boolean addMessage(Message message);

    /**
     * 列出所有听证视频
     * @return
     */
    List<Video> listAllVideo();

    /**
     * 根据文件名，获得Video
     * @param fileName
     * @return
     */
    Video getVideo(String fileName);

    /**
     * 根据Nid获得News
     * @return
     */
    News getNews(int nid);

    /**
     * 列出所有问卷
     * @return
     */
    List<Question> listAllQuestion();

    /**
     * 列出问卷对应的问题
     * @param question
     * @return
     */
    public List<QuestionItem> listAllQuestionItem(Question question);

    /**
     * 根据qid 获得Question
     * @param qid
     * @return
     */
    public  Question getQuestion(int qid);


    /**
     * 检查用户是否填写了问卷
     * @param uid
     * @param qid
     * @return 填写了 true
     */
    boolean checkCompleteQuestion(int uid,int qid);

    /**
     * 用户完成问卷调查
     * @param uid
     * @param qid
     * @param a
     * @param b
     * @param c
     * @param d
     * @return
     */
    boolean completeQuestion(int uid,int qid,int a,int b,int c,int d);
}
