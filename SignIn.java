package com.example.main;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button button3;
    EditText emailView, passwordView;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        addListenerOnButton();
        mAuth = FirebaseAuth.getInstance();
        emailView = findViewById(R.id.textView3);
        passwordView = findViewById(R.id.textView4);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Teachers");
    }


    private void updateUI(FirebaseUser currentUser) {

    }
    public void createUser(final String email, String password ) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            final FirebaseUser user = mAuth.getCurrentUser();

                            Teacher teacher = new Teacher(email);

                            myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(teacher).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        notifyUser("Registration success");
                                        updateUI(user);
                                    }
                                    else{
                                        notifyUser(task.getException().toString());
                                    }
                                }
                            });
                            updateUI(user);
                            Intent intent = new Intent(".LogIn");
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void notifyUser(String string){
        Toast.makeText(SignIn.this, string,
                Toast.LENGTH_SHORT).show();
    }

    private void addListenerOnButton() {
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String email = emailView.getText().toString();
                        String password = passwordView.getText().toString();
                        createUser(email,password);

                    }
                }


        );

    }

}
