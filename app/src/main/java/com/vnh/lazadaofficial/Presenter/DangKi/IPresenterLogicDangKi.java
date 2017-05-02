package com.vnh.lazadaofficial.Presenter.DangKi;

import com.vnh.lazadaofficial.Model.DangNhap_DangKi.ModelDangKi;
import com.vnh.lazadaofficial.Model.OjectClass.NhanVien;
import com.vnh.lazadaofficial.View.DangNhap.ViewDangKi;

/**
 * Created by HUYVINH on 12-Sep-16.
 */
public class IPresenterLogicDangKi implements IPresenterDangKi {
    ViewDangKi viewDangKi;
    ModelDangKi modelDangKi;

    public IPresenterLogicDangKi(ViewDangKi viewDangKi) {
        this.viewDangKi = viewDangKi;
        modelDangKi = new ModelDangKi();
    }

    @Override
    public void DangKi(NhanVien nhanvien) {
        modelDangKi.DangKiThanhVien(nhanvien);
    }
}
