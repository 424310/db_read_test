package com.example.db_read_test;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper111 {
    private String UserId;
    public FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef, myRef;
    private List<Category> categories = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Category> categories, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper111() {
        //mAuth = FirebaseAuth.getInstance();
        //UserId = mAuth.getCurrentUser().getDisplayName();
        UserId = "서민지";
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference(UserId);
        myRef = databaseRef.child("Category");
    }

    public void readCategories(final DataStatus dataStatus){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categories.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Category category = keyNode.getValue(Category.class);
                    categories.add(category);
                }
                dataStatus.DataIsLoaded(categories, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}