package app.ittraing.com.ittrainingdemoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.ittraing.com.ittrainingdemoapp.POJO.MenuList;

/**
 * Created by ranja_000 on 6/30/2017.
 */

public class MenuDetailActivity extends AppCompatActivity {

    MenuList menuList;
    CircularImageView mMenuImage;
    TextView mName, mPrice, mMaterialUsed, mAboutFood;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Toast.makeText(this, date+"", Toast.LENGTH_SHORT).show();

        mMenuImage = (CircularImageView) findViewById(R.id.activity_menu_detail_foodImage);
        mName = (TextView)findViewById(R.id.activity_menu_detail_foodName);
        mPrice = (TextView)findViewById(R.id.activity_menu_detail_foodPrice);
        mMaterialUsed = (TextView)findViewById(R.id.activity_menu_detail_materialUsed);
        mAboutFood = (TextView)findViewById(R.id.activity_menu_detail_aboutFood);

        menuList = (MenuList) getIntent().getSerializableExtra("key");

        mName.setText("Name: "+menuList.getName());
        mPrice.setText("Price: Rs. "+menuList.getPrice());
        mMaterialUsed.setText("Material Used: "+menuList.getMaterials());
        mAboutFood.setText("Detail: "+menuList.getDetails());

        Glide.with(MenuDetailActivity.this).load(menuList.getImage()).into(mMenuImage);
    }
}
