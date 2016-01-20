package appcore.com.parsenotificationapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class StarterApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Parse.initialize(this, "QRKbKpKzzj2NPsbsRZcabHQBvKxbN6M4cZ1E2cwR", "ODIDntxKTUy13PM39vBCHETETuZ8lAOL5uGFhbnd");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
