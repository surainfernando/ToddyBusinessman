package hfad.com.toddybusinessman.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hfad.com.toddybusinessman.Add_Toddy_Batch;
import hfad.com.toddybusinessman.BatchAdapter;
import hfad.com.toddybusinessman.ConnectionString;
import hfad.com.toddybusinessman.Contact;
import hfad.com.toddybusinessman.Login;
import hfad.com.toddybusinessman.R;
import hfad.com.toddybusinessman.ToddyBatch;
import hfad.com.toddybusinessman.ui.notifications.NotificationsViewModel;


public class CurrentInventory extends Fragment {
    ArrayList<Contact> contacts;
    ArrayList<ToddyBatch> toddyBatchList;
Button addButton;
Button refreshButton;
RecyclerView rvContacts;


    @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                ViewGroup container, Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.fragment_current_inventory, container, false);
            addButton =root.findViewById(R.id.addButton);
            refreshButton=root.findViewById(R.id.refreshButton);
            //addButton.setText("Hello");
            initialActions();
            setListener();
            rvContacts = (RecyclerView)root.findViewById(R.id.rvContacts);


            // Initialize contacts
            contacts  = new ArrayList<Contact>();
            toddyBatchList=new ArrayList<ToddyBatch>();
            Contact contact=new Contact("opppa",true);
        Contact contact2=new Contact("oppfffpa",true);
        Contact contact3=new Contact("opppa",true);
            contacts.add(contact);
        contacts.add(contact2);
        contacts.add(contact3);
            // Create adapter passing in the sample user data
//            BatchAdapter adapter = new BatchAdapter(contacts);
//            // Attach the adapter to the recyclerview to populate items
//            rvContacts.setAdapter(adapter);
//            // Set layout manager to position the items
//            rvContacts.setLayoutManager(new LinearLayoutManager(getContext()));



            return root;
        }

        public void initialActions()
        {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String business_type = preferences.getString("business_type", "");
            Log.i("Btype", business_type);
            business_type=business_type.trim();
            if(business_type.equals("Toddy Producer"))
            {

            }
            else
            {
                Log.i("Btype", "Link come");
                addButton.setVisibility(View.GONE);

            }
        }
        public void setListener()
        {
            addButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent=new Intent(getActivity(), Add_Toddy_Batch.class);
                    startActivity(intent);


                }
            });
            refreshButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    String name = preferences.getString("name", "");
                    String permit_numbr= preferences.getString("permit", "");
                    JSONObject postData = new JSONObject();
                    try {
                        postData.put("name", name);
                        postData.put("permit", permit_numbr);



                        sendRequest(postData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }





                }
            });
        }

    public void sendRequest(JSONObject postData)//
    {
        ConnectionString obj=new ConnectionString();

        String postUrl = obj.connectioncode+"toddybatch/getToddyTapperBatch";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               Log.i("MyActivity", "7777777777777777777777777777777777");
                try{
                    JSONArray user_wallets = response.getJSONArray("result");
                    JSONObject[] batchList=new JSONObject[user_wallets.length()];
                    for(int i = 0; i<user_wallets.length();i++){

                        JSONObject wallet = user_wallets.getJSONObject(i);
                        batchList[i]=wallet;
                    }
                    System.out.println(batchList[0]);
                    System.out.println(batchList[1]);




                }
                catch(Exception e)
                {

                }
                //System.out.println(response);
                //Log.i("MyActivity", response);
                handleListView(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyActivity", "New Emaicdfffffffffffffffffffffffffffffffffffffffffffffffffffl");
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public void handleListView(JSONObject response)
    {
        Log.i("MyActivity", "7777777777777777777777777777777777");
        try{
            JSONArray user_wallets = response.getJSONArray("result");
            JSONObject[] batchList=new JSONObject[user_wallets.length()];
            for(int i = 0; i<user_wallets.length();i++){

                JSONObject wallet = user_wallets.getJSONObject(i);
                batchList[i]=wallet;
                String x=wallet.getString("creator_name");
                Contact contact1=new Contact(x,true);
                ToddyBatch batch=new ToddyBatch(wallet.getInt("batch_id"),wallet.getString("date_created"),wallet.getInt("volume"),wallet.getString("creator_permit"),wallet.getString("creator_name"),wallet.getString("current_owner_permit"),wallet.getString("current_owner_name"),wallet.getString("current_owner_purchase_date"));
                toddyBatchList.add(batch);
            }
            BatchAdapter adapter = new BatchAdapter(toddyBatchList);
            // Attach the adapter to the recyclerview to populate items
            rvContacts.setAdapter(adapter);
            // Set layout manager to position the items
            rvContacts.setLayoutManager(new LinearLayoutManager(getContext()));





        }
        catch(Exception e)
        {

        }
        //System.out.println(response);
        //Log.i("MyActivity", response);

    }
    }
