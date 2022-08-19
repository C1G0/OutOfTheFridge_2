package com.wandika.outofthefridge_2;

import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Inisiasi Variable
    Button btn_gambar, btn_cari, btn_txt, btn_DR_test;
    RecyclerView recyclerView_recipe;
    ImageView imageView;
    EditText editText;
    TextView textView, test, txt_label, txt_calories, txt_time;
    URL r;
    Uri u;
    String label, calories, time, id, url, uri;
    Adapter adapter;

    List<Recipe> recipeList;

    public static String encode(String url)
    {
        try {
            String encodeURL= URLEncoder.encode( url, "UTF-8" );
            return encodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" +e.getMessage();
        }
    }

    public static String extractIdFromUri(String uri)
    {
        int start = uri.indexOf('_')+1;
        String id = uri.substring(start);

        return id;
    }

//    public static String extractIdFromUri(String uri)
//    {
//        return uri.split("#recipe_").pop();
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Memasukkan Nilai Variable
//        btn_gambar = findViewById(R.id.btn_gambar);
//        btn_cari = findViewById(R.id.btn_cari);
        btn_txt = findViewById(R.id.btn_text);
//        recyclerView_bahan = findViewById(R.id.recyclerview_bahan);
//        imageView = findViewById(R.id.img_bahan);
        editText = findViewById(R.id.edit_bahan);
//        btn_DR_test = findViewById(R.id.detail_recipe_boong);
//        test = findViewById(R.id.test);
        btn_cari = findViewById(R.id.it_detail_recipe);

        recyclerView_recipe = findViewById(R.id.recyclerview_recipe);
        recipeList = new ArrayList<>();

        btn_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                String s = editText.getText().toString();
                //arrayListBahan.add(s);
                //textView.setText("Bahan: " + s);

//                Intent intent = new Intent(getApplicationContext(), ApiRequest.class);
//                intent.putExtra("message_key", s);
//                startActivity(intent);

                String r = encode(s);
//                test.setText(r);

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://api.edamam.com/api/recipes/v2?type=public&q="+r+"&app_id=90b80eb0&app_key=91a5ff9be23f2cab35ad8c1aa4de2e6c";

                //Request Ke API
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse (JSONObject response) {
//                        String label = "", calories = "", time="";
//
//                        JSONObject data;

                        for (int i = 0; i < response.length(); i++) {

                            try {
                                JSONArray hits = response.getJSONArray("hits");
                                //test.setText("Response: " +hits.toString());
                                //test.setText("Response: "+hits.length());
                                JSONObject obj = hits.getJSONObject(i);
                                //test.setText("Response: " +obj.toString());
                                JSONObject recipe = obj.getJSONObject("recipe");
                                //test.setText("Response: " + recipe.toString());

                                Recipe recipe1 = new Recipe();
                                recipe1.setId(extractIdFromUri(recipe.getString("uri")).toString());
                                recipe1.setUrl(recipe.getString("url"));
                                recipe1.setLabel(recipe.getString("label").toString());
                                recipe1.setCalories(recipe.getString("calories").toString());
                                recipe1.setTime(recipe.getString("totalTime").toString());
                                recipe1.setImgUrl(recipe.getString("image"));

                                recipeList.add(recipe1);

//                                Dibalikin id paling terakhir
//                                test.setText("Response: " + recipe1.getId());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        recyclerView_recipe.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new Adapter(getApplicationContext(), recipeList);
                        recyclerView_recipe.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.d("tag","onErrorResponse: " + error.getMessage());
                    }
                });

                queue.add(request);

                }

        });

//        btn_cari.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //String s = editText.getText().toString();
//                //arrayListBahan.add(s);
//                //textView.setText("Bahan: " + s);
//
////                Bundle bundle = new Bundle();
////                Intent intent = new Intent(getApplicationContext(), DetailRecipe.class);
////                bundle.putParcelableArrayList("message_key", recipeList);
////                intent.putExtra(bundle);
//
//
//
////                startActivity(intent);
//
//            }
//        });

    }
}