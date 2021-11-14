package com.IsmailUsman_i180516_i180634;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.PrintStream;

public class signUp extends AppCompatActivity {

    Button signUp, logIn;
    TextView email,password,confirm_password ;
    MyDBHelper DB ;
    FirebaseAuth mAuth;
    TextView myText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();

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
                    // lets create a user now
                    mAuth.createUserWithEmailAndPassword(e,p1)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(signUp.this , "User Created Successfully ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(signUp.this , "Failed To Create User ", Toast.LENGTH_SHORT).show();
                        }
                    });


                }


            }
        });
    }
}