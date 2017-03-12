package cn.caipengbo.service;

import cn.caipengbo.dao.StudentDao;
import cn.caipengbo.domain.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * Created by Myth on 3/4/2017.
 */
@Service
public class StudentService {
    @Resource(name = "implements1")
    private StudentDao studentDao;

    public void saveStudent(Student student) {
        studentDao.save(student);
    }

    public void updateStudentById(long id) {
        studentDao.update(id);
    }
}
