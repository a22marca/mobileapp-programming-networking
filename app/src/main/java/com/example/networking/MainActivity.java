package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";
    private ArrayList<Mountain> mountains;
    private RecyclerViewAdapter recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonTask(this).execute(JSON_URL);

        mountains = new ArrayList<>();

        recycleViewAdapter = new RecyclerViewAdapter(this, mountains, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Mountain mountain) {
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(recycleViewAdapter);
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObj;
                String name;
                jsonObj = (JSONObject) jsonArray.get(i);
                name = jsonObj.getString("name");
                Log.d("onPost", name);
                mountains.add(new Mountain(name));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        recycleViewAdapter.notifyDataSetChanged();

    }

}
