package com.example.user.simpleui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class DrinkMenuActivity extends AppCompatActivity implements DrinkOrderDialog.OnDrinkOrderListener {

    TextView totalTextView;
    ListView drinkMenuListView;
    String[] names = {"Drink1", "Drink2", "Drink3", "Drink4"};
    int[] mPrices = {25,35,45,35};
    int[] lPrices = {35,45,55,45};
    int[] imageId = {R.drawable.drink1, R.drawable.drink2, R.drawable.drink3, R.drawable.drink4};


    List<Drink> drinks = new ArrayList<>();
    List<DrinkOrder> orders = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        Log.d("Debug", "DrinkMenuActivity OnCreate");
        setData();

        totalTextView = (TextView)findViewById(R.id.totalTextView);
        drinkMenuListView = (ListView)findViewById(R.id.drinkMenuListView);
        setupDrinkMenuListView();
    }
    private void setData()
    {
        for(int i = 0; i < names.length; i++)
        {
            Drink drink = new Drink();
            drink.name = names[i];
            drink.mPrice = mPrices[i];
            drink.lPrice = lPrices[i];
            drink.imageId = imageId[i];
            drinks.add(drink);
        }
    }


    private void setupDrinkMenuListView()
    {
        DrinkAdapter adapter = new DrinkAdapter(this, drinks);
        drinkMenuListView.setAdapter(adapter);

        drinkMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DrinkAdapter drinkAdapter = (DrinkAdapter)parent.getAdapter();
                Drink drink = (Drink)drinkAdapter.getItem(position);
                showDrinkOrderDialog(drink);
            }
        });
    }


    public void showDrinkOrderDialog(Drink drink)
    {
        DrinkOrder drinkOrder = new DrinkOrder(drink);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        DrinkOrderDialog dialog = DrinkOrderDialog.newInstance(drinkOrder);
        Fragment prev = getFragmentManager().findFragmentByTag("DrinkOrderDialog");
        if(prev != null)
        {
            ft.remove(prev);
        }
        //ft.replace(R.id.root, dialog);
        ft.addToBackStack(null);
       // ft.commit();
        dialog.show(ft, "DrinkOrderDialog");
    }
    public void updateTotal()
    {
        int total = 0;
        for(DrinkOrder order: orders)
        {
            total += order.mNumber * order.drink.mPrice + order.lNumber * order.drink.lPrice;
        }

        totalTextView.setText(String.valueOf(total));
    }

    public void done(View view)
    {
        Intent intent = new Intent();

        JSONArray jsonArray = new JSONArray();

        for(DrinkOrder order : orders)
        {
           String data = order.toData();
            jsonArray.put(data);
        }

        intent.putExtra("results", jsonArray.toString());

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "DrinkMenuActivity OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "DrinkMenuActivity OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "DrinkMenuActivity OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "DrinkMenuActivity OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "DrinkMenuActivity OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "DrinkMenuActivity OnRestart");
    }

    @Override
    public void onDrinkOrderFinished(DrinkOrder drinkOrder) {
        Boolean flag = false;
        for(int i = 0; i < orders.size(); i ++)
       {
           if(orders.get(i).drink.name.equals(drinkOrder.drink.name) )
           {
               orders.set(i, drinkOrder);
                flag = true;
               break;
           }
           if(!flag) {
           orders.add(drinkOrder);
           }
           updateTotal();
       }
        orders.add(drinkOrder);

    }
}