package anastasijast.example.healthyreminders;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class NotificationJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d("ANE", "se povika onStartJob");
        RESTapi resTapi = new RESTapi(this);
        resTapi.execute();
        return true;
    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
