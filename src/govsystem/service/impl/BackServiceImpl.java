package govsystem.service.impl;

import govsystem.dao.UserDao;
import govsystem.domain.User;
import govsystem.formbean.backform.ModifyUserForm;
import govsystem.service.BackService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:后台业务实现
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

    @Override
    public boolean deleteUserById(long uid) {
        return userDao.deleteUserById(uid);
    }

    @Override
    public boolean modifyUser(ModifyUserForm modifyUserForm) {
        User user = new User();
        try {
            BeanUtils.copyProperties(user,modifyUserForm);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (userDao.updateExceptPsw(user)) {
            return true;
        } else {
            return false;
        }
    }

}
