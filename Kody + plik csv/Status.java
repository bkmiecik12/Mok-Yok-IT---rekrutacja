import org.json.simple.JSONObject;

import java.sql.Timestamp;

/**
 * Created by Bartłomiej on 25.10.2017.
 */
public class Status implements Comparable<Status> {

    long kontakt_id;
    long klient_id;
    long pracownik_id;
    String status;
    Timestamp kontakt_ts;

    Status(JSONObject o){
        kontakt_id = (long) o.get("kontakt_id");
        klient_id = (long) o.get("klient_id");
        pracownik_id = (long) o.get("pracownik_id");
        status = (String) o.get("status");
        kontakt_ts = Timestamp.valueOf((String) o.get("kontakt_ts"));
    }

    @Override
    public int compareTo(Status o) {
        int comparedIDs = klient_id > o.klient_id ? 1 : klient_id == o.klient_id ? 0 : -1;
        if(comparedIDs==0) return kontakt_ts.compareTo(o.kontakt_ts);
        else return comparedIDs;
    }
}
