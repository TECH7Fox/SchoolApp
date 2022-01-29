package com.example.schoolapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class CustomAdapter extends BaseAdapter {
    JSONArray array;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, JSONArray array) {
        this.array = array;
        this.inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return array.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.row_item, null);
        try {
            JSONObject activity = array.getJSONObject(i);

            TextView placeView = view.findViewById(R.id.place);
            TextView timeView = view.findViewById(R.id.time);
            TextView activityView = view.findViewById(R.id.activity);
            TextView descriptionView = view.findViewById(R.id.description);

            placeView.setText(activity.getString("place"));
            timeView.setText(activity.getString("time"));
            activityView.setText(activity.getString("activity"));
            descriptionView.setText(activity.getString("description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

}
