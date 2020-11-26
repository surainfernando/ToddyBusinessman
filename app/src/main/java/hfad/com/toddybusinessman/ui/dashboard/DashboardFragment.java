package hfad.com.toddybusinessman.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import hfad.com.toddybusinessman.BatchAdapter;
import hfad.com.toddybusinessman.Contact;
import hfad.com.toddybusinessman.R;

public class DashboardFragment extends Fragment {
    ArrayList<Contact> contacts;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView rvContacts = (RecyclerView)root.findViewById(R.id.rvContacts);

        // Initialize contacts
        contacts = Contact.createContactsList(20);
        // Create adapter passing in the sample user data
//        BatchAdapter adapter = new BatchAdapter(contacts);
//        // Attach the adapter to the recyclerview to populate items
//        rvContacts.setAdapter(adapter);
//        // Set layout manager to position the items
//        rvContacts.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}