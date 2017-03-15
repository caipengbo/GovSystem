package govsystem.service.impl;

import govsystem.dao.UserDao;
import govsystem.domain.User;
import govsystem.formbean.frontform.LoginForm;
import govsystem.formbean.frontform.RegistForm;
import govsystem.formbean.frontform.SettingForm;
import govsystem.service.FrontService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * Created by Myth on 3/12/2017.
 */
@Service
public class FrontServiceImpl implements FrontService {
    //将UserDaoImpl注入UserDao
    @Resource
    private UserDao userDao;
    @Override
    public User regist(RegistForm registForm) {
        User user = new User();
        //使用BeanUtils复制两个domain的相同属性
        try {
            BeanUtils.copyProperties(user,registForm);
        } catch (Exception e) {
            return null; //发生异常返回null
        }
        userDao.add(user);
        return user;
    }

    @Override
    public User login(LoginForm loginForm) {
        User user = new User();
        user.setUsername(loginForm.getUsername());
        user.setPassword(loginForm.getPassword());
        user = userDao.search(user);
        if (user != null) {
            return user;
        } else {
            return null;
        }

    }

    @Override
    public User setting(SettingForm settingForm) {
        return null;
    }
}
