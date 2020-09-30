package com.devhub.mobi_library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_add,btn_delete,btn_view,btn_edit,btn_staus;
    RelativeLayout info_img_layout;
    private DatabaseHelper databaseHelper;
    private Books books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        books=new Books();
        databaseHelper=new DatabaseHelper(MainActivity.this);


        btn_add=findViewById(R.id.add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(MainActivity.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);

                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this,R.style.CustomAlertDialog);
                alertDialogBuilderUserInput.setView(mView);
                final EditText initial = (EditText) mView.findViewById(R.id.txt_initial);
                final EditText surname = (EditText) mView.findViewById(R.id.txt_surname);
                final EditText title = (EditText) mView.findViewById(R.id.txt_book_title);
                final EditText edition = (EditText) mView.findViewById(R.id.txt_book_edition);
                final EditText isbn = (EditText) mView.findViewById(R.id.txt_book_isbn);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("Add Book ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {



                                books.setInitial(initial.getText().toString().trim());
                                books.setSurname(surname.getText().toString().trim());
                                books.setTitle(title.getText().toString().trim());
                                books.setEdition(edition.getText().toString().trim());
                                books.setIsbn(isbn.getText().toString().trim());

                                databaseHelper.addBooks(books);
                                Toast.makeText(getBaseContext(), "Book Added Successfully To Database", Toast.LENGTH_SHORT).show();




                            }




                        })

                        .setNegativeButton("Dismiss",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                alertDialogAndroid.show();
            }


        });

        btn_delete=findViewById(R.id.delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,delete_books_activity.class));
            }
        });

        btn_view=findViewById(R.id.view);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,view_books_activity.class));
            }
        });

        btn_edit=findViewById(R.id.edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,edit_books_activity.class));
            }
        });

        btn_staus=findViewById(R.id.status);
        btn_staus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Function to be Added Here - future enhancement",Toast.LENGTH_LONG).show();
            }
        });

        info_img_layout=findViewById(R.id.info_btn);
        info_img_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Mobi Library")
                        .setMessage("Good record keeping is important for effective Library Management; this digital solution will assist librarians to manage, file and organize records appropriately. It does'nt keep track of any employee personal information. ")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });



    }
}
