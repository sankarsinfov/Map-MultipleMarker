package com.example.mapapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var arrList = ArrayList<MapValue>()
    var arrList1 = ArrayList<Result>()
    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var gridListAdaptor: ListAdaptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        recyclerView = findViewById(R.id.recycler_view_home)

        var gson = Gson()
        var theater: MapValue = gson.fromJson(Utils.getAssetJsonData(this), MapValue::class.java)
        arrList.add(theater)

        for (i in 0 until arrList.get(0).results.size) {
            arrList1.add(arrList.get(0).results.get(i))
        }

        gridLayoutManager =
            GridLayoutManager(applicationContext, 1, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        gridListAdaptor = ListAdaptor(applicationContext, arrList1!!)
        recyclerView?.adapter = gridListAdaptor

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        
        for (i in 0 until arrList1.size) {
            val place =
                LatLng(arrList1.get(i).geometry.location.lat, arrList1.get(i).geometry.location.lng)
            mMap.addMarker(MarkerOptions().position(place).title("" + arrList1.get(i).name))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
        }

        mMap.maxZoomLevel
    }

}
