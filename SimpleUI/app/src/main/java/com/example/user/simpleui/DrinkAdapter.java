package com.example.user.simpleui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/7/14.
 */
public class DrinkAdapter extends BaseAdapter {

        LayoutInflater inflater;
        List<Drink> drinkList = new ArrayList<>();

        public DrinkAdapter(Context context, List<Drink> drinks)
        {
            drinkList = drinks;
            inflater = LayoutInflater.from(context);
        }

    @Override
    public int getCount() {
        return drinkList.size();
    }

    @Override
    public Object getItem(int position) {
        return drinkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_drink_item, null);
            holder = new Holder();
            holder.drinkName = (TextView)convertView.findViewById(R.id.drinkNameTextView);
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            holder.mPriceTextView = (TextView)convertView.findViewById(R.id.mPrice);
            holder.lPriceTextView = (TextView)convertView.findViewById(R.id.lPrice);
            convertView.setTag(holder);

        }
        else {
            holder = (Holder)convertView.getTag();
        }
        Drink drink = drinkList.get(position);
        holder.drinkName.setText(drink.name);
        holder.mPriceTextView.setText(String.valueOf(drink.mPrice));
        holder.lPriceTextView.setText(String.valueOf(drink.lPrice));
        holder.imageView.setImageResource(drink.imageId);
        return convertView;
    }

    class Holder{
        ImageView imageView;
        TextView mPriceTextView;
        TextView lPriceTextView;
        TextView drinkName;
    }
}
