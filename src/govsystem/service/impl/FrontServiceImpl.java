package govsystem.service.impl;

import govsystem.dao.AdminDao;
import govsystem.dao.UserDao;
import govsystem.domain.Admin;
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
    @Resource
    private AdminDao adminDao;
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
    public User loginUser(LoginForm loginForm) {
        User user = new User();
        try {
            BeanUtils.copyProperties(user,loginForm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDao.search(user);
    }

    @Override
    public Admin loginAdmin(LoginForm loginForm) {
        Admin admin = new Admin();
        try {
            BeanUtils.copyProperties(admin,loginForm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return adminDao.search(admin);
    }

    @Override
    public User setting(SettingForm settingForm) {
        return null;
    }
}
