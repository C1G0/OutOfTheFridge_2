package com.wandika.outofthefridge_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetailRecipe extends AppCompatActivity {
    TextView test;

    List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_recipe);

        test = findViewById(R.id.test);

//        Bundle bundle = getIntent().getExtras();

//        List<Recipe> recipeList = bundle.getParcelableArrayList("message_key");

//        Intent intent = getIntent();

        //String s = intent.getStringExtra("message_key");

//        List<Recipe> recipeList = getIntent().getSerializableExtra("message_key");

//        test.setText(recipeList);
    }
}
