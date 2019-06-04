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

public class RecyclerView_Config {
    private Context mContext;
    private BooksAdapter mBooksAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Book> books, List<String> keys){
        mContext = context;
        mBooksAdapter = new BooksAdapter(books, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdapter);
    }

    //뷰홀더 BookItemView
    class BookItemView extends RecyclerView.ViewHolder{
        //onclick 위해 추가한 코드
        public final View mView;

        private TextView mTitle;
        private TextView mAuthor;
        private TextView mISBN;
        private TextView mCategory;

        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.book_list_item, parent, false));

            //onclick 위해 추가한 코드
            mView = itemView;

            mTitle = (TextView) itemView.findViewById(R.id.title_txtView);
            mAuthor = (TextView) itemView.findViewById(R.id.author_txtView);
            mCategory = (TextView) itemView.findViewById(R.id.category_txtView);
            mISBN = (TextView) itemView.findViewById(R.id.isbn_txtView);
        }

        public void bind(Book book, String key){
            mTitle.setText(book.getTitle());
            mAuthor.setText(book.getAuthor());
            mCategory.setText(book.getCategory_name());
            mISBN.setText(book.getIsbn());
            this.key = key;
        }
    }

    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Book> mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Book> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        public BooksAdapter() {
            super();
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new BookItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView bookItemView, final int i) {
            bookItemView.bind(mBookList.get(i), mKeys.get(i));

            //here it is simply write onItemClick listener here
            bookItemView.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent =  new Intent(context, Next.class);

                    intent.putExtra("Title", mBookList.get(i).getTitle());
                    intent.putExtra("Author",  mBookList.get(i).getAuthor());
                    intent.putExtra("Category",  mBookList.get(i).getCategory_name());
                    intent.putExtra("ISBN", mBookList.get(i).getIsbn());

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() { //데이터 수 반환
            return mBookList.size();
        }
    }
}