package com.example.materialtest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);/*获取toolbar实例*/
        setSupportActionBar(toolbar);/*传入toolbar实例*/

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);/*获取drawer_layout实例*/
        ActionBar actionBar = getSupportActionBar();/*获取当前actionBar实例*/
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);/*导航按钮置为true*/
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);/*重写导航按钮样式，修改图标*/
        }

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);/*获取NavigationView实例*/
        navView.setCheckedItem(R.id.nav_call);/*设置默认选中call菜单*/
        /*设置菜单项选中事件的监听器*/
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {/*选中任意菜单，回调该方法*/
                mDrawerLayout.closeDrawers();/*关闭滑动菜单*/
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.backup) {
            Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.settings) {
            Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.delete) {
            Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == android.R.id.home) {/*重写导航按钮点击事件*/
            mDrawerLayout.openDrawer(GravityCompat.START);/*1.显示导航菜单；2.显示位置根据系统语言调整*/
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);/*加载自定义toolbar布局*/
        return true;
    }
}