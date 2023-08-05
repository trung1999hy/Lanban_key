package com.example.keyboardtheme.ahha.keybroad;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.keyboardtheme.Preference;
import com.example.keyboardtheme.R;
import com.example.keyboardtheme.ahha.module1122.base.ActivityBase;
import com.example.keyboardtheme.ahha.module1122.constants.ApplicationPrefs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class KeyActivity extends ActivityBase implements OnClickListener {

    String[] appPermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERMISSION_REQUEST_CODE = 12;

    ApplicationPrefs applicationPrefs;
    private static final int PICK_FROM_GEALLERY = 1;
    LinearLayout l_lay_keyboard_enableKeyboard, l_lay_keyboard_SetInputMethod, l_lay_keyboard_setThemes, l_lay_keyboard_setThemesBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        Preference.buildInstance(this);
        Preference.buildInstance(this).isOpenFirst();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkAndRequestPermission()) {
                initApp();
            }
        } else {
            initApp();
        }

    }

    private boolean checkAndRequestPermission() {
        List<String> listPermissionNeeded = new ArrayList<>();
        for (String perm : appPermission) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                listPermissionNeeded.add(perm);
            }
        }

        if (!listPermissionNeeded.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions( appPermission, PERMISSION_REQUEST_CODE);
            }
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            HashMap<String, Integer> permissionResults = new HashMap<>();
            List<String> permissionNeeded = new ArrayList<>();
            int deniedCount = 0;

            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    permissionResults.put(permissions[i], grantResults[i]);
                    permissionNeeded.add(permissions[i]);
                    deniedCount++;
                }
            }

            if (deniedCount == 0) {
                initApp();
            } else {
                if (!permissionNeeded.isEmpty()) {
                    ActivityCompat.requestPermissions(this, permissionNeeded.toArray(new String[permissionNeeded.size()]), PERMISSION_REQUEST_CODE);
                }

//                for (Map.Entry<String, Integer> entry : permissionResults.entrySet()){
//                    String permName = entry.getKey();
//                    int perResult = entry.getValue();
//
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,permName)) {
//                        alertDialog("", "This app needs permissions to work without and problems.",
//                                "Yes, Grant Permissions",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                        checkAndRequestPermission();
//                                    }
//                                },
//                                "No, Exit App",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                        finish();
//                                    }
//                                }, false);
//                    }
////                    }else{
////                        alertDialog("","You have denied some permissions. Allow all permission at [Setting] > [Permissions]",
////                                "Go to Settings",
////                                new DialogInterface.OnClickListener() {
////                                    @Override
////                                    public void onClick(DialogInterface dialog, int which) {
////                                        dialog.dismiss();
////                                        Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
////                                                Uri.fromParts("package", getPackageName(), null));
////                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                                        startActivity(i);
////                                        finish();
////                                    }
////                                },
////                                "No, Exit App",
////                                new DialogInterface.OnClickListener() {
////                                    @Override
////                                    public void onClick(DialogInterface dialog, int which) {
////                                        dialog.dismiss();
////                                        finish();
////                                    }
////                                }, false);
////
////                    }
//
//                }
            }
        }

    }


    private void initApp() {
        applicationPrefs = ApplicationPrefs.getInstance(this);
        if (which == 0) {
            Intent intent2 = new Intent(getApplicationContext(), ThemeListActivity.class);
            startActivity(intent2);
        } else if (which == 1) {
            Intent intent = new Intent(getApplicationContext(), CustomeThemesActivity.class);
            startActivity(intent);
        }
        initUI();
    }

    private void showInterstitial() {
        if (which == 0) {
            Intent intent2 = new Intent(getApplicationContext(), ThemeListActivity.class);
            startActivity(intent2);
        } else if (which == 1) {
            Intent intent = new Intent(getApplicationContext(), CustomeThemesActivity.class);
            startActivity(intent);
        }
    }


    private void initUI() {
        // TODO Auto-generated method stub

        /*Find views*/

        l_lay_keyboard_enableKeyboard = (LinearLayout) findViewById(R.id.l_lay_keyboard_enableKeyboard);
        l_lay_keyboard_SetInputMethod = (LinearLayout) findViewById(R.id.l_lay_keyboard_SetInputMethod);
        l_lay_keyboard_setThemes = (LinearLayout) findViewById(R.id.l_lay_keyboard_setThemes);
        l_lay_keyboard_setThemesBackground = (LinearLayout) findViewById(R.id.l_lay_keyboard_setThemesBackground);


        /*Add Litener*/
        l_lay_keyboard_enableKeyboard.setOnClickListener(this);
        l_lay_keyboard_SetInputMethod.setOnClickListener(this);
        l_lay_keyboard_setThemes.setOnClickListener(this);
        l_lay_keyboard_setThemesBackground.setOnClickListener(this);


    }

    private int which = -1;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.l_lay_keyboard_enableKeyboard)
            startActivityForResult(new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS), 0);
        else if (v.getId() == R.id.l_lay_keyboard_SetInputMethod)
            ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .showInputMethodPicker();
        else if (v.getId() == R.id.l_lay_keyboard_setThemes) {
            which = 0;
            showInterstitial();
        } else if (v.getId() == R.id.l_lay_keyboard_setThemesBackground) {
            which = 1;
            showInterstitial();
        }

        //takePicture();


    }

    private void startCropImages(String file2) {

        Intent intent = new Intent(getApplicationContext(), Crope.class);
        intent.putExtra("image-path", file2);
        startActivityForResult(intent, 3);
        // TODO Auto-generated method stub
        //Intent intent = new Intent(getApplicationContext(),Crope.class);
        //  intent.putExtra("image-path", mFileTemp.getPath());
        // Log.i("----------------", mFileTemp.getAbsolutePath());
        //startActivity(intent);

    }

    private void takePicture() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(
                        Intent.createChooser(intent, "Select Picture:"),
                        PICK_FROM_GEALLERY);
            }
        } catch (ActivityNotFoundException localActivityNotFoundException) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case PICK_FROM_GEALLERY:

                Uri selectedImageUri = data.getData();
                String selectedImagePath = getPath(selectedImageUri);
                Bitmap photo = getPreview(selectedImagePath);
                // imageView.setImageBitmap(photo);
                startCropImages(selectedImagePath);
                session.setBitmap(photo);

                break;


        }

    }

    public Bitmap getPreview(String fileName) {
        File image = new File(fileName);

        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(image.getPath(), bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1)) {
            return null;
        }
        int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
                : bounds.outWidth;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = originalSize / 64;
        // opts.inSampleSize = originalSize;
        return BitmapFactory.decodeFile(image.getPath());
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
