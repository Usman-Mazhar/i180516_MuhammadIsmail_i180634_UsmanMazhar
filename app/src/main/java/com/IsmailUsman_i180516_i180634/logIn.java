package com.IsmailUsman_i180516_i180634;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class logIn extends AppCompatActivity {

    Button logIn1, register1;
    TextView email1,password1  ;
    MyDBHelper DB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logIn1 = findViewById(R.id.logIn1);
        register1 = findViewById(R.id.register1);
        email1 = (EditText) findViewById(R.id.email1);
        password1=(EditText) findViewById(R.id.password1);
        DB = new MyDBHelper(this) ;

        logIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e = email1.getText().toString();
                String p = password1.getText().toString();

                if( e.equals("") || p.equals("") )
                {
                    Toast.makeText(logIn.this , "Please Insert All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean checkep = DB.checkemailpassword( e , p );

                    if ( checkep == true)
                    {
                        Toast.makeText(logIn.this , "Log in Successful", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(logIn.this, Callhistory.class);
                        startActivity(intent1);
                    }
                    else
                    {
                        Toast.makeText(logIn.this , "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logIn.this, signUp.class);
                startActivity(intent);
            }
        });
    }
}