package com.example.schoolapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class Activities extends Fragment {

    String[] values = {"Film 18:30", "Rondleiding 19:00", "Workshop 20:00"};

    public Activities() {
        super(R.layout.fragment_activities);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activities, container, false);

        ListView listView = rootView.findViewById(R.id.listView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            getActivity(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            values
        );
        listView.setAdapter(adapter);

        return rootView;
    }
}