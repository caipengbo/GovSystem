package govsystem.domain;

/**
 * Description: 问卷调查中的具体问题
 * Created by Myth on 3/23/2017.
 */
public class QuestionItem {
    private int qid;
    private int no;
    private String  content; //具体的问题内容，选项默认是四个：非常满意、比较满意、一般、不满意

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
