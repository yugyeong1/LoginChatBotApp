package com.dbrud1032.loginchatbotapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dbrud1032.loginchatbotapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

public class ChatActivity extends AppCompatActivity {



    private String CHAT_NAME;
    String ChatName;


    Button btnAdd;

    ListView chatView;
    EditText editChat;

    public String strEmail;

    ArrayAdapter<String> adapter;

    private FirebaseAuth FirebaseAuth;
    // 데이터베이스연결(실시간 데이터베이스)

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference DatabaseRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        database = FirebaseDatabase.getInstance();


        editChat = findViewById(R.id.editChat);
        btnAdd = findViewById(R.id.btnAdd);
        chatView = findViewById(R.id.chatView);


        Intent intent = getIntent();
        CHAT_NAME = intent.getStringExtra("chatName");
        strEmail = getIntent().getStringExtra("userEmail");
        ChatName = getIntent().getStringExtra("ChatName");




        openChat(CHAT_NAME);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("CHATBOT_APP", strEmail + "strEmail");

                if (editChat.getText().toString().equals(""))
                    return;

                User user = new User(strEmail, editChat.getText().toString());
                DatabaseRef.child("chat").child(CHAT_NAME).push().setValue(user); // 데이터 푸쉬
                editChat.setText(""); //입력창 초기화
            }
        });

    }

    private void addMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        User user = dataSnapshot.getValue(User.class);
        adapter.add( user.getEmailId()  + " : " + user.getMessage());
    }


    private void openChat(String chatName){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        chatView.setAdapter(adapter);

        DatabaseRef.child("chat").child(chatName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot , @Nullable String previousChildName) {
                addMessage(dataSnapshot, adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 액션바에 메뉴가 나오도록 설정한다.
        getMenuInflater().inflate(R.menu.chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.menuBack){

            finish();

        }

        return super.onOptionsItemSelected(item);

    }



}
