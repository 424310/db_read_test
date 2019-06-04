package com.example.db_read_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MenuListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_menus);
        new menu_FirebaseDatabaseHelper().readMenus(new menu_FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Menu> menus, List<String> keys) {
                new menu_RecyclerView_Config().setConfig(mRecyclerView, MenuListActivity.this, menus, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
}
