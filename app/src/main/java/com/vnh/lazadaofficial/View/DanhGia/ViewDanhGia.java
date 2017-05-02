package com.vnh.lazadaofficial.View.DanhGia;

import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;

import java.util.List;

/**
 * Created by HUYVINH on 05-Nov-16.
 */

public interface ViewDanhGia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void HienThiDanhSachNhanXet(List<DanhGia> danhGias);
}
