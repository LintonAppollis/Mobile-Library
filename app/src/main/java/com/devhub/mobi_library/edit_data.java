package com.devhub.mobi_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.List;

public class edit_data extends AppCompatActivity {
    EditText initial,surname,title,edition,isbn;
    DatabaseHelper databaseHelper;
    Button btn_update,btn_cancel;
    Books books;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        databaseHelper=new DatabaseHelper(edit_data.this);
         id = getIntent().getExtras().getString("id","defaultKey");
        books=new Books();
         initial = (EditText) findViewById(R.id.txt_initial);
         surname = (EditText) findViewById(R.id.txt_surname);
        title = (EditText) findViewById(R.id.txt_book_title);
         edition = (EditText) findViewById(R.id.txt_book_edition);
        isbn = (EditText) findViewById(R.id.txt_book_isbn);
        btn_update=findViewById(R.id.btn_update);
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(edit_data.this,edit_books_activity.class));
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                books.setInitial(initial.getText().toString());
                books.setSurname(surname.getText().toString());
                books.setTitle(title.getText().toString());
                books.setEdition(edition.getText().toString());
                books.setIsbn(isbn.getText().toString());
                databaseHelper.update_book(books,id);
                Toast.makeText(edit_data.this,"Data Updated Successfully",Toast.LENGTH_LONG).show();
            }
        });

        List<Books> books = databaseHelper.get_book_info(id);
        for (Books cn : books) {

            initial.setText(cn.getTitle());
            surname.setText(cn.getSurname());
            title.setText(cn.getTitle());
            edition.setText(cn.getEdition());
            isbn.setText(cn.getIsbn());

        }
    }

    @Override
    public void onBackPressed() {
        finish();
      startActivity(new Intent(edit_data.this,edit_books_activity.class));
    }
}