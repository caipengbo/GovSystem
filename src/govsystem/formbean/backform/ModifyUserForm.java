package govsystem.formbean.backform;

/**
 * Description: 修改用户表单
 * Created by Myth on 3/13/2017.
 */
public class ModifyUserForm {
    private long uid;
    private String username;
    private String password;
    private String name;
    private String birthday;
    private String identityCode;
    private int identityFlag;

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

