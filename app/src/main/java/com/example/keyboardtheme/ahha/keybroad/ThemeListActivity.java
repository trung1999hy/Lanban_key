package com.example.keyboardtheme.ahha.keybroad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keyboardtheme.Preference;
import com.example.keyboardtheme.R;
import com.example.keyboardtheme.ahha.module1122.constants.AppConstants;
import com.example.keyboardtheme.ahha.module1122.constants.ApplicationPrefs;
import com.example.keyboardtheme.inapp.PurchaseInAppActivity;
import com.example.keyboardtheme.softkeyboard.adapter.ListAdapter;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Set;

public class ThemeListActivity extends Activity implements OnItemClickListener {

    ApplicationPrefs applicationPrefs;
    private GridView listView_themes_list;
    private ListAdapter listAdapter;
    private TextView textView;
    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes_list);
        applicationPrefs = ApplicationPrefs.getInstance(this);
        preference = Preference.buildInstance(this);
        textView = findViewById(R.id.coin);

        ((MaterialToolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initUI();
    }


    private void initUI() {
        // TODO Auto-generated method stub
        listAdapter = new ListAdapter(getApplicationContext());
        listView_themes_list = (GridView) findViewById(R.id.listView_themes_list);
        listView_themes_list.setAdapter(listAdapter);
        listView_themes_list.setOnItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        textView.setText(Preference.buildInstance(this).getValueCoin() + "");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        if (AppConstants.getTheme(ThemeListActivity.this).get(position).isBlock()) {
            if (preference.getValueCoin() >= 2) {
                preference.setValueCoin(preference.getValueCoin() - 2);
                Set<String> keyList = preference.getListKeyBy();
                keyList.add(AppConstants.getTheme(ThemeListActivity.this).get(position+1).getTheme()+"");
                preference.setListKeyBy(keyList);
                listAdapter.listPositionBlock = AppConstants.getTheme(ThemeListActivity.this);
                listAdapter.notifyDataSetChanged();
                applicationPrefs.setThemesId(position + 1);
                textView.setText(Preference.buildInstance(this).getValueCoin() + "");
                onBackPressed();
                Toast.makeText(this, "The background has been successfully set", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("You don't have enough gold ")
                        .setMessage("Open shop to buy more gold")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(ThemeListActivity.this, PurchaseInAppActivity.class);
                                ThemeListActivity.this.startActivity(intent);
                            }
                        });
                alertDialog.setNegativeButton("Cancel", null);
                alertDialog.show();
            }
        } else {
            applicationPrefs.setThemesId(position + 1);
            Toast.makeText(this, "The background has been successfully set", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
