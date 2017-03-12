package govsystem.domain;


/**
 * Description:用户类
 * Created by Myth on 3/12/2017.
 */

public class User {
    private long uid;
    private String username;
    private String password;
    private String name;
    private String birthday;
    private String identifyCode;
    private int indetityFlag;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
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

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public int getIndetityFlag() {
        return indetityFlag;
    }

    public void setIndetityFlag(int indetityFlag) {
        this.indetityFlag = indetityFlag;
    }
}
