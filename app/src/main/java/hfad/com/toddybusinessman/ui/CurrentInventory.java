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

import hfad.com.toddybusinessman.Add_Toddy_Batch;
import hfad.com.toddybusinessman.Login;
import hfad.com.toddybusinessman.R;
import hfad.com.toddybusinessman.ui.notifications.NotificationsViewModel;


public class CurrentInventory extends Fragment {
Button addButton;


        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                ViewGroup container, Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.fragment_current_inventory, container, false);
            addButton =root.findViewById(R.id.addButton);
            //addButton.setText("Hello");
            initialActions();
            setListener();



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
        }
    }
