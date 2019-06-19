package com.example.serverdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ComputerServer  extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new ComputeBinder();
    }


    public class ComputeBinder extends Binder{
        /**
         * 计算平均值
         * scores 可变数组
         * */
        public double calcAvg(double ...scores){
            int count = scores.length;
            if(count==0){
                return 0;
            }
                double sum = 0;
                for (double s:scores){
                    sum+=s;
                }
                return sum/count;
        }
    }
}
