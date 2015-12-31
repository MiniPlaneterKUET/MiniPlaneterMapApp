package mini.planeter.miniplanetermap;

/**
 * Created by Manash on 12/29/2015.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlacesManager {

    private final String TAG = "PlacesManager";
    public final static char COMMA = ',';
    public final static char SEMICOLON = ';';

    //Map for location with corresponding LatLng values
    private Map <String, String> locationMap;

    //Map for destination and current position
    protected class Destination{
        private String place;
        private String location;
        private LatLng locationLatLng;

        public Destination(String place, String location){
            this.place = place;
            this.location = location;
            this.locationLatLng = new LatLng(getLatitude(), getLongitude());
        }

        public String getLocation(){
            return this.location;
        }

        public String getPlace(){
            return this.place;
        }

        public double getLatitude(){
            return Double.parseDouble(PlacesManager.sliceString(getLocation(), COMMA));
        }

        public double getLongitude(){
            int start = getLocation().indexOf(COMMA) + 1;
            int end = getLocation().indexOf(SEMICOLON);
            return Double.parseDouble(getLocation().substring(start, end));
        }

        public LatLng getLatLng(){
            return this.locationLatLng;
        }


        public double getLng(){
            return getLatLng().longitude;
        }

        public double getLat(){
            return getLatLng().latitude;
        }

        public void setDestination(String place, String location){
            this.place = place;
            this.location = location;
        }

        public String getDestination(){
            return this.place + "-" + this.location;
        }
    }

    protected class CustomLocation extends Destination{
        CustomLocation(String place, String location){
            super(place, location);
        }
    }

    private String[] places = {
            "Amar Ekushey Hall Canteen",
            "Amar Ekushey Hall",
            "Central Mosque",
            "Central Library",
            "Department of ME",
            "Janata Bank",
            "Cafeteria",
            "Post Office",
            "Main Gate"
    };


    private String[] locations = {
            "22.8983666,89.5012359;",
            "22.8980479,89.5010186;",
            "22.8993308,89.5002207;",
            "22.899161,89.5018729;",
            "22.9004248,89.5019031;",
            "22.8990535,89.5029035;",
            "22.8984339,89.5031181;",
            "22.89781,89.5035365;",
            "22.8976902,89.5070221;"
    };

    public PlacesManager(){
        locationMap = new HashMap<>();

        for (int i = 0; i < places.length; i++){
            locationMap.put(places[i], locations[i]);
        }

        LatLng des = getLocation(places[2]);
        Log.i(TAG, "The destination is: " + String.valueOf(des));

    }

    public LatLng getLocation(String key){
        CustomLocation customLocation = new CustomLocation(key, locationMap.get(key));
        return customLocation.getLatLng();
    }


    public double getLatitude(String key){
        CustomLocation customLocation = new CustomLocation(key, locationMap.get(key));
        return customLocation.getLatitude();
    }

    public double getLongitude(String key){
        CustomLocation customLocation = new CustomLocation(key, locationMap.get(key));
        return customLocation.getLongitude();
    }

    //Print out the log
    public void printPlaces(){
        Log.i(TAG, "=== Printing the Places ===");
        for (String place : places){
            Log.i(TAG, place);
        }
        Log.i(TAG, "=== Place Printing End ===");
    }

    //Printing the Location with LatLng Coordinate values
    public void printLocations(){
        Log.i(TAG, " === Printing Places with Corresponding Locations START === ");
        for (String key : places){
            Log.i(TAG, "Place: " + key + " -- Location [Lat:Lng] : [" + locationMap.get(key) + "]");
        }
        Log.i(TAG, " === Printing Places with Corresponding Locations END === ");
    }

    public static String sliceString(String input, char upto){
        int index = input.indexOf(upto);
        if (input != null) return input.substring(0, index);
        else return null;
    }

}
