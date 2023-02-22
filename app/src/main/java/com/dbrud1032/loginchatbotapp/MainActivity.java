package com.dbrud1032.loginchatbotapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private EditText userChat;
    private TextView txtEmail;
    private Button userNext;
    private ListView chatList;
    ArrayAdapter<String> adapter;
    public String strEmail;
    String email;

    private FirebaseAuth FirebaseAuth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference DatabaseRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userChat = findViewById(R.id.userChat);
        txtEmail = findViewById(R.id.txtEmail);
        userNext = findViewById(R.id.userNext);
        chatList = findViewById(R.id.chatList);

        strEmail = getIntent().getStringExtra("email");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (strEmail == null) {

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            startActivity(intent);
            finish();
            return;
        }

        email = getIntent().getStringExtra("email");

        txtEmail.setText(email+"님 안녕하세요");




        userNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( userChat.getText().toString().equals(""))
                    return;

                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("chatName", userChat.getText().toString());
                intent.putExtra("userEmail", email);
                startActivity(intent);
            }
        });

        showChatList();

    }

    private void showChatList() {
        // 리스트 어댑터 생성 및 세팅
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        chatList.setAdapter(adapter);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        DatabaseRef.child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("CHATBOT_APP", "dataSnapshot.getKey() : " + dataSnapshot.getKey());
                adapter.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 액션바에 메뉴가 나오도록 설정한다.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.menuLogOut){

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("로그아웃");
            builder.setMessage("로그아웃 하시겠습니까?");
            builder.setNegativeButton("취소", null);
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // 변수초기화후 개체 반환 클래스
                    FirebaseAuth = FirebaseAuth.getInstance();
                    //로그아웃
                    FirebaseAuth.signOut();

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.show();


        }

        return super.onOptionsItemSelected(item);

    }

}