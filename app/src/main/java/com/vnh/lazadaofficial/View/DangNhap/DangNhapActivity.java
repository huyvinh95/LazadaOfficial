package com.vnh.lazadaofficial.View.DangNhap;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.vnh.lazadaofficial.Adapter.ViewPagerDangNhapAdapter;
import com.vnh.lazadaofficial.R;

public class DangNhapActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabsLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbars);

        setSupportActionBar(toolbar);

        ViewPagerDangNhapAdapter viewPagerDangNhapAdapter = new ViewPagerDangNhapAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerDangNhapAdapter);
        viewPagerDangNhapAdapter.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }
}
