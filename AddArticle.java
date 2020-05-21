package com.example.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddArticle extends AppCompatActivity {
    EditText articleText, titleText;
    FirebaseUser user;
    DatabaseReference myRef;
    Teacher teacher;
    String uid;
    DataSnapshot key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        articleText = (EditText) findViewById(R.id.articleText);
        titleText = (EditText) findViewById(R.id.titleText);
        user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Teachers");
        uid = user.getUid();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    if(keyNode.getKey().equals(uid)){
                        teacher = keyNode.getValue(Teacher.class);
                        key = keyNode;

                        break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addArticle(View view){
        Article article = new Article(titleText.getText().toString(), articleText.getText().toString());
        teacher.addArticle(article);

        myRef.child(uid).setValue(teacher);

        finish();
    }

}
