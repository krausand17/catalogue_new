package at.fhjoanneum.catalogue_new.https;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;


public class HttpsGetImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;

    public HttpsGetImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        String bmp_url;
        HttpsJsonClient jsonClient = new HttpsJsonClient();
        HttpsImageClient ic = new HttpsImageClient();
        Bitmap bitmap = null;
        JSONArray jsonArray = new JSONArray();


       /* try{
            jsonArray = jsonClient.getJson(new URL(json_url));
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            bmp_url = jsonObject.getString("url");
            bitmap = ic.getImage(new URL(bmp_url));
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        try {
            bitmap = ic.getImage(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
        //if (callback != null ) callback.onResult(bitmap);
    }
}
