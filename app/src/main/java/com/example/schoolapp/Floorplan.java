package com.example.schoolapp;

import android.content.Context;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Floorplan extends Fragment implements OnMapReadyCallback {

    private MapView mapView;

    public Floorplan() {
        super(R.layout.fragment_floorplan);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_floorplan, container, false);
        MapsInitializer.initialize(requireContext());

        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(0, 0);

        googleMap.addGroundOverlay(new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.floorplan))
                .position(latLng, 200, 150));
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style));
        googleMap.setLatLngBoundsForCameraTarget(new LatLngBounds(
            new LatLng(-0.0005, -0.0005),
            new LatLng(0.0005, 0.0005)
        ));
        googleMap.setMinZoomPreference(18);
        googleMap.setMaxZoomPreference(21);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 19));

        JSONArray array;
        try {
            array = new JSONArray(Helper.getJsonFromAssets(requireContext(), R.raw.markers));
            for (int i = 0; i < array.length(); i++) {
                JSONObject marker = array.getJSONObject(i);
                googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(marker.optDouble("lat"), marker.optDouble("lng")))
                    .title(marker.optString("title"))
                    .snippet(marker.optString("snippet"))
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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