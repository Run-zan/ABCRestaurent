package app.ittraing.com.ittrainingdemoapp.practice;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import app.ittraing.com.ittrainingdemoapp.R;
import app.ittraing.com.ittrainingdemoapp.pojo.Car;

/**
 * Created by ranja_000 on 11/3/2017.
 */

public class CarActivity extends AppCompatActivity {

    Calendar calendar;
    EditText mName, mModel, mPrice, mDate;
    AutoCompleteTextView mColor;
    Button mInsert;
    Car car;
    float price;
    String name, model, color, date;

    /*String[] colors = {"Red", "Blue", "White", "Black"};*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate  the menu ;
        //this adds items to the action bar if present
        getMenuInflater().inflate(R.menu.activity_car, menu);
        return true;
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        calendar = Calendar.getInstance();

        mName = (EditText) findViewById(R.id.activity_car_name);
        mModel = (EditText) findViewById(R.id.activity_car_model);
        mPrice = (EditText) findViewById(R.id.activity_car_price);
        mColor = (AutoCompleteTextView) findViewById(R.id.activity_car_color);
        mDate = (EditText)findViewById(R.id.activity_car_et_date);
        mInsert = (Button) findViewById(R.id.activity_car_insert);

        /////////////////////////Date picker//////////////////////////////////
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog datePickerDialog = new DatePickerDialog(CarActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText(dayOfMonth+ "-"+(month +1)+"-" +year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        /////////////////////////! End of Date picker//////////////////////////////////

       /* //Creating the instance of ArrayAdapter containing the list of colors
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, colors);
        mColor.setThreshold(1);//will start from first character
        mColor.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        mColor.setTextColor(Color.RED);*/

        mInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = mName.getText().toString();
                model = mModel.getText().toString();
                try {
                    price = Float.valueOf(mPrice.getText().toString());
                }
                catch (NumberFormatException e){
                    Toast.makeText(CarActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }//without try catch block it causes crash in app

                color = mColor.getText().toString();
                date = mDate.getText().toString();

                car = new Car(price, name, model, color, date);
                

                Intent intent = new Intent(CarActivity.this, CarDetailActivity.class);
                intent.putExtra("key", car);
                startActivity(intent);
            }
        });
    }
}

