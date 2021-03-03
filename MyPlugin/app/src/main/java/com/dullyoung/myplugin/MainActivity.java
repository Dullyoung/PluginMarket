package com.dullyoung.myplugin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.dullyoung.pluginstanded.PluginActivityInterface;

import java.lang.reflect.Constructor;


public class MainActivity extends BasePluginActivity  {


    @Override
    public void setContentView(View view) {
        super.setContentView(R.layout.activity_main);
    }

    @Override
    public void attachPlugin(Activity proxyActivity) {
        super.attachPlugin(proxyActivity);
    }

    private String TAG="MainActivity";
    @Override
    public void onPluginCreate(Bundle savedInstanceState) {
        super.onPluginCreate(savedInstanceState);
        Log.i(TAG, "onPluginCreate: ");
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPluginStart() {
        super.onPluginStart();
    }

    @Override
    public void onPluginResume() {
        super.onPluginResume();
    }

    @Override
    public void onPluginPause() {
        super.onPluginPause();
    }

    @Override
    public void onPluginStop() {
        super.onPluginStop();
    }

    @Override
    public void onPluginDestroy() {
        super.onPluginDestroy();
    }

    @Override
    public void onPluginSaveInstanceState(Bundle outState) {
        super.onPluginSaveInstanceState(outState);
    }

    @Override
    public boolean onPluginTouchEvent(MotionEvent event) {
        return super.onPluginTouchEvent(event);
    }

    @Override
    public void onPluginBackPressed() {
        super.onPluginBackPressed();
    }
}