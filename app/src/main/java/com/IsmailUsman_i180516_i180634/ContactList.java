package com.IsmailUsman_i180516_i180634;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;



public class ContactList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ContactModel> arrayList  = new ArrayList<ContactModel>();
    MainAdapter adapter;
    ImageButton mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        recyclerView = findViewById(R.id.recycler_view);
        checkPermission();

        mess = findViewById(R.id.message4);
        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void checkPermission(){

        if(ContextCompat.checkSelfPermission(ContactList.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(ContactList.this,new String[]{Manifest.permission.READ_CONTACTS},100);
        }else{
            getContactList();
        }
    }

    private void getContactList() {

        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
        Cursor cursor = getContentResolver().query(uri,null,null,null,sort);

        if(cursor.getCount()>0){

            while(cursor.moveToNext()){
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" =?";

                Cursor phoneCursor = getContentResolver().query(uriPhone,null,selection,new String[]{id},null);

                if(phoneCursor.moveToNext()){

                    @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    ContactModel model = new ContactModel();
                    model.setName(name);
                    model.setNumber(number);
                    arrayList.add(model);
                    phoneCursor.close();

                }
            }
            cursor.close();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            getContactList();
        }else {
            Toast.makeText(ContactList.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            checkPermission();
        }

    }

}