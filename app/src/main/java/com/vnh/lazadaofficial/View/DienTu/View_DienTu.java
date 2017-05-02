package com.vnh.lazadaofficial.View.DienTu;

import com.vnh.lazadaofficial.Model.OjectClass.DienTu;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.Model.OjectClass.ThuongHieu;

import java.util.List;

/**
 * Created by HUYVINH on 19-Sep-16.
 */
public interface View_DienTu {
    void HienThiDanhSachThuongHieu(List<DienTu> listDienTu);
    void HienThiLogoThuongHieu(List<ThuongHieu> listThuongHieu);
    void HienThiSanPhamMoiVe(List<SanPham> listSanPham);
}
