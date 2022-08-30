package anastasijast.example.healthyreminders;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NetworkUtils {
    private static final String BASE_URL =  "http://10.0.2.2:5000/api/Reminder";

    static String getInfo() {
        Log.d("ANE", "se povika getInfo");
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JSONString = null;

        try {
            Uri builtURI = Uri.parse(BASE_URL).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            JSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSONString;
    }
    static void postInfo(ArrayList<String> arr){
        try {
            Uri builtURI = Uri.parse(BASE_URL).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());
            HttpURLConnection con = (HttpURLConnection)requestURL.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.connect();
            String jsonInputString = "{"+"\"id\": \""+arr.get(0)+"\", \"name\": \""+arr.get(1)+"\", \"description\":\""+arr.get(2)+"\"}";
            Log.d("ANE",jsonInputString);
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            Log.d("ANE",response.toString());
        } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
