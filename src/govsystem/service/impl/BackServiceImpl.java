package govsystem.service.impl;

import govsystem.dao.*;
import govsystem.domain.*;
import govsystem.formbean.backform.AddQuestionItemForm;
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
    @Resource
    private QuestionDao questionDao;


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
    public boolean addNews(News news) {
        return newsDao.add(news);
    }

    @Override
    public boolean deleteNewsById(int nid) {
        News news = new News();
        news.setNid(nid);
        return newsDao.delete(news);
    }

    @Override
    public boolean modifyNews(News news) {
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

    @Override
    public List<Question> listQuestion() {
        return questionDao.list();
    }

    @Override
    public List<QuestionItem> listQuetionItem(Question question) {
        return questionDao.listItem(question);
    }

    @Override
    public boolean deleteQuestion(Question question) {
        return questionDao.delete(question);
    }

    @Override
    public boolean deleteQuestionItem(QuestionItem questionItem) {
        return questionDao.deleteItem(questionItem);
    }

    @Override
    public boolean addQuestion(Question question) {
        return questionDao.add(question);
    }

    @Override
    public boolean addQuestionItem(AddQuestionItemForm addQuestionItemForm) {
        QuestionItem questionItem = new QuestionItem();
        questionItem.setQid(addQuestionItemForm.getQid());
        questionItem.setContent(addQuestionItemForm.getContent());
        return questionDao.addItem(questionItem);
    }

    @Override
    public boolean modifyQuestion(Question question) {
        return questionDao.modifyQuestion(question);
    }

    @Override
    public boolean addVideo(Video video) {
        return false;
    }
}
