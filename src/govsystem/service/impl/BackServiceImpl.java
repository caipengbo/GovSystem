package govsystem.service.impl;

import govsystem.dao.UserDao;
import govsystem.domain.User;
import govsystem.service.BackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Created by Myth on 3/12/2017.
 */
@Service
public class BackServiceImpl implements BackService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> listUsers() {
        return userDao.listAllUser();
    }
}
