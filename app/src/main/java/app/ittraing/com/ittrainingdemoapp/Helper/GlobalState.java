package app.ittraing.com.ittrainingdemoapp.Helper;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;

public class GlobalState extends Application {

    SharedPreferences checkLogin;
    SharedPreferences.Editor checkLoginEditor;

    SharedPreferences userInfo;
    SharedPreferences.Editor userInfoEditor;

    public static GlobalState singleton;

    public static final String PREFS_IS_LOGGED_IN = "is logged in"; // to check if user is logged in
    public static final String PREFS_VALID_USER_INFO = "valid user info"; // to store the user information like name and phone number
    public static String PREFS_USER_INFO = "user info";

    public static final String PREFS_CHECK_LOGIN = "check login";

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onCreate() {
        super.onCreate();

        checkLogin = this.getSharedPreferences(PREFS_CHECK_LOGIN, 0);
        checkLoginEditor = checkLogin.edit();

        userInfo = this.getSharedPreferences(PREFS_USER_INFO, 0);
        userInfoEditor = userInfo.edit();

        singleton = this;
    }

    /**
     * @return MySIngleton instance
     */
    public GlobalState getInstance() {
        return singleton;
    }

    public String getPrefsCheckLogin(){
        return checkLogin.getString(PREFS_CHECK_LOGIN,"");
    }

    public void setPrefsCheckLogin(String value, int resultCode){
        if(resultCode == 1){
            checkLoginEditor.putString(PREFS_CHECK_LOGIN,value).commit();
        }else {
            checkLoginEditor.remove(PREFS_CHECK_LOGIN).commit();
        }
    }

    public String getPrefsUserInfo(){
        return userInfo.getString(PREFS_USER_INFO,"");
    }

    public void setPrefsUserInfo(String value, int resultcode){
        if (resultcode == 1){
            userInfoEditor.putString(PREFS_USER_INFO,value).commit();
        }else {
            userInfoEditor.remove(PREFS_USER_INFO).commit();
        }
    }
}
