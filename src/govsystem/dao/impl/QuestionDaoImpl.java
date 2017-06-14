package govsystem.dao.impl;

import govsystem.dao.QuestionDao;
import govsystem.domain.Question;
import govsystem.domain.QuestionItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Created by Myth on 3/23/2017.
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    class QuestionRowMapper implements RowMapper<Question> {
        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
            Question question = new Question();
            question.setQid(rs.getInt("qid"));
            question.setTitle(rs.getString("title"));
            question.setAid(rs.getInt("aid"));
            question.setPostTime(rs.getString("posttime"));
            question.setAllNum(rs.getInt("all_num"));
            question.setaNum(rs.getInt("a_num"));
            question.setbNum(rs.getInt("b_num"));
            question.setcNum(rs.getInt("c_num"));
            question.setdNum(rs.getInt("d_num"));
            question.setAvailable(rs.getInt("available"));
            question.setName(rs.getString("name"));
            return question;
        }
    }
    class QuestionItemRowMapper implements RowMapper<QuestionItem> {
        public QuestionItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            QuestionItem questionItem = new QuestionItem();
            questionItem.setQid(rs.getInt("qid"));
            questionItem.setNo(rs.getInt("no"));
            questionItem.setContent(rs.getString("content"));
            return questionItem;
        }
    }

    @Override
    public List<Question> list() {
        List<Question> questionList;
        String sql = "select * from tb_question,tb_admin where tb_question.aid=tb_admin.aid";
        try {
            questionList = jdbcTemplate.query(sql, new QuestionRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  questionList;
    }

    @Override
    public Question get(int qid) {
        List<Question> questionList;
        String sql = "select * from tb_question,tb_admin where tb_question.aid=tb_admin.aid and qid=?";
        try {
            questionList = jdbcTemplate.query(sql,new QuestionRowMapper(),qid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (questionList != null && questionList.size() == 1) {
            return questionList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<QuestionItem> listItem(Question question) {
        if (question == null) return null;
        int qid = question.getQid();
        List<QuestionItem> questionItemList;
        String sql = "select * from tb_question_item where qid="+ qid;
        try {
            questionItemList = jdbcTemplate.query(sql, new QuestionItemRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  questionItemList;
    }

    @Override
    public boolean add(Question question) {
        String sql = "insert into tb_question(title,aid,posttime,available) values(?,?,now(),?)";
        int affectedNum = 0;
        try {
            affectedNum = jdbcTemplate.update(sql,question.getTitle(),question.getAid(),question.getAvailable());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (affectedNum != 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean addItem(QuestionItem questionItem) {
        String sql = "insert into tb_question_item(qid,content) values(?,?)";
        int affectedNum = 0;
        try {
            affectedNum = jdbcTemplate.update(sql,questionItem.getQid(),questionItem.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (affectedNum != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Question question) {

        int qid = question.getQid();
        String sql1 = "delete from tb_question_user where qid=?";
        String sql2 = "delete from tb_question_item where qid=?";
        String sql3 = "delete from tb_question where qid=?";
        int affectedNum1 = 0;
        int affectedNum2 = 0;
        int affectedNum3 = 0;
        try {
            affectedNum1 = jdbcTemplate.update(sql1,qid);
            affectedNum2 = jdbcTemplate.update(sql2,qid);
            affectedNum3 = jdbcTemplate.update(sql3,qid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (affectedNum1 != 0 || affectedNum2 != 0 || affectedNum3 != 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deleteItem(QuestionItem questionItem) {
        int qid = questionItem.getQid();
        int no = questionItem.getNo();
        String sql = "delete from tb_question_item where qid=? and no=?";
        int affectedNum = 0;
        try {
            affectedNum = jdbcTemplate.update(sql,qid,no);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (affectedNum != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean modifyQuestionTitle(Question question) {
        String sql = "";
        int effectedNum = 0;
        sql = "update tb_question set available=?,title=? where qid=?";
        try {
            effectedNum = jdbcTemplate.update(sql,question.getAvailable(),question.getTitle(),question.getQid());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (effectedNum == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean modifyQuestionNum(int qid,int a, int b, int c, int d) {
        String sql = "";
        int effectedNum = 0;
        sql = "update tb_question set all_num=all_num+1,a_num=a_num+?,b_num=b_num+?,c_num=c_num+?,d_num=d_num+? where qid=?";
        try {
            effectedNum = jdbcTemplate.update(sql,a,b,c,d,qid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (effectedNum == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isComplete(int uid, int qid) {
        List<Question> questionList;
        String sql = "select count(*) from tb_question_user where uid=? and qid=?";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql,Integer.class,uid,qid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addUserCompleteItem(int uid, int qid) {
        String sql = "insert into tb_question_user(uid,qid) values(?,?)";
        int affectedNum = 0;
        try {
            affectedNum = jdbcTemplate.update(sql,uid,qid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (affectedNum != 0) {
            return true;
        } else {
            return false;
        }
    }
}
