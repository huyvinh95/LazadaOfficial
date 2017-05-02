package com.vnh.lazadaofficial.View.DanhGia;

import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.vnh.lazadaofficial.Model.OjectClass.DanhGia;
import com.vnh.lazadaofficial.Presenter.DanhGia.PresenterDanhGiaLogic;
import com.vnh.lazadaofficial.R;

import java.util.List;

public class DanhGiaActiviti extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener,ViewDanhGia,View.OnClickListener {
    RatingBar rb_binhchon;
    EditText et_NoiDungDanhGia,et_TieuDeDanhGia;
    TextInputLayout input_NoiDungDanhGia, input_TieuDeDanhGia;
    Button btn_DanhGia;
    int masp = 0;
    int sosao = 0;
    PresenterDanhGiaLogic presenterDanhGiaLogic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);
        rb_binhchon = (RatingBar) findViewById(R.id.rb_binhchon);
        et_NoiDungDanhGia = (EditText) findViewById(R.id.et_NoiDungDanhGia);
        et_TieuDeDanhGia = (EditText) findViewById(R.id.et_TieuDeDanhGia);
        input_TieuDeDanhGia = (TextInputLayout) findViewById(R.id.input_tieudedanhgia);
        input_NoiDungDanhGia = (TextInputLayout) findViewById(R.id.input_noidungdanhgia);
        btn_DanhGia = (Button) findViewById(R.id.btn_DangDanhGia);
        presenterDanhGiaLogic = new PresenterDanhGiaLogic(this);

        masp = getIntent().getIntExtra("masp",0);

        btn_DanhGia.setOnClickListener(this);
        rb_binhchon.setOnRatingBarChangeListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(getBaseContext(), "Đánh giá thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(getBaseContext(), "Đánh giá thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HienThiDanhSachNhanXet(List<DanhGia> danhGias) {

    }

    @Override
    public void onClick(View v) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String madg = telephonyManager.getDeviceId();
        String tendt = Build.MODEL;
        String tieude = et_TieuDeDanhGia.getText().toString();
        String noidung = et_NoiDungDanhGia.getText().toString();
        if (tieude.trim().length()>0){
            input_TieuDeDanhGia.setErrorEnabled(false);
            input_TieuDeDanhGia.setError("");
        } else{
            input_TieuDeDanhGia.setErrorEnabled(true);
            input_TieuDeDanhGia.setError("Bạn chưa nhập tiêu đề đánh giá");
        }

        if (noidung.trim().length()>0){
            input_NoiDungDanhGia.setErrorEnabled(false);
            input_NoiDungDanhGia.setError("");
        }else {
            input_NoiDungDanhGia.setErrorEnabled(true);
            input_NoiDungDanhGia.setError("Bạn chưa nhập nội dung đánh giá");
        }


        if (!input_NoiDungDanhGia.isErrorEnabled() && !input_TieuDeDanhGia.isErrorEnabled()){
            DanhGia danhGia = new DanhGia();
            danhGia.setMADG(madg);
            danhGia.setTENTHIETBI(tendt);
            danhGia.setMASP(masp);
            danhGia.setTIEUDE(tieude);
            danhGia.setNOIDUNG(noidung);
            danhGia.setSOSAO(sosao);

            presenterDanhGiaLogic.DanhGiaChiTiet(danhGia);

        }

    }
}
