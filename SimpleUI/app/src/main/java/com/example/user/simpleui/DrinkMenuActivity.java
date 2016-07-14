package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DrinkMenuActivity extends AppCompatActivity {

    TextView totalTextView;
    ListView drinkMenuListView;
    String[] names = {"Drink1", "Drink2", "Drink3", "Drink4"};
    int[] mPrices = {25,35,45,35};
    int[] lPrices = {35,45,55,45};
    int[] imageId = {R.drawable.drink1, R.drawable.drink2, R.drawable.drink3, R.drawable.drink4};

    List<Drink> drinks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        Log.d("Debug", "DrinkMenuActivity OnCreate");
        setData();

        totalTextView = (TextView)findViewById(R.id.totalTextView);
        drinkMenuListView = (ListView)findViewById(R.id.drinkMenuListView);
    }
    private void setData()
    {
        for(int i = 0; i < names.length; i ++)
        {
            Drink drink = new Drink();
            drink.name = names[i];
            drink.mPrice = mPrices[i];
            drink.lPrice = lPrices[i];
            drink.imageId = imageId[i];
            drinks.add(drink);  
        }
    }
}
