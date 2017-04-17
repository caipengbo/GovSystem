package govsystem.dao.impl;

import govsystem.dao.MessageDao;
import govsystem.domain.Message;
import govsystem.domain.News;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Created by Myth on 3/15/2017.
 */
@Repository
public class MessageDaoImpl implements MessageDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    //内部类，用于结果与Message的映射
    class MessageRowMapper implements RowMapper<Message> {
        //rs为返回结果集，以每行为单位封装
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            Message message = new Message();
            message.setMid(rs.getInt("mid"));
            message.setContent(rs.getString("content"));
            message.setPostTime(rs.getString("posttime"));
            message.setUid(rs.getInt("uid"));
            message.setName(rs.getString("name"));
            message.setNid(rs.getInt("nid"));
            message.setState(rs.getInt("state"));
            return message;
        }
    }

    @Override
    public boolean add(Message message) {
        String sql1 = "insert into tb_message(content,posttime,uid,nid)values(?,now(),?,?)";
        String sql2 = "update tb_news set messagenum=messagenum+1 where nid=?";
        int affectedNum1 = 0;
        int affectedNum2 = 0;
        try {
            affectedNum1 = jdbcTemplate.update(sql1,message.getContent(),message.getUid(),message.getNid());
            affectedNum2 = jdbcTemplate.update(sql2,message.getNid());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (affectedNum1 != 0 && affectedNum2 != 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(Message message) {
        int mid = message.getMid();
        int nid = message.getNid();
        String sql1 = "update tb_news set messagenum=messagenum-1 where nid=?";
        String sql2 = "delete from tb_message where mid=?";
        int affectedNum1 = 0;
        int affectedNum2 = 0;
        try {
            affectedNum1 = jdbcTemplate.update(sql1,nid);
            affectedNum2 = jdbcTemplate.update(sql2,mid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (affectedNum1 != 0 || affectedNum2 != 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Message> list(News news) {
        List<Message> messageList;
        String sql = null;
        if (news == null) {
            return null;
        } else {
            sql = "select * from tb_message,tb_user where tb_message.uid=tb_user.uid and nid="+ news.getNid();
        }
        try {
            messageList = jdbcTemplate.query(sql, new MessageRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  messageList;
    }

    @Override
    public List<Message> listChecked(News news) {
        List<Message> messageList;
        String sql = null;
        if (news == null) {
            return null;
        } else {
            sql = "select * from tb_message,tb_user where tb_message.uid=tb_user.uid and state=1 and nid="+ news.getNid();
        }
        try {
            messageList = jdbcTemplate.query(sql, new MessageRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  messageList;
    }

    @Override
    public boolean update(Message message) {
        int effectedNum = 0;
        String sql = "update tb_message set state=? where mid=?";
        try {
            effectedNum = jdbcTemplate.update(sql,message.getState(),message.getMid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (effectedNum == 0) {
            return false;
        } else {
            return true;
        }
    }
}
