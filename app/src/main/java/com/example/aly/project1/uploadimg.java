package com.example.aly.project1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

/**
 * Created by aly on 11/19/16.
 */

public class uploadimg extends Activity implements View.OnClickListener {
    Users user;
    DatabaseHelper helper = new DatabaseHelper(this);
    private static final int RESULT_LOAD_IMAGE = 0;
    ImageView imageToUpload;
    Button bFinish;

    protected void onCreate(Bundle savedInstanceState) {
        user=(Users)getIntent().getParcelableExtra("Users");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgupload);
        imageToUpload = (ImageView) findViewById(R.id.imageToUpload);
        bFinish= (Button) findViewById(R.id.bFinish);
        imageToUpload.setImageURI(Uri.parse(user.getProfilepic()));
        imageToUpload.setOnClickListener(this);
        bFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageToUpload:
                Intent galleryIntent = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.bFinish:
                Intent i = new Intent(this,Logged.class);
                i.putExtra("Users", user);
                startActivity(i);
                finish();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data !=null)
        {
            Uri selectedImage = data.getData();
            helper.Update_Picture(user.getUname(),user.getProfilepic(),selectedImage.toString());
            user.setProfilepic(selectedImage.toString());

            imageToUpload.setImageURI(Uri.parse(helper.GetColumn(user.getUname(),"ProfilePic")));



        }
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(this, Login.class));
        finish();

    }

}
