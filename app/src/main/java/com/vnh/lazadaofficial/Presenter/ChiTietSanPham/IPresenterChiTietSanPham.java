package com.vnh.lazadaofficial.Presenter.ChiTietSanPham;

import android.content.Context;

import com.vnh.lazadaofficial.Model.OjectClass.SanPham;

/**
 * Created by HUYVINH on 27-Oct-16.
 */

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGiaHienThi(int masp, int limit);
    void ThemGioHang(SanPham sanPham, Context context);
}
