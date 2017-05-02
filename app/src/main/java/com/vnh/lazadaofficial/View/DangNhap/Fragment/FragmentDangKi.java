package com.vnh.lazadaofficial.View.DangNhap.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.vnh.lazadaofficial.Model.OjectClass.NhanVien;
import com.vnh.lazadaofficial.Presenter.DangKi.IPresenterLogicDangKi;
import com.vnh.lazadaofficial.R;
import com.vnh.lazadaofficial.View.DangNhap.ViewDangKi;

/**
 * Created by HUYVINH on 04-Sep-16.
 */
public class FragmentDangKi extends Fragment implements ViewDangKi, View.OnFocusChangeListener {
    IPresenterLogicDangKi iPresenterLogicDangKi = new IPresenterLogicDangKi(this);
    EditText etHoTen, etEmail, etMatKhau, etNhapLaiMatKhau;
    Button btnDangKi;
    SwitchCompat check;
    TextInputLayout input_hoten, input_matkhau, input_nhaplaimatkhau, input_email;
    String emaildocquyen = "";
    Boolean kiemtrathongtin = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangki, container, false);

        etHoTen = (EditText) view.findViewById(R.id.etHoTen);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etMatKhau = (EditText) view.findViewById(R.id.etMatKhau);
        etNhapLaiMatKhau = (EditText) view.findViewById(R.id.etNhaplaiMatKhauDangKi);
        btnDangKi = (Button) view.findViewById(R.id.btnDangKy);
        check = (SwitchCompat) view.findViewById(R.id.check);

        input_hoten = (TextInputLayout) view.findViewById(R.id.input_hoten);
        input_email = (TextInputLayout) view.findViewById(R.id.input_email);
        input_matkhau = (TextInputLayout) view.findViewById(R.id.input_matkhau);
        input_nhaplaimatkhau = (TextInputLayout) view.findViewById(R.id.input_nhaplaimatkhau);

        etHoTen.setOnFocusChangeListener(this);
        etEmail.setOnFocusChangeListener(this);
        etNhapLaiMatKhau.setOnFocusChangeListener(this);

        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten = etHoTen.getText().toString();
                String email = etEmail.getText().toString();
                String matkhau = etMatKhau.getText().toString();

               if (kiemtrathongtin){

                   NhanVien nhanVien = new NhanVien();

                   nhanVien.setTenNv(ten);
                   nhanVien.setTenDangNhap(email);
                   nhanVien.setMatKhau(matkhau);
                   nhanVien.setMaLoaiNv(2);

                   iPresenterLogicDangKi.DangKi(nhanVien);
               }

                check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        emaildocquyen = isChecked + "";
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void DangKiThanhCong() {

    }

    @Override
    public void DangKiThatBai() {

    }

    @Override
    public void onFocusChange(View v, boolean b) {
        int id = v.getId();
        switch (id) {
            case R.id.etHoTen:
                if (!b) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_hoten.setErrorEnabled(true);
                        input_hoten.setError("Bạn chưa nhập vào mục này");
                        kiemtrathongtin = false;
                    } else {
                        input_hoten.setErrorEnabled(false);
                        input_hoten.setError("");
                        kiemtrathongtin = false;
                    }
                }
                break;
            case R.id.etEmail:
                if (!b) {
                    String chuoi = ((EditText) v).getText().toString();
                    Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_email.setErrorEnabled(true);
                        input_email.setError("Bạn chưa nhập vào mục này");
                        kiemtrathongtin = false;
                    } else {
                        if (!kiemtraemail) {
                            input_email.setErrorEnabled(true);
                            input_email.setError("Email không đúng dạng");
                            kiemtrathongtin = false;
                        } else {
                            input_email.setErrorEnabled(false);
                            input_email.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                break;
            case R.id.etMatKhau:
                break;
            case R.id.etNhaplaiMatKhauDangKi:
                if (!b){
                    String chuoi = ((EditText) v).getText().toString();
                    String matkhau = etMatKhau.getText().toString();
                    if (!chuoi.equals(matkhau)) {
                        input_nhaplaimatkhau.setErrorEnabled(true);
                        input_nhaplaimatkhau.setError("Nhập lại mật khẩu không khớp");
                        kiemtrathongtin = false;
                    } else {
                        input_nhaplaimatkhau.setErrorEnabled(false);
                        input_nhaplaimatkhau.setError("");
                        kiemtrathongtin = true;
                    }
                }

                break;

        }
    }
}
