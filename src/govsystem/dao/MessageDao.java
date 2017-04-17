package govsystem.dao;

import govsystem.domain.Message;
import govsystem.domain.News;

import java.util.List;

/**
 * Description: 留言DAO
 * Created by Myth on 3/15/2017.
 */
public interface MessageDao {
    /**
     * 增加留言
     * @param message
     * @return
     */
    public boolean add(Message message);

    /**
     * 删除留言
     * @param message
     * @return
     */
    public boolean delete(Message message);

    /**
     * 根据新闻，列出对应所有留言
     * @param news
     * @return
     */
    public List<Message> list(News news);

    /**
     * 根据新闻，列出对应审核通过留言
     * @param news
     * @return
     */
    public List<Message> listChecked(News news);

    /**
     * 更新留言(需要更新的内容仅有state)
     * @param message
     * @return
     */
    public boolean update(Message message);
}
