package app.ittraing.com.ittrainingdemoapp.pojo;

import java.io.Serializable;

/**
 * Created by ranja_000 on 7/16/2017.
 */

public class LoginCredentials implements Serializable {

    String userName, password;

    public LoginCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
