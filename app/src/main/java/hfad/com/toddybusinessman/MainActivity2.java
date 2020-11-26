package hfad.com.toddybusinessman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // Initialize contacts
        contacts = Contact.createContactsList(20);
        // Create adapter passing in the sample user data
        //BatchAdapter adapter = new BatchAdapter(contacts);
        // Attach the adapter to the recyclerview to populate items
        //rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
       // rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}