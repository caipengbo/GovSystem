package govsystem.dao.impl;

import govsystem.dao.AdminDao;
import govsystem.domain.Admin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Created by Myth on 3/18/2017.
 */
@Repository
public class AdminDaoImpl implements AdminDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    class AdminRowMapper implements RowMapper<Admin> {
        //rs为返回结果集，以每行为单位封装着
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setAid(rs.getInt("aid"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            admin.setName(rs.getString("name"));
            admin.setBirthday(rs.getString("birthday"));
            admin.setPrivilege(rs.getInt("privilege"));
            return admin;
        }

    }

    @Override
    public Admin search(Admin admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();
        String sql = "select * from tb_admin where username=? and password=?";
        try {
            admin = jdbcTemplate.queryForObject(sql, new AdminRowMapper(), username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  admin;
    }

    @Override
    public boolean add(Admin admin) {
        String sql = "insert into tb_admin(username,password,name,birthday,privilege) " +
                "values(?,?,?,?,1)";
        int affectedNum = jdbcTemplate.update(sql,admin.getUsername(),admin.getPassword(),
                admin.getName(),admin.getBirthday());

        if (affectedNum != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Admin admin) {
        int aid = admin.getAid();
        //TODO 在数据库中设置级联删除
        String sql = "delete from tb_admin where aid=?"; //设置级联删除
        int affectedNum = 0;
        try {
            affectedNum = jdbcTemplate.update(sql,aid);
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
    public List<Admin> list() {
        List<Admin> adminList;
        String sql = "select * from tb_admin where privilege=1";
        try {
            adminList = jdbcTemplate.query(sql, new AdminRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  adminList;
    }

    @Override
    public boolean update(Admin admin) {
        String sql = "";
        int effectedNum = 0;
        try {
            if (admin.getPassword() != null) {
                sql = "update tb_admin set username=?,password=?,name=?,birthday=? where aid=?";
                effectedNum = jdbcTemplate.update(sql,admin.getUsername(),admin.getPassword(),
                    admin.getName(),admin.getBirthday(),admin.getAid());
             } else {
                 sql = "update tb_admin set username=?,name=?,birthday=? where aid=?";
                 effectedNum = jdbcTemplate.update(sql,admin.getUsername(),
                    admin.getName(),admin.getBirthday(),admin.getAid());
            }

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
}
