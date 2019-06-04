package com.example.db_read_test;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class menu_RecyclerView_Config {
    private Context mContext;
    private MenusAdapter mMenusAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Menu> menus, List<String> keys){
        mContext = context;
        mMenusAdapter = new MenusAdapter(menus, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mMenusAdapter);
    }

    //뷰홀더 BookItemView
    class MenuItemView extends RecyclerView.ViewHolder{
        //onclick 위해 추가한 코드
        public final View mView;

        private TextView mMenu_name;
        private TextView mMenu_price;
        private TextView mMenu_comment;

        private String key;

        public MenuItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.menu_list_item, parent, false));

            //onclick 위해 추가한 코드
            mView = itemView;

            mMenu_name= (TextView) itemView.findViewById(R.id.menu_name_txtView);
            mMenu_price = (TextView) itemView.findViewById(R.id.menu_price_txtView);
            mMenu_comment = (TextView) itemView.findViewById(R.id.menu_comment_txtView);
        }

        public void bind(Menu menu, String key){
                        mMenu_name.setText(menu.getMenu_name());
            mMenu_price.setText(menu.getMenu_price());
            mMenu_comment.setText(menu.getMenu_comment());
            this.key = key;
        }
    }

    class MenusAdapter extends RecyclerView.Adapter<MenuItemView>{
        private List<Menu> mMenuList;
        private List<String> mKeys;

        public MenusAdapter(List<Menu> mMenuList, List<String> mKeys) {
            this.mMenuList = mMenuList;
            this.mKeys = mKeys;
        }

        public MenusAdapter() {
            super();
        }

        @NonNull
        @Override
        public MenuItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MenuItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuItemView bookItemView, final int i) {
            bookItemView.bind(mMenuList.get(i), mKeys.get(i));

            //here it is simply write onItemClick listener here
            bookItemView.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent =  new Intent(context, Test.class);

                    intent.putExtra("n", mMenuList.get(i).getMenu_name());
                    intent.putExtra("p",  mMenuList.get(i).getMenu_price());
                    intent.putExtra("c",  mMenuList.get(i).getMenu_comment());

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() { //데이터 수 반환
            return mMenuList.size();
        }
    }
}