package com.example.parth_c0766346_feandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContactAdaptor extends ArrayAdapter {

    Context context;
    ArrayList<Contact> contactList;
    int layoutRes;
    DatabaseHelper mDatabase;

    public ContactAdaptor(@NonNull Context context, int resource, ArrayList<Contact> contactList, DatabaseHelper mDatabase) {
        super(context, resource, contactList);
        this.context = context;
        this.contactList = contactList;
        this.layoutRes = resource;
        this.mDatabase = mDatabase;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(layoutRes, null);

        TextView fname = v.findViewById(R.id.firstName);
        TextView lname = v.findViewById(R.id.lastName);
        TextView phoneNum = v.findViewById(R.id.phone);
        TextView address = v.findViewById(R.id.address);
        Button delButton = v.findViewById(R.id.delBTN);



        final Contact c = contactList.get(position);
        fname.setText(c.getFirst_name());
        lname.setText(c.getLast_name());
        phoneNum.setText(c.getPhone());
        address.setText(c.getAddress());


        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.deleteContact(c.getId());
                refreshList();
            }
        });

        v.findViewById(R.id.ll_adaptor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewContact.class);
                i.putExtra("NEW",false);
                i.putExtra("DATA",c);
                context.startActivity(i);

            }
        });

        return v;
    }

    private void refreshList() {

        Cursor cursor = mDatabase.getAllContacts();
        contactList.clear();

        if (cursor.moveToFirst()) {


            do {
                contactList.add(new Contact(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());
            cursor.close();
        }
            notifyDataSetChanged();


    }

}
