package com.vnh.lazadaofficial.Model.OjectClass;

import java.util.List;

/**
 * Created by HUYVINH on 19-Sep-16.
 */
public class DienTu {
    List<ThuongHieu> listThuongHieu;
    List<SanPham> listSanPham;
    String HinhAnh;
    String Tennoibat, TenTopnoibat;
    boolean thuongHieu;

    public boolean isThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(boolean thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getTennoibat() {
        return Tennoibat;
    }

    public void setTennoibat(String tennoibat) {
        Tennoibat = tennoibat;
    }

    public String getTenTopnoibat() {
        return TenTopnoibat;
    }

    public void setTenTopnoibat(String tenTopnoibat) {
        TenTopnoibat = tenTopnoibat;
    }


    public List<ThuongHieu> getListThuongHieu() {
        return listThuongHieu;
    }

    public void setListThuongHieu(List<ThuongHieu> listThuongHieu) {
        this.listThuongHieu = listThuongHieu;
    }

    public List<SanPham> getListSanPham() {
        return listSanPham;
    }

    public void setListSanPham(List<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
