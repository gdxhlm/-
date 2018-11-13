package cn.gdxhlm;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1=findViewById(R.id.tv_1);
        SharedPreferences sharedPreferences=getSharedPreferences("kjjl",MODE_PRIVATE);
        String timeopen1=sharedPreferences.getString("time","");
        textView1.setText(timeopen1);

    }
}
