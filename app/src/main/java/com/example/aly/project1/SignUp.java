package com.example.aly.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by aly on 11/18/16.
 */
public class SignUp extends Activity {
    DatabaseHelper helper = new DatabaseHelper(this);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }
    public void onSignUpClick(View v)

    {

        if(v.getId() == R.id.sbtn);
        {

            EditText eusername = (EditText)findViewById(R.id.user_name);
            EditText epassword = (EditText)findViewById(R.id.pass_word);
            EditText econfirmpassword = (EditText)findViewById(R.id.confirm_password);
            EditText eemail = (EditText)findViewById(R.id.email);

            String username = eusername.getText().toString();
            String password = epassword.getText().toString();
            String confirmpassword = econfirmpassword.getText().toString();
            String email= eemail.getText().toString();
            if(!username.matches("^[a-z0-9_-]{3,15}$")){
                Toast WrongUsername = Toast.makeText(SignUp.this,"Username Must contain only letters,numbers and _,- ", Toast.LENGTH_SHORT);
                WrongUsername.show();
            }/*
             else if(!password.matches(" ")){
                Toast WrongUsername = Toast.makeText(SignUp.this,"^{4,16}$", Toast.LENGTH_SHORT);
                WrongUsername.show();
            */
           else if(password.length()<4){
                Toast PassLength = Toast.makeText(SignUp.this,"Password must be minimum of 4 characters", Toast.LENGTH_SHORT);
                PassLength.show();
            }
            else if(!password.equals(confirmpassword)){

                //popup
                Toast PassMissmatch = Toast.makeText(SignUp.this,"Password and confirm password are not matching", Toast.LENGTH_SHORT);
                PassMissmatch.show();

            }
            else if (helper.UserNameExist(username)){
                Toast UsernameTaken = Toast.makeText(SignUp.this,"Username already taken,try another", Toast.LENGTH_SHORT);
                UsernameTaken.show();
            }
            else if (helper.EmailExist(email)){
                Toast EmailTaken = Toast.makeText(SignUp.this,"This e-mail is used by an other account", Toast.LENGTH_SHORT);
                EmailTaken.show();
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast InvalidEmail = Toast.makeText(SignUp.this,"Please enter a valid email", Toast.LENGTH_SHORT);
                InvalidEmail.show();
            }
            else
            {
                //insert details in DB
                Users c = new Users();
                c.setUname(username);
                c.setEmail(email);
                c.setPass(password);
                helper.insertUser(c);
                c.setProfilepic(helper.GetColumn(username,"ProfilePic"));
                Intent i=new Intent(this,uploadimg.class) ;
                i.putExtra("Users", c);
                startActivity(i);
                finish();
            }

        }
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
        finish();

    }
}
