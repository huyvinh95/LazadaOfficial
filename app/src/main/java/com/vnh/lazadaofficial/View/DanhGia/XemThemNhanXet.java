package com.vnh.lazadaofficial.View.DanhGia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.vnh.lazadaofficial.Adapter.AdapterDanhGia;
import com.vnh.lazadaofficial.Model.HienThiSanPhamTheoDanhMuc.ILoadMore;
import com.vnh.lazadaofficial.Model.HienThiSanPhamTheoDanhMuc.ILoadMoreScroll;
import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
import com.vnh.lazadaofficial.Presenter.DanhGia.PresenterDanhGiaLogic;
import com.vnh.lazadaofficial.R;

import java.util.ArrayList;
import java.util.List;

public class XemThemNhanXet extends AppCompatActivity implements ViewDanhGia,ILoadMore {
    RecyclerView rvXemThemNhanXet;
    ProgressBar pbLoadNhanXet;
    PresenterDanhGiaLogic presenterDanhGiaLogic;
    List<DanhGia> laytongdanhgia;
    int masp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_them_nhan_xet);
        rvXemThemNhanXet  = (RecyclerView) findViewById(R.id.rvXemThemNhanXet);
        pbLoadNhanXet = (ProgressBar) findViewById(R.id.pgLoadDanhGia);
        laytongdanhgia = new ArrayList<>();
        presenterDanhGiaLogic = new PresenterDanhGiaLogic(this);
        presenterDanhGiaLogic.LayDanhSachDanhGiaCuaSanPham(masp,10,pbLoadNhanXet);
        masp  = getIntent().getIntExtra("masp",0);
    }

    @Override
    public void LoadMore(int tongitem) {
    presenterDanhGiaLogic.LayDanhSachDanhGiaCuaSanPham(masp,tongitem,pbLoadNhanXet);
    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachNhanXet(List<DanhGia> danhGias) {
        laytongdanhgia.addAll(danhGias);
        AdapterDanhGia  adapterDanhGia = new AdapterDanhGia(this,laytongdanhgia,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvXemThemNhanXet.setLayoutManager(layoutManager);
        rvXemThemNhanXet.setAdapter(adapterDanhGia);
        rvXemThemNhanXet.addOnScrollListener(new ILoadMoreScroll(layoutManager,this));
        adapterDanhGia.notifyDataSetChanged();

    }
}
