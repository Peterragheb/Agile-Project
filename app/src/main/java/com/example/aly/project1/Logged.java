package com.example.aly.project1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by aly on 11/18/16.
 */

public class Logged extends Activity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged);

        DatabaseHelper helper=new DatabaseHelper(this);
        Users user=(Users)getIntent().getParcelableExtra("Users");
        TextView tvUsername = (TextView)findViewById(R.id.tvUsername);
        ImageView ivProfilePicture=(ImageView) findViewById(R.id.ivProfilePicture);
        tvUsername.setText(user.getUname());
        Button bLogout=(Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {



                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);


            }
        }
        
      
        ivProfilePicture.setImageURI(Uri.parse(user.getProfilepic()));

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            finish();
            //super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.bLogout){
            super.onBackPressed();
            startActivity(new Intent(this, Login.class));
            finish();

        }
    }
}
