package com.example.materialtest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private Fruit[] fruits = {
            new Fruit("Apple", R.drawable.apple), new Fruit("Banana",R.drawable.banana),
            new Fruit("Orange", R.drawable.orange), new Fruit("Watermelon", R.drawable.watermelon),
            new Fruit("Pear", R.drawable.pear), new Fruit("Grape", R.drawable.grape),
            new Fruit("Pineapple", R.drawable.pineapple), new Fruit("Strawberry",R.drawable.strawberry),
            new Fruit("Cherry", R.drawable.cherry), new Fruit("Mango", R.drawable.mango)};


    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);/*获取FloatingActionButton悬浮按钮实例*/
        fab.setOnClickListener(new View.OnClickListener() {/*设置监听事件，点击响应逻辑....*/
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "FAB clicked", Toast.LENGTH_SHORT).show();
                /*高级提示框Smackbar，拥有可交互功能*/
                Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Data restored",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);//两列数据
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
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