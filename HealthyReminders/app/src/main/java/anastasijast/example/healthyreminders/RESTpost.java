package anastasijast.example.healthyreminders;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

public class RESTpost extends AsyncTask<Void, Void, Void> {
    private ArrayList<String> arrayList;
    public RESTpost(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("ANE", "se povika doInBackground");
        NetworkUtils.postInfo(arrayList);
        return null;
    }
}
