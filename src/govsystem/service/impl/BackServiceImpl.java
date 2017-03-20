package govsystem.service.impl;

import govsystem.dao.AdminDao;
import govsystem.dao.MessageDao;
import govsystem.dao.NewsDao;
import govsystem.dao.UserDao;
import govsystem.domain.Admin;
import govsystem.domain.Message;
import govsystem.domain.News;
import govsystem.domain.User;
import govsystem.formbean.backform.AddNewsForm;
import govsystem.formbean.backform.ModifyNewsForm;
import govsystem.formbean.backform.ModifyUserForm;
import govsystem.service.BackService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:后台业务实现(包括用户、新闻、留言等等)
 * Created by Myth on 3/12/2017.
 */
@Service
public class BackServiceImpl implements BackService {

    @Resource
    private UserDao userDao;
    @Resource
    private NewsDao newsDao;
    @Resource
    private MessageDao messageDao;
    @Resource
    private AdminDao adminDao;


    @Override
    public List<User> listUsers(String username) {
        User user = new User();
        user.setUsername(username);
        return userDao.list(user);
    }

    @Override
    public boolean deleteUserById(int uid) {
        User user = new User();
        user.setUid(uid);
        return userDao.delete(user);
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
        return userDao.update(user);
    }

    @Override
    public List<News> listNews(Integer nid) {
        News news = new News();
        if (nid == null) {
            news.setNid(-1);
        } else {
            news.setNid(nid.intValue());
        }
        return newsDao.list(news);
    }

    @Override
    public boolean addNews(AddNewsForm addNewsForm) {
        News news = new News();
        try {
            BeanUtils.copyProperties(news,addNewsForm);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        news.setApplyNum(0);
        news.setLookedNum(0);
        news.setMessageNum(0);
        //TODO 发布人外键
        news.setAid(1);
        return newsDao.add(news);
    }

    @Override
    public boolean deleteNewsById(int nid) {
        News news = new News();
        news.setNid(nid);
        return newsDao.delete(news);
    }

    @Override
    public boolean modifyNews(ModifyNewsForm modifyNewsForm) {
        News news = new News();
        try {
            BeanUtils.copyProperties(news,modifyNewsForm);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return newsDao.update(news);
    }

    @Override
    public List<Message> listMessage(Integer nid) {
        News news = new News();
        if (nid == null) {
            return null;
        } else {
            news.setNid(nid.intValue());
        }
        return messageDao.list(news);
    }

    @Override
    public List<User> listLookedUser(Integer nid) {
        News news = new News();
        if (nid == null) {
            return null;
        } else {
            news.setNid(nid.intValue());
        }
        return newsDao.listLookedUsers(news);
    }

    @Override
    public List<User> listApplyUser(Integer nid) {
        News news = new News();
        if (nid == null) {
            return null;
        } else {
            news.setNid(nid.intValue());
        }
        return newsDao.listApplyUsers(news);
    }

    @Override
    public boolean allowApply(Integer nid,Integer uid) {
        if (nid == null || uid == null) {
            return false;
        }
        News news = new News();
        User user = new User();
        news.setNid(nid);
        user.setUid(uid);
        return newsDao.allowApply(news,user);
    }
    @Override
    public boolean refuseApply(Integer nid,Integer uid) {
        if (nid == null || uid == null) {
            return false;
        }
        News news = new News();
        User user = new User();
        news.setNid(nid);
        user.setUid(uid);
        return newsDao.refuseApply(news,user);
    }
    @Override
    public boolean refuseView(Integer nid,Integer uid) {
        if (nid == null || uid == null) {
            return false;
        }
        News news = new News();
        User user = new User();
        news.setNid(nid);
        user.setUid(uid);
        return newsDao.refuseView(news, user);
    }

    @Override
    public boolean deleteMessage(Message message) {
        return message != null && messageDao.delete(message);
    }

    @Override
    public List<Admin> listAdmin() {
        return adminDao.list();
    }

    @Override
    public boolean addAdmin(Admin admin) {
        return adminDao.add(admin);
    }

    @Override
    public boolean modifyAdmin(Admin admin) {
        return adminDao.update(admin);
    }
    @Override
    public boolean deleteAdminById(int aid) {
        Admin admin = new Admin();
        admin.setAid(aid);
        return adminDao.delete(admin);
    }
}
