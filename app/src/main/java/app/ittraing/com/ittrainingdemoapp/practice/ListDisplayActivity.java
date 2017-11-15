package app.ittraing.com.ittrainingdemoapp.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import app.ittraing.com.ittrainingdemoapp.R;

/**
 * Created by ranja_000 on 11/7/2017.
 */

public class ListDisplayActivity extends AppCompatActivity{

    //Array of String
    String[] mobile_brand = {"SAMSUNG", "COLORS", "BLACK BERRY", "HTC", "NOKIA", "SONY", "LG", "APPLE"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        final ListView listView = (ListView) findViewById(R.id.activity_list_display_lv_mobile_list);

        //Adapter are used to provide the data to the listview
        final ArrayAdapter adapter = new ArrayAdapter<String> (this, android.R.layout.simple_expandable_list_item_1, mobile_brand);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Display the position of item inside list
                Toast.makeText(ListDisplayActivity.this, "Position:"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
