package hfad.com.toddybusinessman;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BatchAdapter extends
        RecyclerView.Adapter<BatchAdapter.ViewHolder> {
    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // ... constructor and member variables

    // Usually involves inflating a layout from XML and returning the holder

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;
        public TextView permit_number;
        public TextView date_created;
        public TextView volume;
        private Context context;




        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.

            super(itemView);
            this.context = context;


            messageButton = (Button) itemView.findViewById(R.id.message_button);

            date_created=(TextView)itemView.findViewById(R.id.date_created);
            volume=(TextView)itemView.findViewById(R.id.volume);
            itemView.setOnClickListener(this);



        }
        @Override
        public void onClick(View view) {

            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(itemView, position);
                }
            }
        }
    }
    private List<ToddyBatch> mContacts;

    // Pass in the contact array into the constructor
    public BatchAdapter(List<ToddyBatch> contacts) {
        mContacts = contacts;
    }
    @Override
    public BatchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.batch_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(BatchAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        ToddyBatch batch = mContacts.get(position);

        // Set item views based on your views and data model

        Button button = holder.messageButton;
        button.setText(batch.getCreator_permit());

        TextView date_created=holder.date_created;

        TextView volume=holder.volume;
        volume.setText(String.valueOf(batch.volume));
        try{
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String string1 =batch.getDate_created();

            Date result1 = df1.parse(string1);
            SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd-MM-yyyy");
            String DateStr = timeStampFormat.format(result1);
            date_created.setText(DateStr);

        }
        catch (ParseException e)
        {

        }




    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }


}