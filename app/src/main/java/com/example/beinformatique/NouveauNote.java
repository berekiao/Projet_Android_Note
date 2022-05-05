package com.example.beinformatique;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class NouveauNote extends AppCompatActivity {
    EditText saisiTitre;
    EditText saisiContenu;
    Button sauver;
    String FILE_NAME = "new_note.json";
    File file;
    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;
    String rescape = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_note);

        final FileOutputStream[] out = {null};
        final JsonWriter[] f = new JsonWriter[1];
        file = new File(this.getExternalFilesDir("external"), FILE_NAME);
        saisiTitre = findViewById(R.id.titreNote);
        saisiContenu = findViewById(R.id.detailsNote);
        sauver =  findViewById(R.id.sauverNote);

        sauver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    file.createNewFile();
                    out[0] = new FileOutputStream(file, true);
                    f[0] = new JsonWriter(new OutputStreamWriter(out[0]));
                    f[0].beginArray();
                }catch (IOException e){
                    e.printStackTrace();
                }

                try {
                    f[0].beginObject().name("monTitre").value(saisiTitre.getText().toString())
                            .name("monContenu").value(saisiContenu.getText().toString());
                    f[0].endObject();
                    Toast.makeText(NouveauNote.this, "Note créer",Toast.LENGTH_LONG).show();
                }catch (IOException e){
                    e.printStackTrace();
                }

                try {
                    f[0].endArray();
                    f[0].close();
                    Toast.makeText(NouveauNote.this, "Note sauvegarder",Toast.LENGTH_LONG).show();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
