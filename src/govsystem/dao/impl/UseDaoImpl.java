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
        String sql = "insert into tb_user(username,password,name) " +
                "values(?,?,?)";
        int affectedNum = jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),
                user.getName());

        if (affectedNum != 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(User user) {
        int uid = user.getUid();
        String sql = "delete from tb_user where uid=?"; //在数据库中设置级联删除
        int affectedNum = jdbcTemplate.update(sql,uid);

        if (affectedNum != 0) {
                return true;
        } else {
                return false;
        }

    }


    @Override
    public User search(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String sql = "select * from tb_user where username=? and password=?";
        try {
            user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), username, password);
        } catch (Exception e) {
            return null;
        }
        return  user;
    }

    @Override
    public boolean update(User user) {
        String sql = "";
        int effectedNum = 0;
        sql = "update tb_user set username=?,password=?,name=?,birthday=?,identitycode=?,identityflag=? where uid=?";
        try {
            effectedNum = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(),
                    user.getBirthday(), user.getIdentityCode(), user.getIdentityFlag(), user.getUid());
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
    public boolean authenticate(String name, String identitycode) {
        String sql = "select count(*) from tb_library where name=? and identitycode=?";

        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql,Integer.class,name,identitycode);
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
    public List<User> list(User user) {
        String username = user.getUsername();
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
