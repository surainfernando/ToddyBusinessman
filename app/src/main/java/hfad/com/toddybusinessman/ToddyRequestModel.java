package hfad.com.toddybusinessman;

public class ToddyRequestModel {
    int request_id;
    int approval_status;
    int volume;
    int batch_id ;
    String date_created;

    public ToddyRequestModel(int request_id, int approval_status, int volume, int batch_id, String date_created, String buyer_permit_number, String seller_permit_number, String seller_name, String buyer_name) {
        this.request_id = request_id;
        this.approval_status = approval_status;
        this.volume = volume;
        this.batch_id = batch_id;
        this.date_created = date_created;
        this.buyer_permit_number = buyer_permit_number;
        this.seller_permit_number = seller_permit_number;
        this.seller_name = seller_name;
        this.buyer_name = buyer_name;
    }

    public int getRequest_id() {
        return request_id;
    }

    public int getApproval_status() {
        return approval_status;
    }

    public int getVolume() {
        return volume;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getBuyer_permit_number() {
        return buyer_permit_number;
    }

    public String getSeller_permit_number() {
        return seller_permit_number;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    String buyer_permit_number ;
    String seller_permit_number;
    String seller_name;
    String buyer_name;
}

