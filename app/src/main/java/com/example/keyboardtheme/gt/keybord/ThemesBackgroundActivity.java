package com.example.keyboardtheme.gt.keybord;

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

import androidx.appcompat.widget.Toolbar;

import com.example.keyboardtheme.Preference;
import com.example.keyboardtheme.R;
import com.example.keyboardtheme.gt.module.constants.AppConstants;
import com.example.keyboardtheme.gt.module.constants.ApplicationPrefs;
import com.example.keyboardtheme.inapp.PurchaseInAppActivity;
import com.example.keyboardtheme.softkeyboard.adapter.ThemesBackgroundAdapter;

import java.util.Set;

public class ThemesBackgroundActivity extends Activity implements OnItemClickListener {

    ApplicationPrefs applicationPrefs;
    private GridView listView_themes_list;
    private ThemesBackgroundAdapter listAdapter;
    private Preference preference;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_themes_list);
        applicationPrefs = ApplicationPrefs.getInstance(this);
        preference = Preference.buildInstance(this);
        textView = findViewById(R.id.coin);
		/* mInterstitialAd = new InterstitialAd(this);
	        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
	        AdRequest adRequest1 = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
	        mInterstitialAd.loadAd(adRequest1);
	        mInterstitialAd.setAdListener(new AdListener() {
				public void onAdLoaded() {
					// Call displayInterstitial() function
					showInterstitial();
				}
			});*/

        initUI();
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        textView.setText("" + Preference.buildInstance(ThemesBackgroundActivity.this).getValueCoin());
    }

    private void initUI() {
        // TODO Auto-generated method stub

        listAdapter = new ThemesBackgroundAdapter(getApplicationContext());
        listView_themes_list = (GridView) findViewById(R.id.listView_themes_list);
        listView_themes_list.setAdapter(listAdapter);
        listView_themes_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        if (AppConstants.getTheme(ThemesBackgroundActivity.this).get(position).isBlock()) {


            if (preference.getValueCoin() >= 2) {
                preference.setValueCoin(preference.getValueCoin() - 2);
                Set<String> keyList = preference.getListKeyBy();
                keyList.add(AppConstants.getTheme(ThemesBackgroundActivity.this).get(position).getTheme() + "");
                preference.setListKeyBy(keyList);
                initUI();
                applicationPrefs.setCustomeThemesKeyBackground(AppConstants.KEY_BACKGROUND_LIST[position]);
                Toast.makeText(this, "The background has been successfully set", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("MESSAGE", "done");
                setResult(2, intent);
                finish();//finishing activity
            } else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("You don't have enough gold ")
                        .setMessage("Open shop to buy more gold")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(ThemesBackgroundActivity.this, PurchaseInAppActivity.class);
                                ThemesBackgroundActivity.this.startActivity(intent);
                            }
                        });
                alertDialog.setNegativeButton("Cancel", null);
                alertDialog.show();
            }
        } else {
            applicationPrefs.setCustomeThemesKeyBackground(AppConstants.KEY_BACKGROUND_LIST[position]);
            Toast.makeText(this, "The background has been successfully set", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("MESSAGE", "done");
            setResult(2, intent);
            finish();//finishing activityl
        }

    }

}
