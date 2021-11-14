package com.IsmailUsman_i180516_i180634;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintStream;

public class signUp extends AppCompatActivity {

    Button signUp, logIn;
    TextView email,password,confirm_password ;
    MyDBHelper DB ;
    TextView myText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        logIn = findViewById(R.id.logIn);
        signUp = findViewById(R.id.signUp);
        email = (EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        confirm_password=(EditText) findViewById(R.id.confirm_password);
        DB = new MyDBHelper(this) ;


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUp.this, logIn.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e = email.getText().toString();
                String p1 = password.getText().toString();
                String p2 = confirm_password.getText().toString();

                if( e.equals("") || p1.equals("") || p2.equals("") )
                {
                    Toast.makeText(signUp.this , "Please Insert All Fields", Toast.LENGTH_SHORT).show();
                }

                else if(!p1.equals(p2))
                {
                    Toast.makeText(signUp.this , "Passwords Don't Match ", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    boolean check = DB.checkemail(e) ;

                    if ( check == true)
                    {
                        boolean insert = DB.insertData(e,p1,p2);
                        boolean ins = DB.insertProfileData("hello" , "mani" , "khan" , "male" , "hellow to the future" , "bye") ;

                        if(insert==true)
                        {
                            Toast.makeText(signUp.this , "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signUp.this, createProfile.class);
                            String str1 = DB.fetchingquerryagain() ;

                            Log.d("CHECK_KAR_RAHA" , str1) ;
                            startActivity(intent);
                        }

                        else
                        {
                            Toast.makeText(signUp.this , "Sign Up Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else
                    {
                        Toast.makeText(signUp.this , "User Already Exists ! Please Try Another Email", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}