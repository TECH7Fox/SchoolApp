package com.example.schoolapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Home extends Fragment {
    public Home() {
        super(R.layout.fragment_home);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView schoolName = view.findViewById(R.id.schoolNameView);
        TextView date = view.findViewById(R.id.dateView);

        schoolName.setText(Helper.getConfig(requireContext(), "school_name"));
        date.setText(Helper.getConfig(requireContext(), "date"));

        return view;
    }
}