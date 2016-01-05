package cam.appcore.com.intentmaster;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int notficationId = -1;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;

        Button emailBtn =  (Button) findViewById(R.id.btnEmail);

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = IntentFactory.getEmailIntent("kepung@gmail.com", "Test", "Test content");
                startActivity(Intent.createChooser(intent, "Sending email"));
            }
        });

        Button smsBtn = (Button) findViewById(R.id.btnSms);
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = IntentFactory.getSmsIntent();
                startActivity(intent);
            }
        });

        Button contactBtn = (Button) findViewById(R.id.btnContact);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = IntentFactory.getContactsItemIntent(1);
                startActivity(intent);
            }
        });

        Button customBtn = (Button) findViewById(R.id.btnCall);
        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = IntentFactory.getCustomIntent();
                startActivity(Intent.createChooser(intent, "Calling IntentClient"));
            }
        });

        Button btnNotification = (Button) findViewById(R.id.btnCreateNotification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                int id = -1;
                NotificationCompat.Builder notification = NotificationFactory.createNotificationLayout(ctx, R.layout.playchat,
                "main layout title", "content ");

                mNotificationManager.notify(id, notification.build());
            }
        });
    }

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
