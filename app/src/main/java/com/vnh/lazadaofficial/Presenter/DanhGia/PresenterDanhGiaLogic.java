package com.vnh.lazadaofficial.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;

import com.vnh.lazadaofficial.Model.DanhGia.ModelDanhGia;
import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
import com.vnh.lazadaofficial.View.DanhGia.DanhGiaActiviti;
import com.vnh.lazadaofficial.View.DanhGia.ViewDanhGia;

import java.util.List;

/**
 * Created by HUYVINH on 05-Nov-16.
 */

public class PresenterDanhGiaLogic implements IPresenterDanhGia {
    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;


    public PresenterDanhGiaLogic(ViewDanhGia viewDanhGia) {
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }
    public void DanhGiaChiTiet(DanhGia danhGia){
       boolean kiemtra = modelDanhGia.DanhGiaChiTiet(danhGia);
        if (kiemtra){
            viewDanhGia.DanhGiaThanhCong();
        }else
        {
            viewDanhGia.DanhGiaThatBai();
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGias = modelDanhGia.LayDanhSachDanhGia(masp,limit);
        if (danhGias.size() > 0){
            viewDanhGia.HienThiDanhSachNhanXet(danhGias);
        }

    }

}
