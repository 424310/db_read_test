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

public class menu_FirebaseDatabaseHelper {
    private String UserId, name;
    public FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef, myRef;
    private List<Menu> menus = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Menu> menus, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public menu_FirebaseDatabaseHelper() {
        //mAuth = FirebaseAuth.getInstance();
        //UserId = mAuth.getCurrentUser().getDisplayName();
        UserId = "서민지";
        //카테고리 이름은 어떻게 가져와야할까?
        name = "갯마을칼국수";

        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference(UserId);
        myRef = databaseRef.child("Menu").child(name);
    }

    public void readMenus(final DataStatus dataStatus){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menus.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Menu menu = keyNode.getValue(Menu.class);
                    menus.add(menu);
                }
                dataStatus.DataIsLoaded(menus, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}