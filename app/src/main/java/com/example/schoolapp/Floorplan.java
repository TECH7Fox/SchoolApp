package com.example.schoolapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;

public class Floorplan extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap mMap;
    private View view;
    private GroundOverlayOptions mOverlayOptions;

    public Floorplan() {
        super(R.layout.fragment_floorplan);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_floorplan, container, false);
        MapsInitializer.initialize(getContext());

        this.mapView = (MapView) view.findViewById(R.id.map);
        this.mapView.onCreate(savedInstanceState);
        this.mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(0, 0);

        mOverlayOptions = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.floorplan))
                .position(latLng, 200, 150);
        mMap.addGroundOverlay(mOverlayOptions);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));
        mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(-0.0005, -0.0005), new LatLng(0.0005, 0.0005)));
        mMap.setMinZoomPreference(18);
        mMap.setMaxZoomPreference(21);

        JSONArray array = null;
        try {
            array = new JSONArray(getJsonFromAssets(getContext()));
            for (int i = 0; i < array.length(); i++) {
                JSONObject marker = array.getJSONObject(i);
                mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(marker.optDouble("lat"), marker.optDouble("lng")))
                    .title(marker.optString("title"))
                    .snippet(marker.optString("snippet"))
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("JSON", getJsonFromAssets(getContext()));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 19));
    }

    static String getJsonFromAssets(Context context) {
        String jsonString;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.markers);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
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