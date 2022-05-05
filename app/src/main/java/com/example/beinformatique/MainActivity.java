package com.example.beinformatique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listNote;
    Button plus;
    ListView listeSimple;
    ArrayList<Object> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = null;
        InputStream jsonFile = null;

        listeSimple = findViewById(R.id.maliste);
        list = new ArrayList<Object>();

        try {
            jsonFile = getAssets().open("note.json");
            int size = jsonFile.available();
            byte[] buffer = new byte[size];
            jsonFile.read(buffer);
            jsonFile.close();
            json = new String(buffer, "UTF-8");
            JSONObject object = new JSONObject(json);

            JSONArray childArray = object.getJSONArray("distraction");
            for (int i = 0; i < childArray.length(); i++) {
                list.add(childArray.getJSONObject(i).getString("titre"));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        final ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
                android.R.layout.simple_list_item_1, list);
        listeSimple.setAdapter(adapter);
        listeSimple.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        listeSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fonctionSuivant(position);
            }
        });

        this.plus = (Button) findViewById(R.id.ajoutNote);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent autreActivite = new Intent(getApplicationContext(), NouveauNote.class);
                startActivity(autreActivite);
              //  finish();
            }
        });

    }

    private void fonctionSuivant(int position) {
        Intent intent = null;
        intent = new Intent(getBaseContext(),DetailsActivity.class);
        intent.putExtra("position", position);
        if(intent!=null)
            startActivity(intent);
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }*/
}