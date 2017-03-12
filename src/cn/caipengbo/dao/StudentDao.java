package cn.caipengbo.dao;

import cn.caipengbo.domain.Student;

/**
 * Description:
 * Created by Myth on 3/4/2017.
 */
public interface  StudentDao {

    public void save(Student student);
    public void del(long id);
    public void search();
    public void update(long id);
}
