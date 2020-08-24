package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    EditText name;
    LinearLayout constraintLayout;

    TextView tvtime;
    EditText email;

    EditText pass;
    EditText repass;

    Button register;
    FirebaseAuth mAuth;
    FirebaseDatabase rootnode;

    DatabaseReference reff;
    Data data;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        constraintLayout = findViewById(R.id.container);
        tvtime = findViewById(R.id.tvtime);
        email = findViewById(R.id.textInputLayout4);
        name = findViewById(R.id.textInputLayout5);
        pass = findViewById(R.id.textInputLayout2);
        repass = findViewById(R.id.textInputLayout);
        register = findViewById(R.id.btn_signup);
        data = new Data();
        //reff = FirebaseDatabase.getInstance().getReference().child("Data");

        // add a check to make sure that entered password and reentered password is matching.





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  rootnode = FirebaseDatabase.getInstance();
               // reff = rootnode.getReference("Data");
                data = new Data();
                reff = FirebaseDatabase.getInstance().getReference().child("Data");
                data.setName(name.getText().toString());
                data.setEmail(email.getText().toString());
                reff.push().setValue(data);


                if(!pass.equals(repass)){

                    Toast.makeText(MainActivity2.this, "Passwords do not match", Toast.LENGTH_LONG).show();

                }
                else if(name.equals("")){
                    Toast.makeText(MainActivity2.this, "Please enter your name", Toast.LENGTH_LONG).show();

                }
                else if(email.equals("")){
                    Toast.makeText(MainActivity2.this, "Please enter your email", Toast.LENGTH_LONG).show();

                }
                else if(pass.equals("")){
                    Toast.makeText(MainActivity2.this, "Please enter the password", Toast.LENGTH_LONG).show();

                }
                else if(repass.equals("")){
                    Toast.makeText(MainActivity2.this, "Please re enter the password", Toast.LENGTH_LONG).show();

                }
                else {



                    Task<AuthResult> registered_successfully = mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name.getText().toString()).build();
                                mAuth.getCurrentUser().updateProfile(profileUpdates);
                                Toast.makeText(MainActivity2.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                reff.push().setValue(data);
                                email.setText("");
                                pass.setText("");
                                name.setText("");
                                repass.setText("");

                            } else {
                                Toast.makeText(MainActivity2.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }});


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
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    }}

