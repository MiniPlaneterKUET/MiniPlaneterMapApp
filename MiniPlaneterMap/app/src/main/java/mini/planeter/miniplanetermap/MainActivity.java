package mini.planeter.miniplanetermap;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //LocationList adapter for auto completion
    private ArrayAdapter <String> locationAdapter;


    //Location AutoCompleteTextView
    private AutoCompleteTextView locationAutoCompleteTextView;

    //Clear Button
    private Button clearButton;

    public static String KEY = "Sample Key";
    public static String VALUE = "Sample Value";

    //Application Title
    final private static String APPLICATION_TITLE = "Planeter Mapper";

    //Log Tag
    final private String TAG = "MainActivity";

    public static String PLACE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearButton = (Button) findViewById(R.id.clearButton);

        //Getting the locations
        String[] locationResource = getResources().getStringArray(R.array.locationList);

        //Setting the location adapter
        locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                locationResource);

        locationAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.locationAutoCompleteTextView);

        locationAutoCompleteTextView.setAdapter(locationAdapter);
        locationAutoCompleteTextView.setThreshold(1);

        locationAutoCompleteTextView.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                String text = locationAutoCompleteTextView.getText().toString();
                Toast.makeText(getApplicationContext(), "Selected Location\n:" + text, Toast.LENGTH_SHORT).show();
                Log.i(TAG, text);
            }
        });

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle(APPLICATION_TITLE);



        Button button;
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!locationAutoCompleteTextView.getText().toString().isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    PLACE = locationAutoCompleteTextView.getText().toString();
                    PlacesManager placesManager = new PlacesManager();
                    intent.putExtra(PLACE, placesManager.getLocation(PLACE));
//                intent.putExtra(KEY, VALUE);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter a Location", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationAutoCompleteTextView.setText(null);
            }
        });

        locationAutoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Current Text: " + locationAutoCompleteTextView.getText().toString().isEmpty());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }
}
