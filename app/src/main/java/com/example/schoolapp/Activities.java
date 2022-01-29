package com.example.schoolapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;

public class Activities extends Fragment {

    public Activities() {
        super(R.layout.fragment_activities);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activities, container, false);

        ListView listView = rootView.findViewById(R.id.listView);
        CustomAdapter adapter = null;
        try {
            adapter = new CustomAdapter(
                requireContext(),
                new JSONArray(Helper.getJsonFromAssets(requireContext(), R.raw.activities))
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setAdapter(adapter);

        return rootView;
    }
}