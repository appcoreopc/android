package cam.appcore.com.intentmaster;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

public class NotificationFactory {

    public static NotificationCompat.Builder createNotification(Context ctx, String title, String content)
    {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ctx)
             .setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setContentText(content);

        Intent triggerIntent = new Intent(ctx, MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(ctx);

        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(triggerIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);

        return notificationBuilder;
    }

    public static NotificationCompat.Builder createNotificationLayout(Context ctx, int layoutId, String title, String content)
    {

        RemoteViews remoteView = new RemoteViews(ctx.getPackageName(), layoutId);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setContentText(content).setContent(remoteView);

        Intent triggerIntent = new Intent(ctx, MainActivity.class);
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(ctx);

        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(triggerIntent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);

        remoteView.setOnClickPendingIntent(R.id.btnPlay, IntentFactory.createPendingIntent(ctx, PlayChatActivity.class));
        return notificationBuilder;

    }

    public static int createAndFireNotification(Context ctx, NotificationManager notificationService, String title, String content)
    {
        int notificationId = -1;
        notificationService.notify(notificationId, NotificationFactory.createNotification(ctx,
                title, content).build());
        return notificationId;
    }

}
