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
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private EditText editText,passwordtext;
private Button singup1,singup2;
    private ProgressBar progressBar1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign In");
        mAuth = FirebaseAuth.getInstance();
        editText=findViewById(R.id.editext);
        progressBar1=findViewById(R.id.progresBar1);
        passwordtext=findViewById(R.id.passwordtext);
        singup1=findViewById(R.id.singup1);
        singup2=findViewById(R.id.singup2);
        singup1.setOnClickListener(this);
        singup2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id. singup1:
                userLogin();
                break;
            case R.id. singup2:
                startActivity(new Intent(getApplicationContext(), signup.class));
                break;


        }

    }

    private void userLogin() {

        String Email = editText.getText().toString().trim();
        String Password =passwordtext.getText().toString().trim();
        progressBar1.setVisibility(View.VISIBLE);
        if(Email.isEmpty())
        {
            editText.setError("Enter an email address");
            editText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            editText.setError("Enter a valid email address");
            editText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(Password.isEmpty())
        {
            passwordtext.setError("Enter a password");
            passwordtext.requestFocus();
            return;
        }
        if(Password.length()<6)
        {
            passwordtext.setError("Minimum Length of password Should be 6");
            passwordtext.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar1.setVisibility(View.GONE);
               if (task.isSuccessful()) {
                   Intent intent= new Intent(getApplicationContext(),profile.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(intent);

                } else {
                   Toast toast = Toast.makeText(getApplicationContext(), "Error: "+task.getException().getMessage(), Toast.LENGTH_LONG);
                   toast.show();
                }
            }
        });

    }


}