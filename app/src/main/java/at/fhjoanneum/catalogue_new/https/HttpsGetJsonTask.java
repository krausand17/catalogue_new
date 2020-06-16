package at.fhjoanneum.catalogue_new.https;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;

import at.fhjoanneum.catalogue_new.MainActivity;

public class HttpsGetJsonTask extends AsyncTask<String, Void, JSONArray> {

    MainActivity ma;

    public HttpsGetJsonTask(MainActivity ma) {
            this.ma = ma;
        }


    @Override
    protected JSONArray doInBackground(String... urls) {
        String url = urls[0];
        Log.i("andi","json url: \n"+url);
        HttpsJsonClient jsonClient = new HttpsJsonClient();
        JSONArray jsonArray = new JSONArray();


            try{
                jsonArray = jsonClient.getJson(new URL(url));
                //JSONObject jsonObject = jsonArray.getJSONObject(0);
            }catch (IOException e){
                e.printStackTrace();
            }
            Log.i("andi","JsonArray task: \n"+jsonArray.toString());
            Log.i("andi","JsonArray length: "+jsonArray.length());
            return jsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        if (jsonArray.length()>1)
            ma.parseBreedJson(jsonArray);
        else
            ma.parseImgJson(jsonArray);
    }
}
