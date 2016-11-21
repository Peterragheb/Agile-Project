package com.example.aly.project1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onButtonClick (View v)
    {
        if (v.getId() == R.id.button)
        {
            EditText t = (EditText)findViewById(R.id.login_username);
            String str = t.getText().toString();
            EditText tt = (EditText)findViewById(R.id.login_password);
            String pass = tt.getText().toString();

            String password = helper.searchPass(str);
            if(pass.equals(password))
            {
                Users user=new Users();
                user.setUname(helper.GetColumn(str,"Username"));
                user.setProfilepic(helper.GetColumn(user.getUname(),"ProfilePic"));
                Intent i = new Intent(this,Logged.class);
                i.putExtra("Users", user);
                startActivity(i);
                finish();
            }
            else {

                Toast errorlog = Toast.makeText(Login.this,"Username and Password not matching!", Toast.LENGTH_SHORT);
                errorlog.show();

            }




        }
        if(v.getId() == R.id.button2)
        {

            Intent i = new Intent(this,SignUp.class);

            startActivity(i);
            finish();
        }

    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
           finish();
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

}

