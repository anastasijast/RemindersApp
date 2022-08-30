package anastasijast.example.healthyreminders;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 0;
    private JobScheduler mScheduler;
    public Button newR,addR;
    public ArrayList<String> arr,arrD;
    String i;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent notifyIntent = new Intent(this,MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000*60*60*24, pendingIntent);
        arr = new ArrayList<String>();
        arrD = new ArrayList<String>();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b==null) {
            scheduleJob();
        }
        if(b!=null) {
            String data = (String) b.get("data");
            try {
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<JSONObject> listdata = new ArrayList<JSONObject>();
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        listdata.add((JSONObject) jsonArray.get(i));
                    }
                    for(int i=0; i<listdata.size(); i++) {
                        Log.d("NIZA", String.valueOf(listdata.get(i).get("name")));
                        arr.add(String.valueOf(listdata.get(i).get("name")));
                        arrD.add(String.valueOf(listdata.get(i).get("description")));
                    }
                }
                } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        addR = findViewById(R.id.add_reminder);
        newR = findViewById(R.id.new_reminder);
        addR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddReminder.class);
                i = String.valueOf(arr.size());
                intent.putExtra("index",i);
                startActivity(intent);
            }
        });
        newR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewReminder.class);
                intent.putExtra("niza", arr);
                intent.putExtra("niza1", arrD);
                startActivity(intent);
            }
        });
    }
    private void scheduleJob() {
        Log.i("ANE","se povika scheduleJob()");
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
        ComponentName serviceName = new ComponentName(getPackageName(),
                NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                .setRequiresCharging(true);
        JobInfo myJobInfo = builder.build();
        mScheduler.schedule(myJobInfo);
    }
}