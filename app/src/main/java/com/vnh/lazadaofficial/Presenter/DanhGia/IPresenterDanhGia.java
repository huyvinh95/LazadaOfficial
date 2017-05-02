package com.vnh.lazadaofficial.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
import com.vnh.lazadaofficial.View.DanhGia.DanhGiaActiviti;
import com.vnh.lazadaofficial.View.DanhGia.ViewDanhGia;

/**
 * Created by HUYVINH on 05-Nov-16.
 */

public interface IPresenterDanhGia {
    void DanhGiaChiTiet(DanhGia danhGia);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
