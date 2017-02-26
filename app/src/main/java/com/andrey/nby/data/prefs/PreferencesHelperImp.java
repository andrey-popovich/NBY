package com.andrey.nby.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;

import com.andrey.nby.App;
import com.andrey.nby.R;
import com.andrey.nby.di.component.ApplicationComponent;

public class PreferencesHelperImp implements PreferencesHelper {

    private static final long VIBRATION_DURATION = 30;
    private static final String TAG = "PreferencesHelperImp";

    private SharedPreferences settings;
    private Vibrator vibrator;
    private Context context = App.getContext();

    public PreferencesHelperImp(ApplicationComponent component) {
        component.inject(this);
        settings = PreferenceManager.getDefaultSharedPreferences(context);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void playSoundAndVibration(){
        playSound();
        vibrate();
    }

    public void playSound(){
        if (settings.getBoolean("soundsOn", true)){
            MediaPlayer mp = MediaPlayer.create(context, R.raw.def_sound);
            mp.start();
        }
    }

    public void vibrate(){
        if (settings.getBoolean("vibrationOn", true)) {
            vibrator.vibrate(VIBRATION_DURATION);
        }
    }

    @Override
    public String getSortString() {
        String st = settings.getString("sortList", "nr1");
        Log.i(TAG, "nr1 " + st);
        switch (st) {
            case "1":
                return "rate";
            case "2":
                return "cc";
            default:
                return "";
        }
    }
}
