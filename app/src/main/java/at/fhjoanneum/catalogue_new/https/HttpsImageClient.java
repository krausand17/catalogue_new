package at.fhjoanneum.catalogue_new.https;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsImageClient  {
    private HttpsURLConnection connection = null;

    public Bitmap getImage(URL url) throws IOException {
        Bitmap bitmap = null;
        InputStream input = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input!= null) input.close();
            if (connection != null) connection.disconnect();
        }
        return bitmap;
    }

}


