package com.example.parth_c0766346_feandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewContact extends AppCompatActivity {

    Boolean isNewContact;
    DatabaseHelper mDBhelper;
    EditText firstET, lastET, numET, addressET;
    Contact contactData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);


        mDBhelper = new DatabaseHelper(this);
        firstET = findViewById(R.id.firstName);
        lastET = findViewById(R.id.lastName);
        numET = findViewById(R.id.phone);
        addressET = findViewById(R.id.address);



        Intent i = getIntent();
        isNewContact = i.getBooleanExtra("NEW", true);

        if (!isNewContact){

            contactData = (Contact) i.getSerializableExtra("DATA");
            firstET.setText(contactData.getFirst_name());
            lastET.setText(contactData.getLast_name());
            numET.setText(contactData.getPhone());
            addressET.setText(contactData.getAddress());

        }


    }

    public void saveAndGoBack(View view) {

        // Add or Edit to database

        if (isNewContact){
            addToDatabase();
        }
        else{
            editDatabase();
        }


        // go back

        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


    public void addToDatabase(){

        Boolean added = mDBhelper.addContact(firstET.getText().toString(), lastET.getText().toString(),
                numET.getText().toString(), addressET.getText().toString());


        Toast.makeText(this, added ? "Added" : "Not Added", Toast.LENGTH_SHORT).show();

    }

    public void editDatabase(){

        Boolean updated = mDBhelper.editContact(contactData.getId(),firstET.getText().toString(), lastET.getText().toString(),
                numET.getText().toString(), addressET.getText().toString());


        Toast.makeText(this, updated ? "Updated" : "Not Updated", Toast.LENGTH_SHORT).show();

    }
}
