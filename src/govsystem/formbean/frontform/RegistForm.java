package govsystem.formbean.frontform;

/**
 * Description: 注册表单
 * Created by Myth on 3/12/2017.
 */
public class RegistForm {
    private String username;
    private String password;
    private String name;
    //private String birthday;

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


    @Override
    public String toString() {
        return "username:" + username + " name:" + name;
    }
}
