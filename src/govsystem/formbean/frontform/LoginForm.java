package govsystem.formbean.frontform;

/**
 * Description:登录表单
 * Created by Myth on 3/12/2017.
 */
public class LoginForm {
    private String username;
    private String password;

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


    @Override
    public String toString() {
        return "username:" + username + " password:" + password;
    }
}
