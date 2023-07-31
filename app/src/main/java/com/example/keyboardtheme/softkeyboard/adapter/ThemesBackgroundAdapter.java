package com.example.keyboardtheme.softkeyboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.keyboardtheme.R;
import com.example.keyboardtheme.Theme;
import com.example.keyboardtheme.gt.module.constants.AppConstants;

import java.util.ArrayList;


public class ThemesBackgroundAdapter extends BaseAdapter {


    Context context;
    LayoutInflater layoutInflater;

    public ArrayList<Theme> listTheme;

    public ThemesBackgroundAdapter(Context context) {
        // TODO Auto-generated constructor stub

        this.context = context;
        listTheme = AppConstants.getTheme(context);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listTheme.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listTheme.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    class ViewHolder {
        ImageView imageItem;
        TextView themes_no;
        LinearLayoutCompat linearLayoutCompat;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder.imageItem = convertView.findViewById(R.id.imageView1);
            holder.themes_no = convertView.findViewById(R.id.themes_no);
            holder.linearLayoutCompat = convertView.findViewById(R.id.image_block);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (listTheme.get(position).isBlock()){
            holder.linearLayoutCompat.setVisibility(View.VISIBLE);
        }else {
            holder.linearLayoutCompat.setVisibility(View.GONE);
        }

        holder.themes_no.setText("Theme No :- " + (position + 1));
        holder.imageItem.setImageResource(listTheme.get(position).getTheme());
        return convertView;
    }

}
