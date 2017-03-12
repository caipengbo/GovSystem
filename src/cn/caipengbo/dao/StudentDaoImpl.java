package cn.caipengbo.dao;

import cn.caipengbo.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("implements1")
public class StudentDaoImpl implements StudentDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Student student) {
        //System.out.println("Dao实现1");
        List<Object[]> batchArgs= new ArrayList<Object[]>();
        batchArgs.add(new Object[] {"batch1",1});
        batchArgs.add(new Object[] {"batch2",2});
        batchArgs.add(new Object[] {"batch4",4});
        batchArgs.add(new Object[] {"batch3",3});
        int[] affectedNum = jdbcTemplate.batchUpdate("insert into tb_student(name,age) values(?,?)",batchArgs);
        for(int i : affectedNum) {
            System.out.println(i);
        }
    }

    @Override
    public void del(long id) {
        int effectedNum = jdbcTemplate.update("delete from tb_student where id = ?",id);
        System.out.println( effectedNum );
    }

    @Override
    public void search() {

    }

    @Override
    public void update(long id) {
        String name = "testupdate";
        int effectedNum = jdbcTemplate.update("update tb_student set name = ? where id = ? ",name, id);
        System.out.println( effectedNum );
    }
}
