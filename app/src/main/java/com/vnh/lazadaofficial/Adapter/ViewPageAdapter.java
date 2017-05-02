package com.vnh.lazadaofficial.Adapter;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vnh.lazadaofficial.View.TrangChu.Fragment.FragmentChuongTrinhKM;
import com.vnh.lazadaofficial.View.TrangChu.Fragment.FragmentDienTu;
import com.vnh.lazadaofficial.View.TrangChu.Fragment.FragmentLamDep;
import com.vnh.lazadaofficial.View.TrangChu.Fragment.FragmentMeVaBe;
import com.vnh.lazadaofficial.View.TrangChu.Fragment.FragmentNhaCuaVaDoiSong;
import com.vnh.lazadaofficial.View.TrangChu.Fragment.FragmentNoiBat;
import com.vnh.lazadaofficial.View.TrangChu.Fragment.FragmentTheThaoVaDuLich;
import com.vnh.lazadaofficial.View.TrangChu.Fragment.FragmentThoiTrang;
import com.vnh.lazadaofficial.View.TrangChu.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUYVINH on 29-Aug-16.
 */
public class ViewPageAdapter extends FragmentPagerAdapter {

    List<android.support.v4.app.Fragment> fragmentList = new ArrayList<android.support.v4.app.Fragment>();
    List<String> titlteFragment = new ArrayList<String>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new FragmentNoiBat());
        fragmentList.add(new FragmentChuongTrinhKM());
        fragmentList.add(new FragmentDienTu());
        fragmentList.add(new FragmentMeVaBe());
        fragmentList.add(new FragmentNhaCuaVaDoiSong());
        fragmentList.add(new FragmentLamDep());
        fragmentList.add(new FragmentThoiTrang());
        fragmentList.add(new FragmentTheThaoVaDuLich());
        fragmentList.add(new FragmentThuongHieu());

        titlteFragment.add("Nổi bật");
        titlteFragment.add("Chương trình khuyến mãi");
        titlteFragment.add("Điện tử");
        titlteFragment.add("Mẹ & bé");
        titlteFragment.add("Nhà cửa & đời sống");
        titlteFragment.add("Làm đẹp");
        titlteFragment.add("Thời trang");
        titlteFragment.add("Thể thao & du lịch");
        titlteFragment.add("Thương hiệu");

    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlteFragment.get(position);
    }
}
