package com.example.schoolapp;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Helper {
    public static String getJsonFromAssets(Context context, int rawResourceId) {
        String jsonString;
        try {
            InputStream is = context.getResources().openRawResource(rawResourceId);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

    public static String getConfig(Context context, String name) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.config);
            Properties properties = new Properties();

            properties.load(is);
            Log.d("HELP", properties.toString());
            Log.d("HELP2", properties.getProperty(name));
            return properties.getProperty(name);

        } catch (Resources.NotFoundException e) {
            Log.d("getConfig", "Unable to find the config file: " + e.getMessage());
        } catch (IOException e) {
            Log.d("getConfig", "Failed to open config file.");
        }
        return null;
    }
}
