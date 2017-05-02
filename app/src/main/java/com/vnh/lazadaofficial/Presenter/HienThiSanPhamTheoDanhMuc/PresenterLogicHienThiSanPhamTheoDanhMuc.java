package com.vnh.lazadaofficial.Presenter.HienThiSanPhamTheoDanhMuc;

import com.vnh.lazadaofficial.Model.HienThiSanPhamTheoDanhMuc.ModelHienThiSanPhamTheoDanhMuc;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUYVINH on 27-Sep-16.
 */

public class PresenterLogicHienThiSanPhamTheoDanhMuc implements IPresenterHienThiSanPhamTheoDanhMuc {
    ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc;
    ModelHienThiSanPhamTheoDanhMuc modelHienThiSanPhamTheoDanhMuc;

    public PresenterLogicHienThiSanPhamTheoDanhMuc(ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc) {

        this.viewHienThiSanPhamTheoDanhMuc = viewHienThiSanPhamTheoDanhMuc;
        modelHienThiSanPhamTheoDanhMuc = new ModelHienThiSanPhamTheoDanhMuc();
    }

    @Override
    public void LayDanhMucSanPham(int masp,boolean kiemtra) {
        List<SanPham> listSanPham = new ArrayList<>();
        if (kiemtra){
            listSanPham = modelHienThiSanPhamTheoDanhMuc.LaySanPhamTheoMaLoai(masp,"LayDanhSachSanPhamTheoMaThuongHieu","DANHSACHSANPHAM",0);
         } else {
            listSanPham = modelHienThiSanPhamTheoDanhMuc.LaySanPhamTheoMaLoai(masp,"LayDanhSachSanPhamTheoMaLoaiDanhMuc","DANHSACHSANPHAM",0);
        }

        if (listSanPham.size()>0){
            viewHienThiSanPhamTheoDanhMuc.HienThiThanhCong(listSanPham);
        } else {
            viewHienThiSanPhamTheoDanhMuc.HienThiLoi();
        }

    }
    public List<SanPham> LayDanhMucSanPhamLoadMore(int masp,boolean kiemtra, int limit){
        List<SanPham> listSanPham = new ArrayList<>();
        if (kiemtra){
            listSanPham = modelHienThiSanPhamTheoDanhMuc.LaySanPhamTheoMaLoai(masp,"LayDanhSachSanPhamTheoMaThuongHieu","DANHSACHSANPHAM",limit);
        } else {
            listSanPham = modelHienThiSanPhamTheoDanhMuc.LaySanPhamTheoMaLoai(masp,"LayDanhSachSanPhamTheoMaLoaiDanhMuc","DANHSACHSANPHAM",limit);
        }

        return listSanPham;
    }
}
