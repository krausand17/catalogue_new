package at.fhjoanneum.catalogue_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.fhjoanneum.catalogue_new.helper.Breed;
import at.fhjoanneum.catalogue_new.https.HttpsGetImageTask;
import at.fhjoanneum.catalogue_new.https.HttpsGetJsonTask;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private ImageView mainImageView;
    private String api_url = "https://api.thecatapi.com/v1/images/search";
    private String breed_filter = "";
    private String breed_url = "https://api.thecatapi.com/v1/breeds";
    private List<Breed> breedList = new ArrayList<>();


    private List<String> breeds = new ArrayList<>();
    private Map<String,String> breedMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainImageView = (AppCompatImageView) findViewById(R.id.main_image);

        bottomAppBar = findViewById(R.id.bottomBar);
        setSupportActionBar(bottomAppBar);

        final FloatingActionButton actionButton = findViewById(R.id.actionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newRandomImg(api_url + breed_filter);
            }
        });

        //testList();
        //testMap();
        //setMainImage("https://cdn2.thecatapi.com/images/au1.jpg");


        downloadBreedList(breed_url);
        newRandomImg(api_url + breed_filter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.breed_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        menu.add(Menu.NONE,999,Menu.NONE,"Reset Filter");
        /*for(String breed : breedMap.keySet()){
            menu.add(breedMap.get(breed));
        }*/
        for(Breed br : breedList){
            menu.add(Menu.NONE,br.getId(),Menu.NONE,br.getName());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==999)
            breed_filter="";
        else {
            Breed br = breedList.get(item.getItemId());
            breed_filter = "?breed_ids=" + br.getIdString();
        }
        return true;
    }

    public void newRandomImg(String newImageUrl){
        HttpsGetJsonTask gj = new HttpsGetJsonTask(this);
        gj.execute(newImageUrl);
    }

    public void parseImgJson(JSONArray jsonArray){
        String url = "";
        try {
            JSONObject jo = jsonArray.getJSONObject(0);
            url = jo.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setMainImage(url);
    }

    public void setMainImage(String newImageUrl) {
        HttpsGetImageTask it = new HttpsGetImageTask(this.mainImageView);
        it.execute(newImageUrl);

    }

    public void downloadBreedList(String url){
        HttpsGetJsonTask gj = new HttpsGetJsonTask(this);
        gj.execute(url);
    }

    public void parseBreedJson(JSONArray jsonArray){
        for(int i = 0; i<jsonArray.length();i++){
            try {
                JSONObject ob = jsonArray.getJSONObject(i);
                String name = ob.getString("name");
                String idString = ob.getString("id");
                breedList.add(new Breed(name,idString,i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.i("andi","BreedArray length: " + breedList.size());
        Log.i("andi","BreedArray first element: \n" + breedList.get(0).toString());

    }




// TESTING methods, remove when finished

    public void testList(){
        if(breeds.isEmpty()) {
            breeds.add("bobcat");
            breeds.add("abbessian");
            breeds.add("lynx");
        }
    }

    public void testMap(){
        if(breedMap.isEmpty()){
            breedMap.put("abys","Abyssinian");
            breedMap.put("chee","Cheetoh");
            breedMap.put("java","Javanese");
        }
    }

   // @Override
    public void onResult(Bitmap bitmap) {
        mainImageView.setImageBitmap(bitmap);
        mainImageView.refreshDrawableState();
    }
}
