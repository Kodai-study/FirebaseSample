package com.example.firebasesample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasesample.Module.BookMarkData;
import com.example.firebasesample.Module.BookSampleDataAndMemosModule;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RealtimeDatabase_ReadSample extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_realtime_database_read_sample);
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("bookDatas");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object obj = dataSnapshot.getValue();
                if (obj != null)
                    Log.d("ta", "Value is: " + obj);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("ta", "Failed to read value.", error.toException());
            }
        });

        mDatabase.child("test2").get().addOnCompleteListener(task -> {

            BookSampleDataAndMemosModule getValue;
            if (!task.isSuccessful()) {
                Toast.makeText(RealtimeDatabase_ReadSample.this, "Error getting data", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(RealtimeDatabase_ReadSample.this,  String.valueOf(task.getResult().getValue()), Toast.LENGTH_SHORT).show();
                getValue = task.getResult().getValue(BookSampleDataAndMemosModule.class);
                Toast.makeText(this, Objects.requireNonNull(getValue).getMemoList().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        List<BookSampleDataAndMemosModule> list = new ArrayList<>();

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                list.add(snapshot.getValue(BookSampleDataAndMemosModule.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<BookMarkData> memoList = new ArrayList<>();
        memoList.add(new BookMarkData(1, 0, "veryGood", false));
        memoList.add(new BookMarkData(2, 1, "VeryUsefully", true));
        mDatabase.child("test3").child("memoList").setValue(memoList);

        // Firebaseストレージへの参照を取得する
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
// テキストファイルを示すStorageReferenceを取得する
        StorageReference textRef = storageRef.child("Githubトークン.txt");

// テキストファイルを取得する
        textRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(bytes -> {
            // ファイルのダウンロードに成功した場合の処理
            String text = new String(bytes, StandardCharsets.UTF_8);
            Toast.makeText(RealtimeDatabase_ReadSample.this, text, Toast.LENGTH_SHORT).show();
            Log.d("ta", "Downloaded text file: " + text);
        }).addOnFailureListener(exception -> {

            // ファイルのダウンロードに失敗した場合の処理
            Toast.makeText(RealtimeDatabase_ReadSample.this, "Error", Toast.LENGTH_SHORT).show();
            Log.e("ta", "Error downloading text file: " + exception.getMessage());
        });

    }
}