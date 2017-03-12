package govsystem.dao.impl;

import govsystem.dao.UserDao;
import govsystem.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            user.setIdentifyCode(rs.getString("identifycode"));
            user.setIndetityFlag(rs.getInt("indetityflag"));
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
    public boolean del(User user) {
        return false;
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
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> listAllUser() {
        List<User> userList = new ArrayList<>();
        String sql = "select * from tb_user";
        try {
            userList = jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
        return  userList;
    }


}
