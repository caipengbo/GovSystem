package govsystem.dao.impl;

import govsystem.dao.NewsDao;
import govsystem.domain.News;
import govsystem.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Created by Myth on 3/14/2017.
 */
@Repository
public class NewsDaoImpl implements NewsDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    //内部类，用于结果与News的映射
    class NewsRowMapper implements RowMapper<News> {
        public News mapRow(ResultSet rs, int rowNum) throws SQLException {
            News news = new News();
            news.setNid(rs.getInt("nid"));
            news.setTitle(rs.getString("title"));
            news.setDigest(rs.getString("digest"));
            news.setContent(rs.getString("content"));
            news.setPostTime(rs.getString("posttime"));
            news.setApplyNum(rs.getInt("applynum"));
            news.setLookedNum(rs.getInt("lookednum"));
            news.setMessageNum(rs.getInt("messagenum"));
            news.setIsPublic(rs.getInt("ispublic"));
            news.setName(rs.getString("name"));
            return news;
        }
    }
    class NewsRowMapper2 implements RowMapper<News> {
        //有status
        public News mapRow(ResultSet rs, int rowNum) throws SQLException {
            News news = new News();
            news.setNid(rs.getInt("nid"));
            news.setTitle(rs.getString("title"));
            news.setDigest(rs.getString("digest"));
            news.setContent(rs.getString("content"));
            news.setPostTime(rs.getString("posttime"));
            news.setApplyNum(rs.getInt("applynum"));
            news.setLookedNum(rs.getInt("lookednum"));
            news.setMessageNum(rs.getInt("messagenum"));
            news.setIsPublic(rs.getInt("ispublic"));
            news.setName(rs.getString("name"));
            news.setStatus(rs.getInt("status"));
            return news;
        }
    }


    class UserRowMapper implements RowMapper<User> {
        //rs为返回结果集，以每行为单位封装着
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUid(rs.getInt("uid"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("name"));
            user.setBirthday(rs.getString("birthday"));
            user.setIdentityCode(rs.getString("identitycode"));
            user.setIdentityFlag(rs.getInt("identityflag"));
            return user;
        }

    }

    @Override
    public boolean add(News news) {
        String sql = "insert into tb_news(title,digest,content,posttime,applynum," +
                "lookednum,messagenum,ispublic,aid) values(?,?,?,now(),?,?,?,?,?)";
        int affectedNum = 0;
        try {
            affectedNum = jdbcTemplate.update(sql,news.getTitle(),news.getDigest(),news.getContent()
            ,news.getApplyNum(),news.getLookedNum(),news.getMessageNum(),news.getIsPublic(),news.getAid());
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
    public boolean delete(News news) {
        int nid = news.getNid();
        String sql1 = "delete from tb_message where nid=?";
        String sql2 = "delete from tb_user_news where nid=?";
        String sql3 = "delete from tb_news where nid=?";
        int affectedNum1 = 0;
        int affectedNum2 = 0;
        int affectedNum3 = 0;
        try {
            affectedNum1 = jdbcTemplate.update(sql1,nid);
            affectedNum2 = jdbcTemplate.update(sql2,nid);
            affectedNum3 = jdbcTemplate.update(sql3,nid);
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
    public boolean update(News news) {
        String sql = "";
        int effectedNum = 0;
        sql = "update tb_news set title=?,digest=?,content=?,postTime=now(),isPublic=?,aid=? where nid=?";
        try {
            effectedNum = jdbcTemplate.update(sql,news.getTitle(),news.getDigest(),news.getContent(),news.getIsPublic(),news.getAid(),news.getNid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (effectedNum == 0) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public List<News> list(News news) {
        int nid;
        if (news == null) {
            nid = -1;
        } else {
            nid = news.getNid();  //nid=-1代表nid为空
        }
        List<News> newsList;
        String sql = null;
        if (nid == -1) {
            sql = "select * from tb_news,tb_admin where tb_news.aid=tb_admin.aid";
        } else {
            sql = "select * from tb_news,tb_admin where tb_news.aid=tb_admin.aid and nid="+ nid;
        }
        try {
            newsList = jdbcTemplate.query(sql, new NewsRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  newsList;
    }

    @Override
    public List<News> list(int publicChoice) {
        if (publicChoice!=0 && publicChoice!=1) {
            return null;
        }
        List<News> newsList;
        String sql = null;
        sql = "select * from tb_news,tb_admin where tb_news.aid=tb_admin.aid and ispublic=" + publicChoice;
        try {
            newsList = jdbcTemplate.query(sql, new NewsRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  newsList;
    }

    @Override
    public List<News> listSelectedNews(int uid) {
        List<News> newsList;
        List<News> addList;
        String sql1, sql2;
        sql1 = "select * from tb_news,tb_admin where tb_news.aid=tb_admin.aid and ispublic=0 " +
                "and nid not in (select nid from tb_user_news where uid=?)";
        sql2 = "select * from tb_news,tb_user_news,tb_admin where tb_news.aid=tb_admin.aid and tb_news.nid=tb_user_news.nid and uid=?";
        try {
            newsList = jdbcTemplate.query(sql1, new NewsRowMapper(),uid);
            addList = jdbcTemplate.query(sql2, new NewsRowMapper2(),uid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        newsList.addAll(addList);
        return  newsList;
    }

    @Override
    public List<User> listLookedUsers(News news) {
        if (news == null) {
            return null;
        }
        List<User> userList;
        String sql = "select * from tb_user_news,tb_user where tb_user_news.uid=tb_user.uid and status=2 and nid=" + news.getNid();
        try {
            userList = jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  userList;
    }

    @Override
    public List<User> listApplyUsers(News news) {
        if (news == null) {
            return null;
        }
        List<User> userList;
        String sql = "select * from tb_user_news,tb_user where tb_user_news.uid=tb_user.uid and status=1 and nid=" + news.getNid();
        try {
            userList = jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  userList;
    }

    @Override
    public boolean addApply(News news, User user) {
        String sql1 = "insert into tb_user_news(uid,nid,status) values(?,?,1)";
        String sql2 = "update tb_news set applynum=applynum+1 where nid=?";
        int affectedNum1 = 0;
        int affectedNum2 = 0;
        try {
            affectedNum1 = jdbcTemplate.update(sql1,user.getUid(),news.getNid());
            affectedNum2 = jdbcTemplate.update(sql2,news.getNid());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (affectedNum1 != 0 && affectedNum2!=0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean allowApply(News news,User user) {
        int effectedNum1 = 0;
        int effectedNum2 = 0;
        String sql1 = "update tb_user_news set status=2 where nid=? and uid=?";
        String sql2 = "update tb_news set lookednum=lookednum+1,applynum=applynum-1 where nid=?";
        try {
            effectedNum1 = jdbcTemplate.update(sql1,news.getNid(),user.getUid());
            effectedNum2 = jdbcTemplate.update(sql2,news.getNid());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (effectedNum1 == 0 || effectedNum2 == 0) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public boolean refuseApply(News news,User user) {
        int nid = news.getNid();
        int uid = user.getUid();
        String sql1 = "delete from tb_user_news where nid=? and uid=?";
        String sql2 = "update tb_news set applynum=applynum-1 where nid=?";
        int affectedNum1 = 0;
        int affectedNum2 = 0;
        try {
            affectedNum1 = jdbcTemplate.update(sql1,nid,uid);
            affectedNum2 = jdbcTemplate.update(sql2,nid);
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
    public boolean refuseView(News news,User user) {
        int nid = news.getNid();
        int uid = user.getUid();
        String sql1 = "delete from tb_user_news where nid=? and uid=?";
        String sql2 = "update tb_news set lookednum=lookednum-1 where nid=?";
        int affectedNum1 = 0;
        int affectedNum2 = 0;
        try {
            affectedNum1 = jdbcTemplate.update(sql1,nid,uid);
            affectedNum2 = jdbcTemplate.update(sql2,nid);
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
}
