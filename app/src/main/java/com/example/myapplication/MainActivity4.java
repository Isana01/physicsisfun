package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class MainActivity4 extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    Button back;
    EditText email;
    FirebaseAuth firebaseAuth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        firebaseAuth = FirebaseAuth.getInstance();
        constraintLayout = findViewById(R.id.container);
        back = findViewById(R.id.sendemail);
        email = findViewById(R.id.textInputLayout2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity4.this,"Password send to your Email",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity4.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
        Calendar c = Calendar.getInstance();

        int timeofday = c.get(Calendar.HOUR_OF_DAY);
        if (timeofday >= 0 && timeofday < 12) {
            //morning
            constraintLayout.setBackground(getDrawable(R.drawable.desk));

        } else if (timeofday >= 12 && timeofday < 16) {
            //afternoon
            constraintLayout.setBackground(getDrawable(R.drawable.desk));


        } else if (timeofday >= 16 && timeofday < 21) {
            //evening
            constraintLayout.setBackground(getDrawable(R.drawable.abla));

        }
        else if(timeofday >= 21 && timeofday < 24){
            //night
            constraintLayout.setBackground(getDrawable(R.drawable.abla));

        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    }
}