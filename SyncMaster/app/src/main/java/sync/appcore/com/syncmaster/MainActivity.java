package sync.appcore.com.syncmaster;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;
import java.util.ServiceConfigurationError;

import sync.appcore.com.syncmaster.service.DownloadService;

public class MainActivity extends AppCompatActivity {

    private final String broadcastIntent = "sync.appcore.com.broadcast";
    DownloadService downloadService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartService = (Button) findViewById(R.id.btnStartService);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceIntent = new Intent(getBaseContext(), DownloadService.class);
                startService(serviceIntent);
            }
        });

        Button btnStartScheduleBroadcaster = (Button) findViewById(R.id.btnStartScheduler);

        btnStartScheduleBroadcaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceIntent = new Intent(broadcastIntent);
                getBaseContext().sendBroadcast(serviceIntent);
            }
        });

        Button btnResponse = (Button) findViewById(R.id.btnResponsiveTest);

        btnResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.v("UI Logging", "@ : " + new Date().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent service = new Intent(this, DownloadService.class);
        bindService(service, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mServiceConnection);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DownloadService.MyBinder serviceBinder = (DownloadService.MyBinder) iBinder;
            downloadService = serviceBinder.getService();
            Toast.makeText(getBaseContext(), "", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            downloadService = null;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
