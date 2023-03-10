package ma.enset.manipulationdesfichiers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ma.enset.manipulationdesfichiers.Adapter.CustomAdapter;
import ma.enset.manipulationdesfichiers.Model.ContactModel;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{
                        Manifest.permission.CALL_PHONE
                },
                1);
        ArrayList<ContactModel> liste= new ArrayList<>();
        String jsonString="";
        jsonString=LoadJSONFromAsset(getApplicationContext(),"data.json");
         // Gson and its API
        Gson gson = new Gson();
        Type type = new TypeToken<List<ContactModel>>() {}.getType();
        List<ContactModel> contactModels= gson.fromJson(jsonString, type);
        if(contactModels!=null){
            liste.addAll(contactModels);
          }
        liste.forEach((name)-> System.out.println(name));

        // Adaptateur
        CustomAdapter adapter= new CustomAdapter(this,liste);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),"Bonjour ",Toast.LENGTH_LONG).show();
            }
        });
        }
    //Charger le fichier JSON à partir du dossier Assets.
    private String LoadJSONFromAsset(Context context,String filename){
        String json=null;
        try{
            InputStream is=context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            json=new String(buffer,"UTF-8");
        }catch (IOException e)
        {
           e.printStackTrace();
           return  null;
        }
        return json;
    }

}