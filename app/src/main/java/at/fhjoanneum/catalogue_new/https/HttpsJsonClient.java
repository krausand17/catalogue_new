package at.fhjoanneum.catalogue_new.https;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsJsonClient {

    private HttpsURLConnection connection = null;

    public JSONArray getJson(URL url) throws IOException {

        String resultString = "";
        JSONArray result = new JSONArray();
        InputStream input = null;
        BufferedReader reader = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line=reader.readLine()) != null){
                buffer.append(line + "\n");
            }
            resultString = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) input.close();
            if (reader != null) reader.close();
            if (connection != null) connection.disconnect();
        }
        try {
            result = new JSONArray(resultString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}

