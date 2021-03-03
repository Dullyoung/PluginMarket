package com.dullyoung.pluginstanded;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

/*
 * Created by　Dullyoung on 2021/3/2
 */
public interface PluginActivityInterface {
    //生命周期的activity

    public void attach(AppCompatActivity proxyActivity);


    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void onSaveInstanceState(Bundle outState);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackPressed();
}
