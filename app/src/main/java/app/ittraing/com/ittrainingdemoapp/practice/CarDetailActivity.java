package app.ittraing.com.ittrainingdemoapp.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import app.ittraing.com.ittrainingdemoapp.R;
import app.ittraing.com.ittrainingdemoapp.pojo.Car;

/**
 * Created by ranja_000 on 11/3/2017.
 */

public class CarDetailActivity extends AppCompatActivity{
    TextView mName, mDate, mPrice;
    Car car;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        mName = (TextView)findViewById(R.id.activity_car_detail_name);
        mDate = (TextView)findViewById(R.id.activity_car_detail_date);
        mPrice = (TextView)findViewById(R.id.activity_car_detail_price);

        car = (Car) getIntent().getSerializableExtra("key");
        mName.setText("Name: "+car.getName().toString());
        mDate.setText(("Date: "+car.getDate().toString()));
        mPrice.setText("Price: "+car.getPrice());

    }
}
