package be.irisnet.cirb.fixmystreet;

import java.io.File;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoActivity extends Activity {
    private Uri imageUri;
    public final int TAKE_PICTURE = 168;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
    }
    
    public void nextActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
        intent.addCategory("android.intent.category.cirb.fixmystreet");
        startActivity(intent);
    }

    public void takePhoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        case TAKE_PICTURE:
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = imageUri;
                getContentResolver().notifyChange(selectedImage, null);
                ImageView imageView = (ImageView) findViewById(R.id.preview);
                ContentResolver cr = getContentResolver();
                Bitmap bitmap;
                try {
                     bitmap = android.provider.MediaStore.Images.Media
                     .getBitmap(cr, selectedImage);

                    imageView.setImageBitmap(bitmap);
                    imageView.setVisibility(ImageView.VISIBLE);
                    Toast.makeText(this, selectedImage.toString(),
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                            .show();
                    Log.e("Camera", e.toString());
                }
            }
        }
    }
}