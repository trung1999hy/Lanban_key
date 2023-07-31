package com.example.keyboardtheme.gt.module.constants;


import android.content.Context;

import com.example.keyboardtheme.Preference;
import com.example.keyboardtheme.R;
import com.example.keyboardtheme.Theme;

import java.util.ArrayList;
import java.util.Set;

public class AppConstants {


    public static final String DeveloperID = "110102625";
    public static final String ApplicationID = "204506078";


    public static final int[] THEMES_4 = {R.drawable.themes4_key, R.drawable.themes4_background};

    public static final int[] THEMES_LIST = {R.drawable.themes1, R.drawable.themes2,
            R.drawable.themes3, R.drawable.themes4,
            R.drawable.themes5, R.drawable.themes6,
            R.drawable.themes7, R.drawable.themes8,
            R.drawable.themes9, R.drawable.themes10,
            R.drawable.themes11, R.drawable.themes12,
            R.drawable.themes13, R.drawable.themes14,
            R.drawable.themes15, R.drawable.themes16,
            R.drawable.themes17, R.drawable.themes18,
            R.drawable.themes19, R.drawable.themes20,
            R.drawable.themes21, R.drawable.themes22};

    public static final int[] KEY_BACKGROUND_LIST = {R.drawable.themes1_key, R.drawable.themes2_key,
            R.drawable.themes3_key, R.drawable.themes4_key,
            R.drawable.themes5_key, R.drawable.themes6_key,
            R.drawable.themes7_key, R.drawable.themes8_key,
            R.drawable.themes9_key, R.drawable.themes10_key,
            R.drawable.themes11_key, R.drawable.themes12_key,
            R.drawable.themes13_key, R.drawable.themes14_key,
            R.drawable.themes15_key, R.drawable.themes16_key,
            R.drawable.themes17_key, R.drawable.themes18_key,
            R.drawable.themes19_key, R.drawable.themes20_key,
            R.drawable.themes21_key, R.drawable.themes22_key};


    public static final int[] LAYOUT_LIST = {R.layout.input_themes1, R.layout.input_themes2,
            R.layout.input_themes3, R.layout.input_themes4,
            R.layout.input_themes5, R.layout.input_themes6,
            R.layout.input_themes7, R.layout.input_themes8,
            R.layout.input_themes9, R.layout.input_themes10,
            R.layout.input_themes11, R.layout.input_themes12,
            R.layout.input_themes13, R.layout.input_themes14,
            R.layout.input_themes15, R.layout.input_themes16,
            R.layout.input_themes17, R.layout.input_themes18,
            R.layout.input_themes19, R.layout.input_themes20,
            R.layout.input_themes21, R.layout.input_themes22,
    };

    public static ArrayList<Theme> getTheme(Context context) {
        ArrayList<Theme> listPositionBlock = new ArrayList<>();
        Set<String> listByeKey = Preference.buildInstance(context).getListKeyBy();
        for (int i = 0; i < THEMES_LIST.length; i++) {
            if (i > 6) {
                if (listByeKey.size() > 0) {
                    boolean checkLock = true;
                    for (String item : listByeKey) {
                        if ((THEMES_LIST[i]+"").equals( item)) {
                            checkLock = false;
                            break;
                        }
                    }
                    listPositionBlock.add(new Theme(THEMES_LIST[i], checkLock));
                } else {
                    listPositionBlock.add(new Theme(THEMES_LIST[i], true));
                }
            } else {
                listPositionBlock.add(new Theme(THEMES_LIST[i], false));
            }

        }
        return listPositionBlock;
    }
}
