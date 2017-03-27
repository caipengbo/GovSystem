package govsystem.dao;

import govsystem.domain.Question;
import govsystem.domain.QuestionItem;

import java.util.List;

/**
 * Description:
 * Created by Myth on 3/23/2017.
 */
public interface QuestionDao {
    /**
     * 列出所有问卷
     * @return
     */
    List<Question> list();

    /**
     * 列出问卷对应的问题
     * @param question
     * @return
     */
    List<QuestionItem> listItem(Question question);

    /**
     * 添加问卷
     * @param question
     * @return
     */
    boolean add(Question question);

    /**
     * 添加问卷下的问题
     * @param questionItem
     * @return
     */
    boolean addItem(QuestionItem questionItem);

    /**
     * 删除问卷
     * @param question
     * @return
     */
    boolean delete(Question question);
    /**
     * 删除问题
     * @param questionItem
     * @return
     */
    boolean deleteItem(QuestionItem questionItem);

    /**
     * 修改问卷的标题
     * @param question
     * @return
     */
    boolean modifyQuestion(Question question);
}
