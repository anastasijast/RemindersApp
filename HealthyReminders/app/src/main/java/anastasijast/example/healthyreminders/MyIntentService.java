package anastasijast.example.healthyreminders;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;

    public MyIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("Daily Reminder")
                .setContentText("Set Your Healthy Reminders")
                .setSmallIcon(R.drawable.ic_baseline_health_and_safety_24)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}
