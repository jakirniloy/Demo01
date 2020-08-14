package com.example.demo01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class signup extends AppCompatActivity implements View.OnClickListener {
    private EditText name,pass,Email;
    private Button button1,button2;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.setTitle("Sign Up");
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progresBar);
        name=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
        Email=findViewById(R.id.Useremail);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                UserRegistration();
                break;
            case R.id.button2:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;


        }


    }

    private void UserRegistration() {
        String Email1 = Email.getText().toString().trim();
        String Password =pass.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);
        if(Email1.isEmpty())
        {
            Email.setError("Enter an email address");
            Email.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Email1).matches())
        {
            Email.setError("Enter a valid email address");
            Email.requestFocus();
            return;
        }

        //checking the validity of the password
        if(Password.isEmpty())
        {
            pass.setError("Enter a password");
            pass.requestFocus();
            return;
        }
        if(Password.length()<6)
        {
            pass.setError("Minimum Length of password Should be 6");
            pass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(Email1, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Register is Successful !", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast toast = Toast.makeText(getApplicationContext(),"This User Already Registered!",Toast.LENGTH_LONG);
                                toast.show();
                            }
                            else {
                                Toast toast = Toast.makeText(getApplicationContext(), "Error: "+task.getException().getMessage(), Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    }
                });

    }
}