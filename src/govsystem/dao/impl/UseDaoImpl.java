package govsystem.dao.impl;

import govsystem.dao.UserDao;
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
 * Created by Myth on 3/12/2017.
 */
@Repository
public class UseDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    //内部类，用于结果与User的映射
    class UserRowMapper implements RowMapper<User> {
        //rs为返回结果集，以每行为单位封装着
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUid(rs.getInt("uid"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setBirthday(rs.getString("birthday"));
            user.setIdentityCode(rs.getString("identitycode"));
            user.setIdentityFlag(rs.getInt("identityflag"));
            return user;
        }

    }

    @Override
    public boolean add(User user) {
        String sql = "insert into tb_user(username,password,name,birthday) " +
                "values(?,?,?,?)";
        int affectedNum = jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),
                user.getName(),user.getBirthday());

        if (affectedNum != 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deleteUserById(int uid) {
        String sql = "delete from tb_user where uid=?";

        int affectedNum = jdbcTemplate.update(sql,uid);

        if (affectedNum != 0) {
                return true;
        } else {
                return false;
        }

    }


    @Override
    public User searchUser(String username, String password) {
        String sql = "select * from tb_user where username=? and password=?";
        User user = new User();
        try {
            user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), username, password);
        } catch (Exception e) {
            return null;
        }
        return  user;
    }

    @Override
    public boolean updateAll(User user) {
        String sql = "";
        int effectedNum = 0;
        sql = "update tb_user set username=?,password=?,name=?,birthday=?,identitycode=?,identityflag=? where uid=?";
        effectedNum = jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getName(),
                user.getBirthday(), user.getIdentityCode(),user.getIdentityFlag(),user.getUid());
        if (effectedNum == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean updateExceptPsw(User user) {
        String sql = "";
        int effectedNum = 0;
        sql = "update tb_user set username=?,name=?,birthday=?,identitycode=?,identityflag=? where uid=?";
        try {
            effectedNum = jdbcTemplate.update(sql,user.getUsername(),user.getName(),
                    user.getBirthday(), user.getIdentityCode(),user.getIdentityFlag(),user.getUid());
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
    public List<User> listUser(String username) {
        List<User> userList;
        if (username == null) {
            username = "";
        }
        String sql = "select * from tb_user where username like '"+ username + "%'";
        try {
            userList = jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  userList;
    }


}
