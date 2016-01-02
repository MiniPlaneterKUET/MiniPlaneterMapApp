package mini.planeter.miniplanetermap;

/**
 * Created by Manash on 1/2/2016.
 */
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class Speaker implements TextToSpeech.OnInitListener{

    private static final String TAG = "Speaker";
    private TextToSpeech tts;
    private Context context;

    private boolean allowed = false;


    public Speaker(Context context){
        this.context = context;
        tts = new TextToSpeech(this.context, this);
    }

    @Override
    public void onInit(int status){
        if (status == TextToSpeech.SUCCESS) {

            Log.i(TAG, "STATS OK");

            allowed = true;
            tts.setLanguage(Locale.US);
        }
        else allowed = false;
    }

    public boolean isAllowed(){
        return allowed;
    }

    public void speak(CharSequence message){
        tts.speak(message, TextToSpeech.QUEUE_ADD, null, null);
    }

    public void shutdown(){
        tts.shutdown();
    }
}