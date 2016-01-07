package sync.appcore.com.syncmaster.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

public class DownloadService extends Service {

    private final IBinder mBinder = new MyBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("Service Onstart", "executing....");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Download Service", "service started");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v("Download Service", "Task to execute at:" + new Date().toString());
        return mBinder;
    }

    public class MyBinder extends Binder {
        public DownloadService getService() {
         return DownloadService.this;
        }
    }
}
