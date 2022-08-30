package anastasijast.example.healthyreminders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class NewReminder extends AppCompatActivity {
    public Button btn;
    private static final int NOTIFICATION_ID = 0;
    public Spinner spinner;
    public ArrayList<String> array, arrayD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        array = getIntent().getStringArrayListExtra("niza");
        arrayD = getIntent().getStringArrayListExtra("niza1");
        btn = findViewById(R.id.send);
        spinner = findViewById(R.id.all_reminders);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                array);
        spinner.setAdapter(spinnerArrayAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = String.valueOf(spinner.getSelectedItem());
                int i = array.indexOf(selected);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(getApplicationContext())
                        .setContentTitle(selected)
                        .setContentText(arrayD.get(i))
                        .setSmallIcon(R.drawable.ic_baseline_health_and_safety_24)
                        .setOngoing(true);
                Notification mNotification = builder.build();
                mNotificationManager.notify(NOTIFICATION_ID, mNotification);
            }
        });
    }
}