package hfad.com.toddybusinessman;
import java.util.ArrayList;

public class ToddyBatch {
    int batch_id;
    String date_created;
    int volume;
    String creator_permit;
    String creator_name;
    String current_owner_permit;
    String current_owner_name;
    String current_owner_purchase_date;

    public ToddyBatch(int batch_id, String date_created, int volume, String creator_permit, String creator_name, String current_owner_permit, String current_owner_name, String current_owner_purchase_date) {
        this.batch_id = batch_id;
        this.date_created = date_created;
        this.volume = volume;
        this.creator_permit = creator_permit;
        this.creator_name = creator_name;
        this.current_owner_permit = current_owner_permit;
        this.current_owner_name = current_owner_name;
        this.current_owner_purchase_date = current_owner_purchase_date;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public int getVolume() {
        return volume;
    }

    public String getCreator_permit() {
        return creator_permit;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public String getCurrent_owner_permit() {
        return current_owner_permit;
    }

    public String getCurrent_owner_name() {
        return current_owner_name;
    }

    public String getCurrent_owner_purchase_date() {
        return current_owner_purchase_date;
    }

}
