package govsystem.dao;

import govsystem.domain.News;
import govsystem.domain.User;

import java.util.List;

/**
 * Description:
 * Created by Myth on 3/14/2017.
 */
public interface NewsDao {
    /**
     * 增加新闻
     * @param news
     * @return 添加成功返回true
     */
    boolean add(News news);

    /**
     * 删除新闻（根据nid）
     * @param news
     * @return 成功返回true
     */
    boolean delete(News news);

    /**
     * 修改新闻信息
     * @param news
     * @return 成功返回true
     */
    boolean update(News news);

    /**
     * 根据nid列出新闻
     * @param news（nid为空时候，列出所有用户）
     * @return
     */
    List<News> list(News news);

    /**
     * 根据 ispublic 0,1列出 News
     * @param publicChoice 0列出非公开News，1列出公开News
     * @return
     */
    List<News> list(int publicChoice);

    /**
     * 根据新闻，列出可以查看该新闻的用户
     * @param news
     * @return
     */
    public List<User> listLookedUsers(News news);

    /**
     * 列出可以申请查看该新闻的用户
     * @param news 新闻号
     * @return
     */
    public List<User> listApplyUsers(News news);

    /**
     * 允许用户的申请（需要修改tb_user_news和tb_news表中的）
     * @param news
     * @param user
     * @return
     */
    public boolean allowApply(News news,User user);

    /**
     * 拒绝用户的申请（修改tb_user_news和tb_news表中的）
     * @param news
     * @param user
     * @return
     */
    public boolean refuseApply(News news,User user);
    /**
     * 拒绝用户查看（修改tb_user_news和tb_news表中的）
     * @param news
     * @param user
     * @return
     */
    public boolean refuseView(News news,User user);

}
