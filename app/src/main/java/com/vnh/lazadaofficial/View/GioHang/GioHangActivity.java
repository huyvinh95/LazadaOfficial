package com.vnh.lazadaofficial.View.GioHang;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vnh.lazadaofficial.Adapter.AdapterChiTietGioHang;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.Presenter.GioHangChiTiet.PresenterLogicGioHangChiTiet;
import com.vnh.lazadaofficial.R;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang {

    RecyclerView recyclerView;
    PresenterLogicGioHangChiTiet presenterLogicGioHangChiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_Giohang);
        presenterLogicGioHangChiTiet = new PresenterLogicGioHangChiTiet(this);
        presenterLogicGioHangChiTiet.LayDanhSachSanPham(this);
    }


    @Override
    public void HienThiDanhSachGioHang(List<SanPham> sanPhamList) {
        AdapterChiTietGioHang adapterChiTietGioHang = new AdapterChiTietGioHang(this, sanPhamList);
        recyclerView.setAdapter(adapterChiTietGioHang);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }
}
