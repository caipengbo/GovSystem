package govsystem.service;

import govsystem.domain.User;

import java.util.List;

/**
 * Description: 后台业务
 * 具体业务：
 * 1.用户管理； 2.新闻管理 3.留言管理 4.问卷管理
 */
public interface BackService {
    /**
     * 列出所有用户
     * @return 用户List
     */
    List<User> listUsers();
}
