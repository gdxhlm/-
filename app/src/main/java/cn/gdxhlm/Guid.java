package cn.gdxhlm;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Guid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        try {
            Get();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }



    public void Get() throws PackageManager.NameNotFoundException {




        final Handler handler=new Handler(new Handler.Callback() {
            String pName = "cn.gdxhlm";
                PackageManager pm = getPackageManager();
                PackageInfo pinfo = pm.getPackageInfo(pName, PackageManager.GET_CONFIGURATIONS);
                String versionName = pinfo.versionName;
                ;

            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what>(int)(Float.parseFloat(versionName)*100)){
                    AlertDialog.Builder builder=new AlertDialog.Builder(Guid.this);
                    builder.setTitle("更新提醒");
                    builder.setMessage("是否更新");
                    builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.lanzous.com/b482753"));
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.show();

                }
                else {
                    Toast.makeText(Guid.this, "不用升级", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Guid.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader bufferedReader=null;
                try {
                    URL url=new URL("http://www.gdxhlm.cn/BBKZ/KJJL.json");
                    connection= (HttpURLConnection) url.openConnection();
                    InputStreamReader inputStreamReader=new InputStreamReader(connection.getInputStream());
                    bufferedReader=new BufferedReader(inputStreamReader);
                    StringBuilder jsonresult=new StringBuilder();
                    String line;
                    int i=0;
                    while ((line=bufferedReader.readLine())!=null){
                        jsonresult.append(line);
                        i++;
                        Log.d("IZHI", String.valueOf(i));
                    }
                    Log.d("HUJG", String.valueOf(jsonresult));
                   int version=(jsonjx(String.valueOf(jsonresult)))*100;
                    Message message=new Message();
                   message.what=version;
                    handler.sendMessage(message);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                    if(bufferedReader!=null){
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

    }


    public int jsonjx(String data){
        int version=0;
        try {
            JSONArray jsonArray=new JSONArray(data);
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            version=jsonObject.getInt("version");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return version;
    }

}
