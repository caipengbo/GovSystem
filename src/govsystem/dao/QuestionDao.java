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
     * 根据id获得question
     * @param qid
     * @return
     */
    Question get(int qid);

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
    boolean modifyQuestionTitle(Question question);

    /**
     * 修改统计参数（选择abcd各自的数目）
     * @param a
     * @param b
     * @param c
     * @param d
     * @return
     */
    boolean modifyQuestionNum(int qid,int a,int b,int c,int d);

    /**
     * 检查用户是否填写了问卷（查询tb_question_user）
     * @param uid
     * @param qid
     * @return
     */
    boolean isComplete(int uid,int qid);

    /**
     * 当用户填完问卷时,记录下来
     * @param uid
     * @param qid
     * @return
     */
    boolean addUserCompleteItem(int uid,int qid);

}
