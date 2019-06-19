package com.example.serverdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.serverdemo.ComputerServer.ComputeBinder;

public class MainActivity extends AppCompatActivity {

    private EditText etChinese,etMath,etEnglish;
    private TextView tvResult;
    private Button btn;
    private ComputeBinder binder = null ;

   private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (ComputeBinder) service;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动Service
        Intent server = new Intent(this,ComputerServer.class);
        //startService(server);
        bindService(server,conn, Context.BIND_AUTO_CREATE);
        init();
        test();
    }
    //销毁
    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
    //绑定
    private void init() {
        etChinese = findViewById(R.id.etChinese);
        etMath = findViewById(R.id.etMath);
        etEnglish = findViewById(R.id.etEnglish);
        tvResult = findViewById(R.id.tvResult);
        btn = findViewById(R.id.btn);
    }
    //测试与服务连接 实现功能
    private void test(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 try {
                    double chinese = Double.parseDouble(etChinese.getText().toString());
                    double math = Double.parseDouble(etMath.getText().toString());
                    double english = Double.parseDouble(etEnglish.getText().toString());
                    if (binder!=null){
                        double result = binder.calcAvg(chinese,math,english);
                        String getResult = String.valueOf(result);
                        tvResult.setText(getResult);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
