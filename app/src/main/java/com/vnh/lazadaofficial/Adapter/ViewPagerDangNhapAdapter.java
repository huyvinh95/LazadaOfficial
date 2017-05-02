package com.vnh.lazadaofficial.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vnh.lazadaofficial.View.DangNhap.Fragment.FragmentDangKi;
import com.vnh.lazadaofficial.View.DangNhap.Fragment.FragmentDangNhap;

/**
 * Created by HUYVINH on 04-Sep-16.
 */
public class ViewPagerDangNhapAdapter extends FragmentPagerAdapter {
    public ViewPagerDangNhapAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentDangNhap fragmentDangNhap = new FragmentDangNhap();
                return fragmentDangNhap;
            case 1:
                FragmentDangKi fragmentDangKi = new FragmentDangKi();
                return fragmentDangKi;

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Đăng Nhập";
            case 1:
                return "Đăng Kí";

            default:
                return null;
        }
    }
}
