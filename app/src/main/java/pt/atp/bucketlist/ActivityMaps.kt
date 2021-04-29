package pt.atp.bucketlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pt.atp.bucketlist.model.Marker

class ActivityMaps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val placesVisitedList = listOf(
            Marker("Venice", LatLng(45.4,12.3)),
            Marker("Auschwitz", LatLng(50.0,19.1)),
            Marker("Zakopane", LatLng(49.3,19.9)),
            Marker("Berlin", LatLng(52.3,13.2)),
            Marker("Salzburg", LatLng(47.8,13.0)),
            Marker("Munich", LatLng(48.1,11.6)),
            Marker("Dresden", LatLng(51.3,13.4)),
            Marker("Prague", LatLng(50.0,14.3)),
            Marker("Budapest", LatLng(47.3,19.2)),
            Marker("Warsaw", LatLng(52.1,21.1)),
            Marker("Oslo", LatLng(59.5,10.5)),
            Marker("Gdansk", LatLng(54.4,18.6)),
            Marker("Poznan", LatLng(52.4,16.9)),
            Marker("Krakow", LatLng(50.4,19.6)),
            Marker("Wroclaw", LatLng(51.6,17.2)),
            Marker("Leipzig", LatLng(51.2,12.2)),
            Marker("Utrecht", LatLng(52.5,5.6)),
            Marker("Amsterdam", LatLng(52.3,4.9)),
            Marker("Rotterdam", LatLng(51.9,4.5)),
            Marker("Gent", LatLng(51.1,3.7)),
            Marker("Brugge", LatLng(51.2,3.2)),
            Marker("Brussels", LatLng(50.9,4.3)),
            Marker("Antwerp", LatLng(51.3,4.4)),
            Marker("Tropea", LatLng(38.7,15.9)),
            Marker("Chicago", LatLng(41.9,-87.6)),
            Marker("Springfield IL", LatLng(39.5,-89.4)),
            Marker("St. Louis", LatLng(38.6,-90.2)),
            Marker("Springfield MO", LatLng(37.1,-93.2)),
            Marker("Tulsa", LatLng(36.7,-95.6)),
            Marker("Oklahoma", LatLng(36.1,-96.9)),
            Marker("Amarillo", LatLng(35.2,-101.8)),
            Marker("Santa Fe", LatLng(35.4,-105.6)),
            Marker("Albuquerque", LatLng(35.6,-106.4)),
            Marker("Gallup", LatLng(35.3,-108.7)),
            Marker("Flagstaff", LatLng(35.1,-111.4)),
            Marker("Grand Canyon", LatLng(36.2,-112.4)),
            Marker("Kingman", LatLng(35.2,-114.0)),
            Marker("Las Vegas", LatLng(36.1,-115.2)),
            Marker("Needles", LatLng(34.8,-114.6)),
            Marker("Los Angeles", LatLng(34.1,-118.2)),
            Marker("Geneva", LatLng(46.1,6.8)),
            Marker("Chamonix", LatLng(45.9,6.9)),
            Marker("Lausanne", LatLng(46.5,6.6)),
            Marker("Berna", LatLng(46.6,7.3)),
            Marker("Paris", LatLng(48.9,2.3)),
            Marker("London", LatLng(51.5,-0.1)),
        )

        val placesToVisitList = listOf(
            Marker("Ireland", LatLng(53.4,-8.3)),
            Marker("Scotland", LatLng(56.0,-4.0)),
            Marker("Patagonia", LatLng(-46.7,-74.6)),
            Marker("Norway", LatLng(60.47,8.5))
        )

        for(marker in placesVisitedList){
            mMap.addMarker(MarkerOptions().position(marker.location).title(marker.name))
        }

        for(marker in placesToVisitList){
            mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(200F)).position(marker.location).title(marker.name))
        }
    }
}