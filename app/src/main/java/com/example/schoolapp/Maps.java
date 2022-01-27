package com.example.schoolapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap mMap;
    private View view;

    public Maps() {
        super(R.layout.fragment_maps);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_maps, container, false);
//        mapView = (MapView) getView().findViewById(R.id.map);
//        mapView.getMapAsync(this);//when you already implement OnMapReadyCallback in your fragment
//        return view;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_maps, container, false);
        MapsInitializer.initialize(getContext());

        this.mapView = (MapView) view.findViewById(R.id.map);
        this.mapView.onCreate(savedInstanceState);
        this.mapView.getMapAsync(this);
        // initialize other UI elements.
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(52.6476692958648, 5.051300404252521);
        //mMap.addMarker(new MarkerOptions().position(latLng).title("Horizon College"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f));
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}