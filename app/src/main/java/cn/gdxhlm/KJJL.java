package cn.gdxhlm;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KJJL extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "跳转成功，记录已保存", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences=getSharedPreferences("kjjl",MODE_PRIVATE);
        String timeopen1=sharedPreferences.getString("time","");
        SharedPreferences.Editor editor=getSharedPreferences("kjjl",MODE_PRIVATE).edit();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH mm ss");
        Date date=new Date(System.currentTimeMillis());
        String timeopen=format.format(date);
        editor.putString("time",timeopen1+"\n"+timeopen);
        editor.apply();
        finish();
    }
}
