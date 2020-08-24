package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

public class MainActivity extends AppCompatActivity {
    LinearLayout constraintLayout;
    TextView tvtime;
    TextView textView;
    Button btn_signup;
    Button signin;
    EditText email;
    EditText pass;

    FirebaseAuth firebaseAuth;


    @RequiresApi(api = LOLLIPOP)
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    @RequiresApi(api = LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        constraintLayout = findViewById(R.id.container);
        tvtime = findViewById(R.id.tvtime);
        textView = findViewById(R.id.textView);
        pass = findViewById(R.id.textInputLayout);
        email = findViewById(R.id.textInputLayout2);
        signin = findViewById(R.id.btn_signin);
        btn_signup = findViewById((R.id.btn_signup));

        firebaseAuth = FirebaseAuth.getInstance();

     // if(FirebaseAuth.getInstance().getCurrentUser() != null){
      //     Intent intent = new Intent(this,MainActivity3.class);
      //     startActivity(intent);
      //     finish();
      //  }



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity4.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below this point, sign in is happening
                // before signing in , check if user entered all fields

                if(email.getText().toString().equals("") || pass.getText().toString().equals("")){

                    Toast.makeText(MainActivity.this, "Please Enter your details first", Toast.LENGTH_LONG).show();

                }
                else{

                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Welcome ",Toast.LENGTH_LONG).show();
                                email.setText("");
                                pass.setText("");
                                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                                startActivity(intent);
                            }

                            else
                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    });

                }




            }
        });



        Calendar c = Calendar.getInstance();

        int timeofday = c.get(Calendar.HOUR_OF_DAY);
        if (timeofday >= 0 && timeofday < 12) {
            //morning
            constraintLayout.setBackground(getDrawable(R.drawable.desk));
            tvtime.setText("Good Morning");
        } else if (timeofday >= 12 && timeofday < 16) {
            //afternoon
            constraintLayout.setBackground(getDrawable(R.drawable.desk));
            tvtime.setText("Good Afternoon");

        } else if (timeofday >= 16 && timeofday < 21) {
            //evening
            constraintLayout.setBackground(getDrawable(R.drawable.desk));
            tvtime.setText("Good Evening");

        }
        else if(timeofday >= 21 && timeofday < 24){
            //night
                constraintLayout.setBackground(getDrawable(R.drawable.desk));

            tvtime.setText("Good Night");
        }
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); Phone tw nh utha kr phen dya?


    }}