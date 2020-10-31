package com.example.rview1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterClass.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "createrNAme";
    public static final String EXTRA_LIKES = "likeCount";

RecyclerView recyclerView;
AdapterClass adapterClass;
ArrayList<model> modelArrayList;
RequestQueue requestQueue;
Button go;
EditText search;
LinearLayout linearLayout;
FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        linearLayout = findViewById(R.id.llayout);

        modelArrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        go = findViewById(R.id.go);
        search = findViewById(R.id.search);
        floatingActionButton = findViewById(R.id.floatingActionButton);


        search.setVisibility(View.GONE);
        go.setVisibility(View.GONE);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation fade_in = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                fade_in.setDuration(1000);
                fade_in.setFillAfter(true);
               search.startAnimation(fade_in);
               go.startAnimation(fade_in);
                search.setVisibility(View.VISIBLE);
                go.setVisibility(View.VISIBLE);
                linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        });



        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                modelArrayList.clear();
                String src = search.getText().toString();
                src = src.replaceAll("\\s", "+");
                String url = "https://pixabay.com/api/?key=14024414-907cf9a6e438fd714715c6773&q="+src+"&image_type=photo&pretty=true";
               try {
                   getData(url);
               }catch (Exception e){
                   Toast.makeText(MainActivity.this, "New EXC", Toast.LENGTH_LONG).show();
               }



                ScaleAnimation fade_in = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                fade_in.setDuration(1000);
                fade_in.setFillAfter(true);
                search.startAnimation(fade_in);
                go.startAnimation(fade_in);

               search.setVisibility(View.GONE);
               go.setVisibility(View.GONE);
                linearLayout.setBackgroundColor(Color.parseColor("#D5D5D5"));
            }
        });









    }

    private void getData(String uri) {
        String url = uri;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);

                                String image = object.getString("webformatURL");
                                int like = object.getInt("likes");
                                String user = object.getString("user");

                                modelArrayList.add(new model(image,user, like));
                            }
                            adapterClass = new AdapterClass(MainActivity.this,modelArrayList);
                            recyclerView.setAdapter(adapterClass);
                            adapterClass.setOnItemClickListener(MainActivity.this);










                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        model clicketitem = modelArrayList.get(position);

        detailIntent.putExtra(EXTRA_URL, clicketitem.getImageURL());
        detailIntent.putExtra(EXTRA_CREATOR, clicketitem.getUsername());
        detailIntent.putExtra(EXTRA_LIKES, clicketitem.getLikes()+"");

        startActivity(detailIntent);

    }
}