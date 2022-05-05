package com.example.beinformatique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView text1 = findViewById(R.id.titr);
        TextView text2 = findViewById(R.id.details);

        String json = null;
        InputStream jsonFile = null;

        Intent intent = getIntent();
        int position= (int) intent.getExtras().get("position");

        try {
            jsonFile = getAssets().open("note.json");
            int size = jsonFile.available();
            byte[] buffer = new byte[size];
            jsonFile.read(buffer);
            jsonFile.close();
            json = new String(buffer, "UTF-8");
            JSONObject object = new JSONObject(json);

            text1.setVisibility(View.VISIBLE);
            text2.setVisibility(View.VISIBLE);

            JSONArray childArray = object.getJSONArray("distraction");
            for (int i = 0; i < childArray.length(); i++) {
                if (position == i) {
                    text1.setText(childArray.getJSONObject(i).getString("titre"));
                    text2.setText(childArray.getJSONObject(i).getString("details"));
                }
                //Toast.makeText(this,""+ childArray.getJSONObject(i).getString("titre"),
                        //Toast.LENGTH_SHORT).show();
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_secondaire,menu);
        return true;
    }
}