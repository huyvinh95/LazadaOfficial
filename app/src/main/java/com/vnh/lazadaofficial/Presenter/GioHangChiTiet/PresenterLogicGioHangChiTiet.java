package com.vnh.lazadaofficial.Presenter.GioHangChiTiet;

import android.content.Context;
import android.widget.Toast;

import com.vnh.lazadaofficial.Model.GioHang.ModelGioHang;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.View.GioHang.ViewGioHang;

import java.util.List;

/**
 * Created by HUYVINH on 28-Apr-17.
 */

public class PresenterLogicGioHangChiTiet implements PresenterGioHangChiTiet {

    ViewGioHang viewGioHang;
    ModelGioHang modelGioHang;

    public PresenterLogicGioHangChiTiet(ViewGioHang viewGioHang) {
        this.viewGioHang = viewGioHang;
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayDanhSachSanPham(Context context) {
        modelGioHang.MoKetNoi(context);
        List<SanPham> sanPhams  = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if (sanPhams.size() > 0){
            viewGioHang.HienThiDanhSachGioHang(sanPhams);
        }else {
            Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
        }
    }
}
