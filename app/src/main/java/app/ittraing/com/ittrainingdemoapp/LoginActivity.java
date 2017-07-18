package app.ittraing.com.ittrainingdemoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import app.ittraing.com.ittrainingdemoapp.Helper.Constants;
import app.ittraing.com.ittrainingdemoapp.Helper.GlobalState;
import app.ittraing.com.ittrainingdemoapp.POJO.LoginCredentials;
import app.ittraing.com.ittrainingdemoapp.Parser.JsonParser;

/**
 * Created by ranja_000 on 6/15/2017.
 */

public class LoginActivity extends AppCompatActivity {

    int status =0;

    JsonParser jsonParser = new JsonParser();

    JSONObject jsonObject;

    ProgressDialog progressDialog;

    AutoCompleteTextView mEmail;

    EditText mPassword;

    Button mLoginBtn, mSignUpBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (AutoCompleteTextView) findViewById(R.id.activity_login_email);

        mPassword = (EditText)findViewById(R.id.activity_login_password);

        mSignUpBtn = (Button)findViewById(R.id.activity_login_register_button);

        mLoginBtn = (Button)findViewById(R.id.activity_login_button);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mEmail.getText().length()>2&&mPassword.getText().length()>2) {

                    new PerformLogin().execute();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Please provide proper info", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    class PerformLogin extends AsyncTask<String, String, String> {

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("email", email);
            hashMap.put("password", password);

            String url = Constants.BASE_URL+"api/login";

            jsonObject = jsonParser.performPostCI(url, hashMap);

            progressDialog.dismiss();

            if (jsonObject == null) {
                status = 1;

            } else {
                try {
                    if (jsonObject.getString("status").equals("success")) {
                        status = 2;

                        LoginCredentials credentials= new LoginCredentials(email, password);

                        GlobalState state = GlobalState.singleton;
                        state.setPrefsIsLoggedIn(Constants.STATE_TRUE, 1);
                        state.setPrefsloggedUserInfo(new Gson().toJson(credentials), 1);

                    } else {
                        status = 3;
                    }
                } catch (JSONException e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (status == 1) {
                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            } else if (status == 2) {
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            } else if(status == 3){
                Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
