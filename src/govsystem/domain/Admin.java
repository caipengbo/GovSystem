package govsystem.domain;

import java.util.Date;

/**
 * Description: 管理员类
 * Created by Myth on 3/12/2017.
 */
public class Admin {
    private long aid;
    private String username;
    private String password;
    private String name;
    private Date birthday;
    private int privilege; //权限 ，1（超级管理员） ，2（普通管理员）

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
