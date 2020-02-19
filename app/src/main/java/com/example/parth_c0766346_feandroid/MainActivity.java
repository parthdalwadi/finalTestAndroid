package com.example.parth_c0766346_feandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView LVContacts;
    ArrayList<Contact> ContactList, SearchList;

    DatabaseHelper mDBhelper;
    SearchView srch;
    ContactAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBhelper = new DatabaseHelper(this);

        ContactList = new ArrayList<>();
        LVContacts = findViewById(R.id.contactList);
        srch = findViewById(R.id.searchBar);

        srch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                System.out.println("this is called");


                SearchList = new ArrayList<>();
                if (!newText.isEmpty()){


                    for (Contact c:ContactList
                         ) {

                        if (c.getFirst_name().contains(newText) || c.getLast_name().contains(newText)){

                            System.out.println("match");

                            SearchList.add(c);

                        }

                    }

                    adaptor = new ContactAdaptor(MainActivity.this, R.layout.contact_format, SearchList, mDBhelper);
                    LVContacts.setAdapter(adaptor);
                }else{

                    getAllData();
                    

                }
                return false;
            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();


        getAllData();
    }

    public void addNewContact(View view) {

        Intent i = new Intent(this, NewContact.class);
        i.putExtra("NEW",true);
        startActivity(i);


    }


    public void getAllData(){


        Cursor cursor = mDBhelper.getAllContacts();
        ContactList.clear();

        if (cursor.moveToFirst()) {


            do {
                ContactList.add(new Contact(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());
            cursor.close();
        }

        adaptor = new ContactAdaptor(this, R.layout.contact_format, ContactList, mDBhelper);
        LVContacts.setAdapter(adaptor);



    }
}
