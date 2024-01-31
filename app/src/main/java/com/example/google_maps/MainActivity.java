package com.example.google_maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    GoogleMap mapa;
    int contador;
    ArrayList<LatLng> puntos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        contador=0;
        puntos =new ArrayList<LatLng>();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;

        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate camUpd1 = CameraUpdateFactory
               .newLatLngZoom(new LatLng(-1.0127272376147443, -79.46956271012085), 15);
        mapa.moveCamera(camUpd1);
        mapa.setOnMapClickListener(this);


    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
       // Projection proj = mapa.getProjection();
        //Point coord = proj.toScreenLocation(latLng);

        TextView lbtLat = findViewById(R.id.txt_latit);
        lbtLat.setText(String.format("%.4f", latLng.latitude));

        TextView lblLong = findViewById(R.id.txt_longi);
        lblLong.setText(String.format("%.4f",latLng.longitude));

        mapa.addMarker(new MarkerOptions().position(latLng)
                .title("Marcador"));

         contador= contador + 1;
        puntos.add(latLng);
        if (contador==4){
            PolylineOptions lineas = new PolylineOptions()
                    .add(puntos.get(0))
                    .add(puntos.get(1))
                    .add(puntos.get(2))
                    .add(puntos.get(3))
                    .add(puntos.get(0));
            lineas.width(8);
            lineas.color(Color.RED);
            mapa.addPolyline(lineas);
            contador=0;
            puntos.clear();
        }

        //Toast.makeText(
          //          MainActivity.this,
            //        "Click\n" +
              //      "Lat: " + latLng.latitude + "\n" +
                //    "Lng: " + latLng.longitude + "\n" +
                  //  "X: " + coord.x + " - Y: " + coord.y,
                    //Toast.LENGTH_SHORT).show();
    }
    public void PintarRecUTEQ(View view){
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0126102537465256, -79.47058724772829)));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0132265550724981, -79.47041844959088)));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0133536079466559, -79.46910599665732)));
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0129269378271246, -79.46933738286815)));


        PolylineOptions lineas = new PolylineOptions()
                .add(new LatLng(-1.0126102537465256, -79.47058724772829))
                .add(new LatLng(-1.0132265550724981, -79.47041844959088))
                .add(new LatLng(-1.0133536079466559, -79.46910599665732))
                .add(new LatLng(-1.0129269378271246, -79.46933738286815))
                .add(new LatLng(-1.0126102537465256, -79.47058724772829));

        lineas.width(8);
        lineas.color(Color.RED);

        mapa.addPolyline(lineas);

    }

}
