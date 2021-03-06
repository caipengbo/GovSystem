package govsystem.domain;


/**
 * Description:用户类
 * Created by Myth on 3/12/2017.
 */

public class User {
    private int uid;
    private String username;
    private String password;
    private String name;
    private String birthday;
    private String identityCode;
    private int identityFlag;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public int getIdentityFlag() {
        return identityFlag;
    }

    public void setIdentityFlag(int identityFlag) {
        this.identityFlag = identityFlag;
    }
}
