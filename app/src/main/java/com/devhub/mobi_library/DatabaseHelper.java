package com.devhub.mobi_library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MobiLibrary.db";

    // User table name
    private static final String TABLE_USER = "user";

    //Region Table

    private static final String TABLE_BOOKS = "books";


    private static final String TABLE_MALL = "mall";

    private static final String COLUMN_BOOK_ID = "book_id";

    private static final String COLUMN_AUTHOR_INITIAL = "author_initial";
    private static final String COLUMN_AUTHOR_SURNAME = "author_surname";
    private static final String COLUMN_BOOK_TITLE = "book_title";
    private static final String COLUMN_BOOK_EDITION = "book_edition";
    private static final String COLUMN_BOOK_ISBN = "book_isbn";


    private static final String COLUMN_BOOKING_ID = "booking_id";
    private static final String COLUMN_BOOKING_PLACE = "booking_place";
    private static final String COLUMN_BOOKER_NAME = "booker_name";


    private static final String TABLE_SLOT = "slots";
    private static final String COLUMN_SLOT_ID = "slot_id";
    private static final String COLUMN_SLOT_NAME = "slot_name";
    private static final String COLUMN_SLOT_REGION = "slot_region";
       private static final String COLUMN_SLOT_STATUS= "slot_status";



    private static final String COLUMN_REGION_ID = "region_id";
    private static final String COLUMN_REGION_NAME = "region_name";





    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_NUMBER = "user_number";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_HINT = "user_hint";
    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"+ COLUMN_USER_EMAIL +" TEXT,"+ COLUMN_USER_NUMBER + " TEXT," + COLUMN_USER_HINT + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";
    private String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOKS + "(" + COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_AUTHOR_INITIAL + " TEXT,"+ COLUMN_AUTHOR_SURNAME + " TEXT,"+ COLUMN_BOOK_TITLE + " TEXT,"+ COLUMN_BOOK_EDITION + " TEXT," + COLUMN_BOOK_ISBN + " TEXT" + ")";
    private String CREATE_SLOT_TABLE = "CREATE TABLE " + TABLE_SLOT + "(" + COLUMN_SLOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SLOT_NAME + " TEXT,"+ COLUMN_REGION_NAME + " TEXT," + COLUMN_SLOT_STATUS + " TEXT" + ")";
 //   private String CREATE_BOOKING_TABLE = "CREATE TABLE " + TABLE_BOOKING + "(" + COLUMN_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SLOT_NAME + " TEXT," + COLUMN_MALL_NAME + " TEXT," + COLUMN_REGION_NAME + " TEXT,"+ COLUMN_BOOKER_NAME + " TEXT" + ")";

   //   private String CREATE_MALL_TABLE = "CREATE TABLE " + TABLE_MALL + "(" + COLUMN_MALL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MALL_NAME + " TEXT,"+ COLUMN_MALL_LOCATION + " TEXT," + COLUMN_MALL_TIMING + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_REGION_TABLE = "DROP TABLE IF EXISTS " + TABLE_BOOKS;
    private String DROP_SLOT_TABLE = "DROP TABLE IF EXISTS " + TABLE_SLOT;
   // private String DROP_BOOKING_TABLE = "DROP TABLE IF EXISTS " + TABLE_BOOKING;

    private String DROP_BOOK_TABLE = "DROP TABLE IF EXISTS " + TABLE_BOOKS;
    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_SLOT_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_REGION_TABLE);
        db.execSQL(DROP_SLOT_TABLE);
        db.execSQL(DROP_BOOK_TABLE);
        // Create tables again
        onCreate(db);

    }


    public String getAllTags(String a) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + "user" + " where user_hint = '" +a + "'" , null);
        String str = null;
        if (c.moveToFirst()) {
            do {
                str = c.getString(c.getColumnIndex("user_password"));
            } while (c.moveToNext());
        }
        return str;
    }




    public ArrayList<Books> getBooks() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_BOOK_TITLE,
                COLUMN_BOOK_EDITION,
                COLUMN_AUTHOR_SURNAME,
                COLUMN_BOOK_ID,
        };
        // sorting orders
        String sortOrder =
                COLUMN_BOOK_TITLE + " ASC";
        ArrayList<Books> userList = new ArrayList<Books>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BOOKS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Books user = new Books();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_BOOK_ID)));
                user.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_TITLE)));
                user.setEdition(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_EDITION)));
                user.setSurname(cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR_SURNAME)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public void update_book(Books books, String id ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_AUTHOR_INITIAL,books.getInitial());
        values.put(COLUMN_AUTHOR_SURNAME,books.getSurname());
        values.put(COLUMN_BOOK_EDITION,books.getEdition());
        values.put(COLUMN_BOOK_TITLE,books.getTitle());
        values.put(COLUMN_BOOK_ISBN,books.getIsbn());
        db.update(TABLE_BOOKS, values, " book_id=?", new String[] { id});


        db.close();
    }


    public List<Books> get_book_info(String id) {
        // array of columns to fetch
        String[] columns = {

                COLUMN_BOOK_TITLE,
                COLUMN_BOOK_ISBN,
                COLUMN_BOOK_EDITION,
                COLUMN_AUTHOR_SURNAME,
                COLUMN_AUTHOR_INITIAL,
        };
        // sorting orders
        String sortOrder =
                COLUMN_SLOT_NAME + " ASC";
        List<Books> userList = new ArrayList<Books>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_BOOK_ID + " = ?";
        String[] selectionArgs = {id};
        Cursor cursor = db.query(TABLE_BOOKS, //Table to query
                columns,    //columns to return
                selection ,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Books user = new Books();
                user.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_TITLE)));
                user.setIsbn(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_ISBN)));
                user.setEdition(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_EDITION)));
                user.setSurname(cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR_SURNAME)));
                user.setInitial(cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR_INITIAL)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }



    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_NUMBER, user.getNumber());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_HINT, user.getHint());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }


    public void addBooks(Books books) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_AUTHOR_INITIAL, books.getInitial());
        values.put(COLUMN_AUTHOR_SURNAME, books.getSurname());
        values.put(COLUMN_BOOK_TITLE, books.getTitle());
        values.put(COLUMN_BOOK_EDITION, books.getEdition());
        values.put(COLUMN_BOOK_ISBN, books.getIsbn());

        // Inserting Row
        db.insert(TABLE_BOOKS, null, values);
        db.close();
    }












    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_NUMBER
        };
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setNumber(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NUMBER)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }



    public void updatepassword(User user,String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_EMAIL + " = ?",
                new String[]{email});
        db.close();
    }
    public void deleteBook1(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public void deleteBook(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BOOKS+ " WHERE "+COLUMN_BOOK_ID+"='"+value+"'");
        db.close();
    }


    public boolean checkUser(String email) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {


        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }




}