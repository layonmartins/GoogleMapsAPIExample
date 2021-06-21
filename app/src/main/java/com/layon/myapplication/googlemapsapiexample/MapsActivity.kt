package com.layon.myapplication.googlemapsapiexample

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.layon.myapplication.googlemapsapiexample.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val DF = LatLng(-15.7751885, -48.3575963) //-15.7751885,-48.3575963

        //add circle
        val circleOptions = CircleOptions()
        circleOptions.center(DF)
        circleOptions.radius(5000.0) //em metros
        circleOptions.strokeWidth(10f)
        circleOptions.strokeColor(Color.GRAY)
        circleOptions.fillColor(Color.argb(128, 255, 153, 0))
        mMap.addCircle(circleOptions)

        //-23.583083,-46.6666227
        mMap.setOnMapClickListener {
            val latitude = it.latitude
            val longitude = it.longitude
            Toast.makeText(this, "Latitude: $latitude longitude: $longitude", Toast.LENGTH_LONG).show()
            mMap.addMarker(MarkerOptions()
                .position(it)
                .title("Local")
                .snippet("Descrição")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconfinder_2526573_transportation_vehicle_icon_64px)))
        }

        //1) -23.586332, -46.658754
        //2) -23.585615, -46.656662
        //3) -23.587158, -46.657037
        //4) -23.587247, -46.658797

        mMap.addMarker(MarkerOptions()
            .position(DF)
            .title("Distrito Federal")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconfinder_2526573_transportation_vehicle_icon_64px)))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DF, 15f)) //Zoom 2.0 - 21
    }
}