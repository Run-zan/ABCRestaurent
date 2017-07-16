package app.ittraing.com.ittrainingdemoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import app.ittraing.com.ittrainingdemoapp.Adapter.MenuListAdapter;
import app.ittraing.com.ittrainingdemoapp.Helper.Constants;
import app.ittraing.com.ittrainingdemoapp.POJO.MenuList;
import app.ittraing.com.ittrainingdemoapp.Parser.JsonParser;

/**
 * Created by ranja_000 on 6/29/2017.
 */

public class MenuListActivity extends AppCompatActivity {

    MenuListAdapter menuListAdapter;
    JsonParser jsonParser = new JsonParser();
    JSONObject jsonObject;
    MenuList menuList;

    ArrayList<MenuList> arrayOfMenuList = new ArrayList<>();

    int status =1;

    ListView mMenuListView;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        mMenuListView = (ListView)findViewById(R.id.activity_menu_list_lv);

        new ShowMenuList().execute();
    }

    class ShowMenuList extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MenuListActivity.this);
            progressDialog.setMessage("Loading Menu");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", "1");
            String url = Constants.BASE_URL + "api/showmenulist";

            jsonObject = jsonParser.performPostCI(url, hashMap);

            if(jsonObject == null){
                status = 1;
            }
            else {

                try {
                    if(jsonObject.getString("status").equals("success")){

                        status = 2;
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for(int i=0; i<jsonArray.length(); i++){
                            JSONObject menuObject = jsonArray.getJSONObject(i);
                            String name, price, details, image, materials;
                            name = menuObject.getString("name");
                            price = menuObject.getString("price");
                            details = menuObject.getString("details");
                            image = menuObject.getString("image");
                            materials = menuObject.getString("materials");

                            menuList = new MenuList(name, price, details, image, materials);

                            arrayOfMenuList.add(menuList);
                        }
                    }

                    else {
                        status = 3;
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

            progressDialog.dismiss();

            if(status==2){

                menuListAdapter= new MenuListAdapter(MenuListActivity.this, arrayOfMenuList);

                mMenuListView.setAdapter(menuListAdapter);

                mMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        menuList = (MenuList)parent.getItemAtPosition(position);
                        Intent intent = new Intent(MenuListActivity.this,MenuDetailActivity.class);
                        intent.putExtra("key", menuList);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
