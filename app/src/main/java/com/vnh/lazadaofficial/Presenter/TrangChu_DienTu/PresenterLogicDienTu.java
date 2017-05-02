package com.vnh.lazadaofficial.Presenter.TrangChu_DienTu;

import com.vnh.lazadaofficial.Model.OjectClass.DienTu;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.Model.OjectClass.ThuongHieu;
import com.vnh.lazadaofficial.Model.TrangChu_DienTu.ModelDienTu;
import com.vnh.lazadaofficial.View.DienTu.View_DienTu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUYVINH on 19-Sep-16.
 */
public class PresenterLogicDienTu implements IPresenter_DienTu {
    View_DienTu view_dienTu;
    ModelDienTu modelDienTu;

    public PresenterLogicDienTu(View_DienTu view_dienTu) {
        this.view_dienTu = view_dienTu;
        modelDienTu = new ModelDienTu();
    }

    @Override
    public void LayDanhSachThuongHieu() {
        List<DienTu> listDienTu = new ArrayList<>();

        List<ThuongHieu> listThuongHieu = modelDienTu.LayTenThuongHieu("LayTenThuongHieuVaHinhThuongHieu", "TENTHUONGHIEU");
        List<SanPham> listSanPham = modelDienTu.LaySanPhamTop("LayDienThoaiVaMayTinhBang", "DIENTHOAIVAMAYTINHBANG");

        DienTu dienTu = new DienTu();
        dienTu.setListThuongHieu(listThuongHieu);
        dienTu.setListSanPham(listSanPham);
        dienTu.setTennoibat("Các Thương Hiệu Lớn");
        dienTu.setTenTopnoibat("Top Điện Thoại và Máy Tính Bảng");
        dienTu.setThuongHieu(true);
        listDienTu.add(dienTu);

        List<ThuongHieu> listPhuKien = modelDienTu.LayTenThuongHieu("LayDanhSachPhuKien", "DANHSACHPHUKIEN");
        List<SanPham> listSanPhamPhuKienTop = modelDienTu.LaySanPhamTop("LayDanhSachTopPhuKien", "TOPPHUKIEN");

        DienTu PhuKien = new DienTu();
        PhuKien.setListThuongHieu(listPhuKien);
        PhuKien.setListSanPham(listSanPhamPhuKienTop);
        PhuKien.setTennoibat("Danh Sách Phụ Kiện");
        PhuKien.setTenTopnoibat("Top Phụ Kiện");
        PhuKien.setThuongHieu(false);
        listDienTu.add(PhuKien);

        List<ThuongHieu> listTienIch = modelDienTu.LayTenThuongHieu("LayDanhSachTienIch", "DANHSACHTIENICH");
        List<SanPham> listSanPhamTienIchTop = modelDienTu.LaySanPhamTop("LayTopTienIch", "TOPTIENICH");

        DienTu TienIch = new DienTu();
        TienIch.setListThuongHieu(listTienIch);
        TienIch.setListSanPham(listSanPhamTienIchTop);
        TienIch.setTennoibat("Danh Sách Tiện Ích");
        TienIch.setTenTopnoibat("Top Sản Phẩm Tiện Ích");
        TienIch.setThuongHieu(false);
        listDienTu.add(TienIch);

        if (listThuongHieu.size() > 0) {
            view_dienTu.HienThiDanhSachThuongHieu(listDienTu);
        }
    }

    @Override
    public void LayLogoThuongHieu() {
        List<ThuongHieu> listThuongHieu = modelDienTu.LayTenThuongHieu("LayTopCacThuongHieu","TOPTHUONGHIEU");
        if (listThuongHieu.size() > 0){
            view_dienTu.HienThiLogoThuongHieu(listThuongHieu);
        }
    }

    @Override
    public void LayDanhSachSanPhamMoi() {
        List<SanPham> listSanPham  = modelDienTu.LaySanPhamTop("LayDanhSachHangMoiVe","DANHSACHSANPHAMMOIVE");
        if (listSanPham.size() > 0){
            view_dienTu.HienThiSanPhamMoiVe(listSanPham);
        }
    }
}
