package cn.caipengbo.dao;

import cn.caipengbo.domain.Student;
import org.springframework.stereotype.Repository;

/**
 * Description:
 * Created by Myth on 3/4/2017.
 */
@Repository("implements2")
public class StudentDaoImpl2 implements StudentDao {
    @Override
    public void save(Student student) {
        System.out.println("Dao的实现2");
    }

    @Override
    public void del(long id) {

    }


    @Override
    public void search() {

    }

    @Override
    public void update(long id) {

    }

}
