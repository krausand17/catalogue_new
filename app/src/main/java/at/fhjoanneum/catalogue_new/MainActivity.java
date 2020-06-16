package at.fhjoanneum.catalogue_new;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.fhjoanneum.catalogue_new.https.HttpsGetImageTask;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private ImageView mainImageView;
    private String api_url = "https://cdn2.thecatapi.com/v1/images/search";
    private String breed_filter = "";
    private List<String> breeds = new ArrayList<>();
    private Map<String,String> breedMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainImageView = (AppCompatImageView) findViewById(R.id.main_image);

        bottomAppBar = findViewById(R.id.bottomBar);
        setSupportActionBar(bottomAppBar);

        testList();
        testMap();

        setMainImage("https://cdn2.thecatapi.com/images/au1.jpg");
        //setMainImage(api_url);
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
        menu.add("Random");
        for(String breed : breedMap.keySet()){
            menu.add(breedMap.get(breed));
        }
        return true;
    }


    public void testList(){
        if(breeds.isEmpty()) {

            breeds.add("bobcat");
            breeds.add("abbessian");
            breeds.add("lynx");
        }
    }

    public void setMainImage(String newImageUrl) {
        HttpsGetImageTask it = new HttpsGetImageTask(this.mainImageView);
        it.execute(newImageUrl);

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
