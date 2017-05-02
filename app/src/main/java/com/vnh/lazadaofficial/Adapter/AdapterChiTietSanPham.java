package com.vnh.lazadaofficial.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by HUYVINH on 27-Oct-16.
 */

public class AdapterChiTietSanPham extends FragmentStatePagerAdapter {

    List<Fragment> fragments;
    public AdapterChiTietSanPham(FragmentManager fm, List<Fragment> fragmentslist) {
        super(fm);
        fragments = fragmentslist;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
