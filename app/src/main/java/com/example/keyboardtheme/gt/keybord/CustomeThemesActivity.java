package com.example.keyboardtheme.gt.keybord;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.keyboardtheme.R;
import com.example.keyboardtheme.gt.module.base.ActivityBase;


import java.io.File;

public class CustomeThemesActivity extends ActivityBase implements OnClickListener {

    LinearLayout l_lay_customethemes_background, l_lay_customethemes_keybackground, l_lay_customethemes_gallerybackground, l_lay_customethemes_fontcolor, l_lay_customethemes_set;
    private static final int PICK_FROM_GEALLERY = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custome_themes);

        initUI();
    }


    private void initUI() {
        // TODO Auto-generated method stub
        l_lay_customethemes_background = (LinearLayout) findViewById(R.id.l_lay_customethemes_background);
        //l_lay_customethemes_keybackground = (LinearLayout) findViewById(R.id.l_lay_customethemes_keybackground);
        l_lay_customethemes_gallerybackground = (LinearLayout) findViewById(R.id.l_lay_customethemes_gallerybackground);
        //l_lay_customethemes_fontcolor = (LinearLayout) findViewById(R.id.l_lay_customethemes_fontcolor);
        l_lay_customethemes_set = (LinearLayout) findViewById(R.id.l_lay_customethemes_set);

        l_lay_customethemes_background.setOnClickListener(this);
        //l_lay_customethemes_keybackground.setOnClickListener(this);
        l_lay_customethemes_gallerybackground.setOnClickListener(this);
        //l_lay_customethemes_fontcolor.setOnClickListener(this);
        l_lay_customethemes_set.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.l_lay_customethemes_background)
            startActivityForResult(new Intent(getApplicationContext(), ThemesBackgroundActivity.class), 1);
	/*	case R.id.l_lay_customethemes_keybackground:
			startActivityForResult(new Intent(getApplicationContext(), ThemesKeyBackgroundActivity.class), 2);
			break;*/

        if (v.getId() == R.id.l_lay_customethemes_gallerybackground)
            takePicture();


        if (v.getId() == R.id.l_lay_customethemes_set) {
            applicationPrefs.setThemesId(1000);// load the next ad
            Toast.makeText(getApplicationContext(), "set keyboard theme..", Toast.LENGTH_LONG).show();
        }
			
		/*case R.id.l_lay_customethemes_fontcolor:
			onClickColorPickerDialog();
			pickColor();
			break;*/
    }

    private void pickColor() {
        // TODO Auto-generated method stub


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
                // load the next ad
            }
        } catch (ActivityNotFoundException localActivityNotFoundException) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        // load the next ad
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

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }
}
