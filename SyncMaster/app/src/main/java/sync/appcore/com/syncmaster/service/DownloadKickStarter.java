package sync.appcore.com.syncmaster.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DownloadKickStarter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("DOWNSVC", "kicstart download broadcast service started");
        Intent service = new Intent(context, DownloadService.class);
        context.startService(service);
    }
}
