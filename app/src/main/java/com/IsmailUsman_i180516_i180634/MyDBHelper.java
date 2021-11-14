package com.IsmailUsman_i180516_i180634;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "signup1.db";

    public MyDBHelper(Context context) {
        super(context, "signup1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL(" create table users( email TEXT primary key , password TEXT , confirm_password TEXT) ");
        MyDB.execSQL(" create table profile( email TEXT primary key , firstName TEXT, lastName TEXT, gender TEXT, bio TEXT) ") ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB , int i, int i1) {

        MyDB.execSQL("drop table if exists users");
        MyDB.execSQL("drop table if exists profile");
    }

    public boolean insertData ( String email , String password , String confirm_password){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("email", email);
            contentValues.put("password", password);
            contentValues.put("confirm_password", confirm_password);

            long result = MyDB.insert("users", null, contentValues);

            if (result == -1)
                return false;

            else
                return true;
    }

    public boolean insertProfileData ( String email , String firstname , String lastname , String gender , String bio , String profileUriStr ){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email", email);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("gender", gender);
        contentValues.put("bio", bio);
        contentValues.put("profileUriStr", profileUriStr);


        long result = MyDB.insert("profile", null, contentValues);

        if (result == -1)
            return false;

        else
            return true;
    }

    public boolean checkemail( String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email=?" , new String[] {email}) ;

        if(cursor.getCount()>0)
            return false ;

        else
            return true ;
    }

    public boolean checkemailpassword( String email , String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email=? and password=?" , new String[] {email,password}) ;

        if(cursor.getCount()>0)
        {
            return true ;
        }

        else
            return false ;
    }

    public String fetchingquerry()
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String s = "" ;
        Cursor cursor = MyDB.rawQuery("select * from users" , null) ;

        cursor.moveToFirst() ;

        while ( !cursor.isAfterLast()){

            if(cursor.getString(cursor.getColumnIndex("email")) != null){
                s+= cursor.getString(cursor.getColumnIndex("email")) ;
                s+="\n";
            }
            cursor.moveToNext();
        }

        cursor.close();
        return s ;
    }

    public String fetchingquerryagain()
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String s = "" ;
        Cursor cursor = MyDB.rawQuery("select * from profile" , null) ;

        cursor.moveToFirst() ;

        while ( !cursor.isAfterLast()){

            if(cursor.getString(cursor.getColumnIndex("email")) != null){
                s+= cursor.getString(cursor.getColumnIndex("email")) ;
                s+="\n";
            }
            cursor.moveToNext();
        }

        cursor.close();
        return s ;
    }
}


