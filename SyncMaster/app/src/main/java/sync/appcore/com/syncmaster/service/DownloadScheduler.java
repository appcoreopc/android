package sync.appcore.com.syncmaster.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;


public class DownloadScheduler extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("Sceduler", "@ will start " + new Date().toString());
        AlarmManager alarmService = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Log.v("Sceduler", " registering alarm manager services " + new Date().toString());

        Intent targetIntent = new Intent(context, DownloadKickStarter.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, targetIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 30);

        alarmService.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                cal.getTimeInMillis(), 1000 * 30, pendingIntent);

        Log.v("Sceduler", "schedule download kick started " + new Date().toString());
    }
}
