import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Bartłomiej on 25.10.2017.
 */
public class Converter {


    public static void main(String[] args) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        StringBuilder csv = new StringBuilder("kontakt_id|klient_id|pracownik_id|status|kontakt_ts\n");

        try {
            Object object = jsonParser.parse(new FileReader("test.json"));

            JSONArray array = (JSONArray) object;
            ArrayList<Status> statusArrayList = new ArrayList<>();
            for(int i=0;i<array.size();i++){
                Status s = new Status((JSONObject) array.get(i));
                statusArrayList.add(s);
            }

            statusArrayList.sort(Status::compareTo);
            //for(int i=0;i<=50;i++)
               // System.out.println(statusArrayList.get(i).klient_id+" "+statusArrayList.get(i).kontakt_ts);

            for(int i=0;i<array.size();i++){
                JSONObject jsonObject = (JSONObject) array.get(i);

                csv.append(statusArrayList.get(i).kontakt_id).append("|")
                        .append(statusArrayList.get(i).klient_id).append("|")
                        .append(statusArrayList.get(i).pracownik_id).append("|")
                        .append(statusArrayList.get(i).status).append("|")
                        .append(statusArrayList.get(i).kontakt_ts).append("\n");
            }
            //System.out.print(csv.toString());

            PrintWriter printWriter = new PrintWriter("statuses.csv");
            printWriter.write(csv.toString());
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
