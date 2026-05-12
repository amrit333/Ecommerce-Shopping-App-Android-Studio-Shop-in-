package com.example.shopinindia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.shopinindia.Entity.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupScreen extends AppCompatActivity {
TextView loginTxt;
    TextInputEditText name, email,password;
    AppCompatButton signupbtn;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_screen);
     loginTxt = findViewById(R.id.txtLogin);
     loginTxt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(SignupScreen.this, Login.class));
         }
     });


        name = findViewById(R.id.edtName);
        email = findViewById(R.id.signupET);
        password = findViewById(R.id.signPass);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
        signupbtn = findViewById(R.id.btnSignup);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  nameStr,emailStr,passwordStr;
                nameStr = name.getText().toString();
                emailStr = email.getText().toString();
                passwordStr = password.getText().toString();
                // Authenticating my user
                    auth.createUserWithEmailAndPassword(emailStr,passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

//                            if account is created succesfully it is my responsiblity to send users's data on databse
                            if(task.isComplete()){
                                // sending data of user to my databse

                                UserModel model  = new UserModel(nameStr,emailStr,passwordStr);

                                reference.child(auth.getCurrentUser().getUid()).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(SignupScreen.this);
                                        dialog.setTitle("User Create Succefully");
                                        dialog.setMessage("you have created your account successfuly");
                                        dialog.setPositiveButton("Go ahead", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                startActivity(new Intent(SignupScreen.this,HomeScreen.class));
                                            }
                                        }).show();

                                    }
                                });
                            }
                        }
                    });
            }
        });
    }


}