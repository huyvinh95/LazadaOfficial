package com.vnh.lazadaofficial.Presenter.ChiTietSanPham;

import android.content.Context;

import com.vnh.lazadaofficial.Model.ChiTietSanPham.MoDelChiTietSanPham;
import com.vnh.lazadaofficial.Model.GioHang.ModelGioHang;
import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
import com.vnh.lazadaofficial.Model.OjectClass.SanPham;
import com.vnh.lazadaofficial.Presenter.HienThiSanPhamTheoDanhMuc.IPresenterHienThiSanPhamTheoDanhMuc;
import com.vnh.lazadaofficial.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

/**
 * Created by HUYVINH on 27-Oct-16.
 */

public class PresenterChiTietSanPham implements IPresenterChiTietSanPham {
    ViewChiTietSanPham viewChiTietSanPham;
    MoDelChiTietSanPham moDelChiTietSanPham;
    ModelGioHang modelGioHang;

    public PresenterChiTietSanPham(){
        modelGioHang = new ModelGioHang();
    }

    public PresenterChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        moDelChiTietSanPham = new MoDelChiTietSanPham();
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = moDelChiTietSanPham.LayChiTietSanPham("LaySanPhamVaChiTietSanPham","DANHSACHSANPHAM",masp);

        if (sanPham.getMASP() > 0){
            String [] linkhinh= sanPham.getANHNHO().split(",");
            viewChiTietSanPham.HienThiSliderChiTietSanPham(linkhinh);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }


    }

    @Override
    public void LayDanhSachDanhGiaHienThi(int masp, int limit) {
        List<DanhGia> danhGias =moDelChiTietSanPham.LayDanhSachDanhGia(masp,limit);
        if (danhGias.size()> 0){
            viewChiTietSanPham.HienThiDanhSachDanhGia(danhGias);
        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham,Context context) {
        modelGioHang.MoKetNoi(context);
        boolean themgiohang = modelGioHang.ThemSanPhamGioHang(sanPham);
        if (themgiohang){
            viewChiTietSanPham.ThemSanPhamThanhCong();
        } else
        {
            viewChiTietSanPham.ThemSanPhamThatBai();
        }
    }
    public int LaySoLuongGioHang(Context context){
        modelGioHang.MoKetNoi(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        int dem = sanPhamList.size();

        return dem;
    }
}
