package app.ittraing.com.ittrainingdemoapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.ittraing.com.ittrainingdemoapp.pojo.MenuList;
import app.ittraing.com.ittrainingdemoapp.R;

/**
 * Created by ranja_000 on 6/29/2017.
 */

public class MenuListAdapter extends ArrayAdapter<MenuList>{

    ArrayList<MenuList> menuLists;
    Context context;
    TextView mName, mPrice;
    ImageView mImage;

    public MenuListAdapter(  Context context1, ArrayList<MenuList> menuList) {
        super(context1, 0, menuList);
        this.menuLists = menuList;
        this.context = context1;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(@Nullable MenuList item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MenuList menuList = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_single_menu_item, parent, false);
        }

         mName = (TextView)convertView.findViewById(R.id.adapter_single_menu_item_tv_name);
         mPrice = (TextView)convertView.findViewById(R.id.adapter_single_menu_item_tv_price);
         mImage = (ImageView)convertView.findViewById(R.id.adapter_single_menu_item_iv_image);

         mName.setText(menuList.getName());
         mPrice.setText("Rs. "+ menuList.getPrice());

         Glide.with(getContext()).load(menuList.getImage()).placeholder(R.drawable.abc_image).into(mImage);

        return convertView;
    }
}
