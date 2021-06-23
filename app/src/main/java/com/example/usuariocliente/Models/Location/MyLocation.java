package com.example.usuariocliente.Models.Location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.widget.Toast;

import com.example.usuariocliente.Cliente.ClienteMenu;
import com.example.usuariocliente.Cliente.Home.ClienteHome;
import com.google.android.gms.location.LocationResult;

public class MyLocation extends BroadcastReceiver {
    public static final String ACTION_PROCESS_UPDATE = "com.example.usuariocliente.UPDATE_LOCATION";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){
            final String action = intent.getAction();
            if(ACTION_PROCESS_UPDATE.equals(action)){
                LocationResult result = LocationResult.extractResult(intent);
                Location location = result.getLastLocation();
                try{
                    ClienteMenu.getInstance().updatecurrentLocation(location);
                } catch (Exception e){
                    Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}