package com.vnh.lazadaofficial.View.ChiTietSanPham;

import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;

import java.util.List;

/**
 * Created by HUYVINH on 27-Oct-16.
 */

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);
    void HienThiSliderChiTietSanPham(String[] linkanh);
    void HienThiDanhSachDanhGia(List<DanhGia> danhGias);
    void ThemSanPhamThanhCong();
    void ThemSanPhamThatBai();
}
