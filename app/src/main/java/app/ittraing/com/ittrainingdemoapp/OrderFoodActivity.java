package app.ittraing.com.ittrainingdemoapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

import app.ittraing.com.ittrainingdemoapp.Helper.Constants;
import app.ittraing.com.ittrainingdemoapp.Parser.JsonParser;

/**
 * Created by ranja_000 on 7/9/2017.
 */

public class OrderFoodActivity extends AppCompatActivity {

    JsonParser jsonParser = new JsonParser();

    JSONObject jsonObject;

    Spinner mSpinner;

    EditText mQuantity, mTime, mDate;

    Button mOrderBtn;

    ProgressDialog progressDialog;

    int status = 0;

    String selectedFood = null;

    Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);

        calendar = Calendar.getInstance();

        mQuantity = (EditText) findViewById(R.id.activity_order_food_quantity);
        mDate = (EditText)findViewById(R.id.activity_order_food_date);
        mTime = (EditText)findViewById(R.id.activity_order_food_time);
        mOrderBtn = (Button)findViewById(R.id.activity_order_food_btn_order);

        mSpinner = (Spinner)findViewById(R.id.activity_order_food_spinner);

        // Creating adapter for spinner
        ArrayAdapter<CharSequence> foodItemAdapter = ArrayAdapter.createFromResource(this, R.array.menu_array, android.R.layout.simple_list_item_1);

        // Drop down layout style
        foodItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(foodItemAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedFood = mSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int year = calendar.get(Calendar.YEAR);
               int month = calendar.get(Calendar.MONTH);
               int day = calendar.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog datePickerDialog = new DatePickerDialog(OrderFoodActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText(dayOfMonth+ "-"+(month +1)+"-" +year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);

                final TimePickerDialog timePickerDialog = new TimePickerDialog(OrderFoodActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        mTime.setText(hour+" : "+min);
                    }

                },hour, min, false);
                timePickerDialog.show();

            }
        });

        mOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDate.getText().length()>2 && mTime.getText().length()>2 && mQuantity.getText().length()>0) {
                    new OrderFood().execute();
                }
                else {
                    Toast.makeText(OrderFoodActivity.this, "Provide proper detail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //class to request Order to server
    class OrderFood extends AsyncTask<String, String, String>{

        String date = mDate.getText().toString();
        String time = mTime.getText().toString();
        String quantity = mQuantity.getText().toString();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(OrderFoodActivity.this);
            progressDialog.setMessage("loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("name", selectedFood);
            hashMap.put("quantity", quantity);
            hashMap.put("time", time);
            hashMap.put("date", date);
            hashMap.put("detail", "adf");
            hashMap.put("userid", "1");
            hashMap.put("username", "ismaran");

            String orderUrl = Constants.BASE_URL+"api/orderfood";

            jsonObject = jsonParser.performPostCI(orderUrl, hashMap);

            if(jsonObject==null){
                status = 1;
            }

            try {
                if(jsonObject.getString("status").equals("success")){

                    status = 2;
                }

                else {

                    status = 3;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (status==2){
                Toast.makeText(OrderFoodActivity.this, "Order Success", Toast.LENGTH_SHORT).show();
            }

            else if(status== 1) {
                Toast.makeText(OrderFoodActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            else if(status ==3){
                Toast.makeText(OrderFoodActivity.this, "Wrong data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
