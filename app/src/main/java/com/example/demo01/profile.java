package com.example.demo01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity implements View.OnClickListener {
 private FirebaseAuth mAuth;
private Button but1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    mAuth=FirebaseAuth.getInstance();
        but1=findViewById(R.id.signout1);
        but1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id. signout1:

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menulayout,menu);
        return super.onCreateOptionsMenu(menu);
    }
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
               if(item.getItemId()==R.id.signout1)
               {
                   FirebaseAuth.getInstance().signOut();
                   finish();
                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(intent);
               }
        return super.onOptionsItemSelected(item);
    }



}