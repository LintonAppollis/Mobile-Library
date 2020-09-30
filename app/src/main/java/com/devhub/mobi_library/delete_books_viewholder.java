package com.devhub.mobi_library;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class delete_books_viewholder extends RecyclerView.Adapter<delete_books_viewholder.contacts>{

    Context context;
    ArrayList <Books> _books;


    DatabaseHelper databaseHelper;

    public delete_books_viewholder(Context c , ArrayList<Books> b)
    {
        context = c;
        _books = b;
        databaseHelper=new DatabaseHelper(context);
    }


    @NonNull
    @Override
    public contacts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new contacts(LayoutInflater.from(context).inflate(R.layout.list_design,parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull final contacts holder, final int position) {
//        holder.Id.setText(_books.get(position).getName());


        holder.Name.setText(_books.get(position).getTitle());
        holder.Id.setText(String.valueOf(_books.get(position).getId()));
        holder.Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 databaseHelper.deleteBook(String.valueOf(_books.get(position).getId()));
                _books.remove(position);
                notifyDataSetChanged();

            }
        });








    }



    @Override
    public int getItemCount() {
        return _books.size();
    }


    public void filterList(ArrayList<Books> filteredList) {
        _books = filteredList;
        notifyDataSetChanged();
    }





    class contacts extends RecyclerView.ViewHolder{
        TextView Name;
        TextView Id;
        ImageView Del;
        CardView cardView;

        public contacts(View itemView){
            super(itemView);
            Name  = (TextView) itemView.findViewById(R.id.book_name);
            Id  = (TextView) itemView.findViewById(R.id.b_id);
            Del = (ImageView) itemView.findViewById(R.id.del);
            cardView = (CardView)itemView.findViewById(R.id.cardview);
        }
    }





}
