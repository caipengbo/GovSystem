package govsystem.service.impl;

import govsystem.dao.*;
import govsystem.domain.*;
import govsystem.formbean.frontform.LoginForm;
import govsystem.formbean.frontform.RegistForm;
import govsystem.service.FrontService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private NewsDao newsDao;
    @Resource
    private VideoDao videoDao;
    @Resource
    private QuestionDao questionDao;
    @Resource
    private MessageDao messageDao;

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
    public boolean authenticate(String name, String identitycode) {
        return userDao.authenticate(name,identitycode);
    }

    @Override
    public boolean modifyUser(User user) {
        return userDao.update(user);
    }

    @Override
    public List<News> listAllNews() {
        News news = new News();
        news.setNid(-1);
        return newsDao.list(news);
    }

    @Override
    public List<News> listNews(int publicChoice) {
        return newsDao.list(publicChoice);
    }

    @Override
    public List<News> listUserNews(int uid) {
        return newsDao.listSelectedNews(uid);
    }

    @Override
    public boolean addApply(int uid, int nid) {
        User user = new User();
        News news = new News();
        user.setUid(uid);
        news.setNid(nid);
        return newsDao.addApply(news,user);
    }

    @Override
    public List<Message> listMessage(int nid) {
        News news = new News();
        news.setNid(nid);
        return messageDao.listChecked(news);
    }

    @Override
    public boolean addMessage(Message message) {
        return messageDao.add(message);
    }


    @Override
    public List<Video> listAllVideo() {
        return videoDao.list();
    }

    @Override
    public Video getVideo(String fileName) {
        return videoDao.get(fileName);
    }

    @Override
    public News getNews(int nid) {
        News news = new News();
        news.setNid(nid);
        List<News> newsList = newsDao.list(news); //根据nid获得news list
        if (newsList.isEmpty()) {
            news = null;
        } else {
            news = newsList.get(0); //获取第一个news
        }
        return news;
    }

    @Override
    public List<Question> listAllQuestion() {
        return questionDao.list();
    }

    @Override
    public List<QuestionItem> listAllQuestionItem(Question question) {
        return questionDao.listItem(question);
    }

    @Override
    public Question getQuestion(int qid) {
        return questionDao.get(qid);
    }

    @Override
    public boolean checkCompleteQuestion(int uid,int qid) {
        return questionDao.isComplete(uid,qid);
    }

    @Override
    public boolean completeQuestion(int uid, int qid, int a, int b, int c, int d) {
        if (questionDao.modifyQuestionNum(qid,a,b,c,d) && questionDao.addUserCompleteItem(uid,qid)) {
            return true;
        } else {
            return false;
        }
    }


}
