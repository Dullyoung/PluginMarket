package com.dullyoung.pluginmarket;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dullyoung.pluginstanded.PluginActivityInterface;

/*
 * Created by　Dullyoung on 2021/3/2
 */
public class BasePluginActivity extends AppCompatActivity implements PluginActivityInterface {

    public Activity mActivity;
    @Override
    public void attachPlugin(Activity proxyActivity) {
        mActivity=proxyActivity;
    }

    @Override
    public void setContentView(View view) {
        if (mActivity != null) {
            mActivity.setContentView(view);
        } else {
            super.setContentView(view);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        if (mActivity != null) {
            return mActivity.getIntent();
        }
        return super.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mActivity.getClassLoader();
    }


    @Override
    public void setContentView(int layoutResID) {
        mActivity.setContentView(layoutResID);
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return mActivity.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return mActivity.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        return mActivity.getWindow();
    }


    @Override
    public WindowManager getWindowManager() {
        return mActivity.getWindowManager();
    }


    @Override
    public void onPluginCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onPluginStart() {

    }

    @Override
    public void onPluginResume() {

    }

    @Override
    public void onPluginPause() {

    }

    @Override
    public void onPluginStop() {

    }

    @Override
    public void onPluginDestroy() {

    }

    @Override
    public void onPluginSaveInstanceState(Bundle outState) {

    }

    @Override
    public boolean onPluginTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onPluginBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void startActivity(Intent intent) {
//        ProxyActivity --->className
        Intent m = new Intent();
        m.putExtra("ClassName", intent.getComponent().getClassName());
        mActivity.startActivity(m);
    }

}
