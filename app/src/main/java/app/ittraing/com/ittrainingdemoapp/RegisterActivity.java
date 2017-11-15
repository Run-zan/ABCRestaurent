package app.ittraing.com.ittrainingdemoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import app.ittraing.com.ittrainingdemoapp.helper.Constants;
import app.ittraing.com.ittrainingdemoapp.parser.JsonParser;

/**
 * Created by ranja_000 on 6/11/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    JSONObject jsonObject;

    JsonParser jsonParser = new JsonParser();

    ProgressDialog progressDialog;

    EditText mEmail, mPassword, mName, mAddress, mPhone;
    Button mRegisterBtn;

    int status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = (EditText) findViewById(R.id.activity_register_email);
        mName = (EditText) findViewById(R.id.activity_register_name);
        mAddress = (EditText) findViewById(R.id.activity_register_address);
        mPassword = (EditText) findViewById(R.id.activity_register_password);
        mPhone = (EditText) findViewById(R.id.activity_register_phone);

        mRegisterBtn = (Button) findViewById(R.id.activity_register_submit);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmail.getText().length() < 2 || mName.getText().length() < 2 || mAddress.getText().length() < 2 || mPassword.getText().length() < 2 || mName.getText().length() < 2) {
                    Toast.makeText(RegisterActivity.this, "Provide proper information", Toast.LENGTH_SHORT).show();

                }
                else {
                    new PerformRegister().execute();
                }
            }
        });
    }

    class PerformRegister extends AsyncTask<String, String, String>{

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String address = mAddress.getText().toString();
        String phone = mPhone.getText().toString();
        String name = mName.getText().toString();

        String url = Constants.BASE_URL+"api/register";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setTitle("loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("email", email);
            hashMap.put("password", password);
            hashMap.put("address", address);
            hashMap.put("phone", phone);
            hashMap.put("name", name);

            jsonObject = jsonParser.performPostCI(url, hashMap);

            if (jsonObject == null) {
                status = 1;
            } else {
                try {
                    if (jsonObject.getString("status").equals("success")) {
                        status = 2;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(status == 1){
                Toast.makeText(RegisterActivity.this, Constants.NETWORK_ISSUE, Toast.LENGTH_SHORT).show();
            }

           else if(status==2){

                Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        }
    }
 }

