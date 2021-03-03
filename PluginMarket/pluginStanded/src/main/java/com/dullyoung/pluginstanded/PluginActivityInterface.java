package com.dullyoung.pluginstanded;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

/*
 * Created by　Dullyoung on 2021/3/2
 */
public interface PluginActivityInterface {
    //生命周期的activity

    public void attachPlugin(Activity proxyActivity);


    public void onPluginCreate(Bundle savedInstanceState);

    public void onPluginStart();

    public void onPluginResume();

    public void onPluginPause();

    public void onPluginStop();

    public void onPluginDestroy();

    public void onPluginSaveInstanceState(Bundle outState);

    public boolean onPluginTouchEvent(MotionEvent event);

    public void onPluginBackPressed();
}
