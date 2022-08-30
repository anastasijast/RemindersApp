package anastasijast.example.healthyreminders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Array;
import java.util.ArrayList;

public class AddReminder extends AppCompatActivity {
    EditText name,desc;
    Button btn;
    String n,d;
    int ind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        btn = findViewById(R.id.btn);
        ind = Integer.parseInt(getIntent().getStringExtra("index"))+1;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n= String.valueOf(name.getText());
                d = String.valueOf(desc.getText());
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(String.valueOf(ind));
                arr.add(n);
                arr.add(d);
                RESTpost post = new RESTpost(arr);
                post.execute();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}